package untamedwilds.entity.mammal;

import com.github.alexthe666.citadel.animation.Animation;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.ActiveGrazeGoal;
import untamedwilds.entity.ai.AmphibiousRandomSwimGoal;
import untamedwilds.entity.ai.AmphibiousTransition;
import untamedwilds.entity.ai.GotoSleepGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.entity.ai.unique.HippoTerritoryTargetGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.util.EntityUtils;

public class EntityHippo extends ComplexMobAmphibious implements INewSkins, ISpecies {
   public static Animation EAT;
   public static Animation IDLE_YAWN;
   public static Animation IDLE_LOOK;
   public static Animation ATTACK;
   public static Animation IDLE_TALK;
   public int angryProgress;

   public EntityHippo(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      IDLE_YAWN = Animation.create(36);
      IDLE_LOOK = Animation.create(128);
      IDLE_TALK = Animation.create(20);
      EAT = Animation.create(48);
      ATTACK = Animation.create(24);
      this.isAmphibious = true;
      this.turn_speed = 0.3F;
   }

   public void registerGoals() {
      this.goalSelector.addGoal(2, new SmartMeleeAttackGoal(this, 1.4, false));
      this.goalSelector.addGoal(3, new SmartMateGoal(this, 0.8));
      this.goalSelector.addGoal(3, new ActiveGrazeGoal(this, 10));
      this.goalSelector.addGoal(4, new AmphibiousTransition(this, 1.1));
      this.goalSelector.addGoal(4, new GotoSleepGoal(this, 1.0));
      this.goalSelector.addGoal(5, new SmartWanderGoal(this, 1.0, 120, 0, false));
      this.goalSelector.addGoal(5, new AmphibiousRandomSwimGoal(this, 1.0, 120));
      this.goalSelector.addGoal(6, new SmartLookAtGoal(this, LivingEntity.class, 10.0F));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]).setAlertOthers(new Class[0]));
      this.targetSelector
         .addGoal(
            3,
            new HippoTerritoryTargetGoal<LivingEntity>(
               this,
               LivingEntity.class,
               true,
               input -> !(input instanceof EntityHippo) && getEcoLevel(input) <= getEcoLevel(this) && input.getMobType() != MobType.WATER
            )
         );
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 9.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.6)
         .add(Attributes.MOVEMENT_SPEED, 0.8)
         .add(Attributes.FOLLOW_RANGE, 24.0)
         .add(Attributes.MAX_HEALTH, 60.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.ARMOR, 0.0)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0);
   }

   @Override
   public boolean wantsToBreed() {
      return ConfigGamerules.naturalBreeding.get() && this.age == 0 ? this.getHunger() >= 80 : false;
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         if (this.isInWater() && this.getTarget() == null) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.01, 0.0));
         }

         if (this.level().getGameTime() % 1000L == 0L) {
            this.addHunger(-10);
            if (!this.isStarving()) {
               this.heal(1.0F);
            }
         }

         int i = this.random.nextInt(3000);
         if (i <= 8 && !this.isInWater() && !this.isAngry() && !this.isSleeping() && this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(IDLE_YAWN);
         }

         if (i > 8 && i <= 12 && !this.isAngry() && !this.isSleeping() && this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(IDLE_LOOK);
         }

         if (i == 13 && !this.isInWater() && this.isNotMoving() && this.canMove() && this.getAnimation() == NO_ANIMATION) {
            this.setSitting(true);
         }

         if (i == 14 && this.isSitting()) {
            this.setSitting(false);
         }

         if (i == 15 && !this.isActive() && !this.isSleeping() && this.isInWater()) {
            this.setAnimation(IDLE_YAWN);
            this.setSleeping(true);
            this.forceSleep = -800 - this.random.nextInt(1200);
         }

         if (i > 2980 && !this.isBaby()) {
            this.setAnimation(IDLE_TALK);
         }

         if (this.getAnimation() == ATTACK
            && this.getTarget() != null
            && this.getBoundingBox().inflate(1.2F, 1.0, 1.2F).contains(this.getTarget().getPosition(0.0F))
            && this.getAnimationTick() > 8) {
            LivingEntity target = this.getTarget();
            this.getTarget().hurt(this.damageSources().mobAttack(this), (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
            EntityUtils.destroyBoat(this.level(), target);
         }

         this.setAngry(this.getTarget() != null);
      }

      if (this.getAnimation() != NO_ANIMATION && this.getAnimation() == IDLE_TALK && this.getAnimationTick() == 1 && this.getAmbientSound() != null) {
         this.playSound(this.getAmbientSound(), this.getSoundVolume(), this.getVoicePitch());
      }

      if (this.level().isClientSide && this.isAngry() && this.angryProgress < 40) {
         this.angryProgress += 4;
      } else if (this.level().isClientSide && !this.isAngry() && this.angryProgress > 0) {
         this.angryProgress -= 4;
      }

      super.aiStep();
   }

   @Override
   public boolean wantsToBeOnLand() {
      return this.isActive();
   }

   @Override
   public boolean wantsToBeInWater() {
      return !this.isActive();
   }

   public boolean doHurtTarget(Entity entityIn) {
      boolean flag = super.doHurtTarget(entityIn);
      if (flag && this.getAnimation() == NO_ANIMATION && !this.isBaby()) {
         Animation anim = this.chooseAttackAnimation();
         this.setAnimation(anim);
      }

      return flag;
   }

   @Override
   public boolean hurt(DamageSource damageSource, float amount) {
      this.performRetaliation(damageSource, this.getHealth(), amount, true);
      return super.hurt(damageSource, amount);
   }

   private Animation chooseAttackAnimation() {
      return ATTACK;
   }

   @Nullable
   public EntityHippo getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      return this.create_offspring(new EntityHippo((EntityType<? extends ComplexMob>)ModEntity.HIPPO.get(), this.level()));
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, EAT, IDLE_YAWN, IDLE_LOOK, IDLE_TALK, ATTACK};
   }

   @Override
   public Animation getAnimationEat() {
      return EAT;
   }
}
