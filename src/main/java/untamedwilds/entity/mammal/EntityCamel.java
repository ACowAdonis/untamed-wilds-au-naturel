package untamedwilds.entity.mammal;

import com.github.alexthe666.citadel.animation.Animation;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.IPackEntity;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ProjectileSpit;
import untamedwilds.entity.ai.GotoSleepGoal;
import untamedwilds.entity.ai.GrazeGoal;
import untamedwilds.entity.ai.SmartFollowOwnerGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.SmartSwimGoal_Land;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.entity.ai.target.BeAnAssTarget;
import untamedwilds.entity.ai.target.ProtectChildrenTarget;
import untamedwilds.entity.ai.target.SmartOwnerHurtTargetGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.util.EntityUtils;

public class EntityCamel extends ComplexMobTerrestrial implements INewSkins, ISpecies, IPackEntity, RangedAttackMob {
   public static Animation IDLE_TALK;
   public static Animation ATTACK_SPIT;

   public EntityCamel(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      IDLE_TALK = Animation.create(20);
      ATTACK_SPIT = Animation.create(20);
      this.turn_speed = 0.2F;
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new SmartSwimGoal_Land(this));
      this.goalSelector.addGoal(2, new SmartMeleeAttackGoal(this, 1.6, false));
      this.goalSelector.addGoal(3, new SmartMateGoal(this, 0.8));
      this.goalSelector.addGoal(3, new GrazeGoal(this, 10));
      this.goalSelector.addGoal(4, new GotoSleepGoal(this, 1.0));
      this.goalSelector.addGoal(5, new SmartWanderGoal(this, 1.0, 120, 0, true));
      this.goalSelector.addGoal(6, new SmartLookAtGoal(this, LivingEntity.class, 10.0F));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
      this.targetSelector
         .addGoal(
            2,
            new ProtectChildrenTarget<LivingEntity>(
               this, LivingEntity.class, true, input -> !(input instanceof EntityCamel) && getEcoLevel(input) > getEcoLevel(this)
            )
         );
      this.targetSelector.addGoal(3, new BeAnAssTarget<LivingEntity>(this, LivingEntity.class, true));
   }

   protected void reassessTameGoals() {
      if (this.isTame()) {
         this.goalSelector.addGoal(3, new SmartFollowOwnerGoal(this, 1.3, 12.0F, 3.0F));
         this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
         this.targetSelector.addGoal(2, new SmartOwnerHurtTargetGoal(this));
      }
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 7.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.5)
         .add(Attributes.MOVEMENT_SPEED, 0.2)
         .add(Attributes.FOLLOW_RANGE, 12.0)
         .add(Attributes.MAX_HEALTH, 35.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.ARMOR, 2.0)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0);
   }

   @Override
   public boolean wantsToBreed() {
      return ConfigGamerules.naturalBreeding.get() && this.age == 0 ? this.getHunger() >= 80 : false;
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         if (this.herd == null) {
            IPackEntity.initPack(this);
         } else {
            this.herd.tick();
         }

         if (this.level().getGameTime() % 1000L == 0L) {
            this.addHunger(-10);
            if (!this.isStarving()) {
               this.heal(1.0F);
            }
         }

         int i = this.random.nextInt(3000);
         if (i == 13 && !this.isInWater() && this.getTarget() == null && this.isNotMoving() && this.canMove() && this.getAnimation() == NO_ANIMATION) {
            this.setSitting(true);
         }

         if (i == 14 && this.isSitting()) {
            this.setSitting(false);
         }

         if (i > 2980 && !this.isBaby()) {
            this.setAnimation(IDLE_TALK);
         }

         if (this.getAnimation() == IDLE_TALK && this.getAnimationTick() == 1 && this.getAmbientSound() != null) {
            this.playSound(this.getAmbientSound(), this.getSoundVolume(), this.getVoicePitch());
         }
      }

      super.aiStep();
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
   public boolean hurt(DamageSource p_19946_, float p_19947_) {
      return p_19946_.is(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS) ? false : super.hurt(p_19946_, p_19947_);
   }

   private Animation chooseAttackAnimation() {
      return NO_ANIMATION;
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
      this.playSound(SoundEvents.RAVAGER_STEP, 0.15F, 1.0F);
   }

   @Nullable
   public EntityCamel getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      return this.create_offspring(new EntityCamel((EntityType<? extends ComplexMob>)ModEntity.CAMEL.get(), this.level()));
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND
         && !this.level().isClientSide()
         && !this.isTame()
         && this.isBaby()
         && EntityUtils.hasFullHealth(this)
         && this.isFood(itemstack)) {
         this.playSound(SoundEvents.HORSE_EAT, 1.5F, 0.8F);
         if (this.getRandom().nextInt(3) == 0) {
            this.tame(player);
            EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.HEART, 3, 6);
         } else {
            EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.SMOKE, 3, 3);
         }
      }

      return super.mobInteract(player, hand);
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, IDLE_TALK, ATTACK_SPIT};
   }

   @Override
   public Animation getAnimationEat() {
      return NO_ANIMATION;
   }

   public void performRangedAttack(LivingEntity entityIn, float p_33318_) {
      ProjectileSpit camel_spit = new ProjectileSpit(this.level(), this, new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
      if (this.getAnimation() == NO_ANIMATION) {
         this.setAnimation(ATTACK_SPIT);
      }

      double d0 = entityIn.getX() - this.getX();
      double d1 = entityIn.getY(0.3333333333333333) - camel_spit.getY();
      double d2 = entityIn.getZ() - this.getZ();
      double d3 = Math.sqrt(d0 * d0 + d2 * d2) * 0.2F;
      camel_spit.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
      if (!this.isSilent()) {
         this.level()
            .playSound(
               null,
               this.getX(),
               this.getY(),
               this.getZ(),
               SoundEvents.LLAMA_SPIT,
               this.getSoundSource(),
               1.0F,
               1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F
            );
      }

      this.level().addFreshEntity(camel_spit);
   }
}
