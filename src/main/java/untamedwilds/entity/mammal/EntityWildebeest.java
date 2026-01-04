package untamedwilds.entity.mammal;

import com.github.alexthe666.citadel.animation.Animation;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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
import untamedwilds.entity.ai.GotoSleepGoal;
import untamedwilds.entity.ai.GrazeGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartPanicGoal;
import untamedwilds.entity.ai.SmartSwimGoal_Land;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.entity.ai.target.ProtectChildrenTarget;
import untamedwilds.init.ModEntity;
import untamedwilds.util.EntityUtils;

public class EntityWildebeest extends ComplexMobTerrestrial implements INewSkins, ISpecies, IPackEntity {
   public static Animation IDLE_TALK;
   public static Animation IDLE_SHAKE;
   public static Animation HOP;
   public static Animation EAT;

   public EntityWildebeest(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      IDLE_TALK = Animation.create(20);
      IDLE_SHAKE = Animation.create(80);
      HOP = Animation.create(30);
      EAT = Animation.create(76);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new SmartSwimGoal_Land(this));
      this.goalSelector.addGoal(2, new SmartPanicGoal(this, 2.3, true));
      this.goalSelector.addGoal(3, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.2, 1.6, input -> getEcoLevel(input) > getEcoLevel(this)));
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
               this, LivingEntity.class, true, input -> !(input instanceof EntityWildebeest) && getEcoLevel(input) > getEcoLevel(this)
            )
         );
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 4.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.0)
         .add(Attributes.MOVEMENT_SPEED, 0.23)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 20.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
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
         if (this.herd == null) {
            IPackEntity.initPack(this);
         } else {
            this.herd.tick();
         }

         if (this.getSpeed() > 0.24F && this.getAnimation() == NO_ANIMATION && !this.getNavigation().isDone()) {
            this.setAnimation(HOP);
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

         if (i > 2960 && !this.isBaby()) {
            this.setAnimation(i % 2 == 0 ? IDLE_SHAKE : IDLE_TALK);
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

   private Animation chooseAttackAnimation() {
      return NO_ANIMATION;
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
      this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
   }

   @Nullable
   public EntityWildebeest getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      return this.create_offspring(new EntityWildebeest((EntityType<? extends ComplexMob>)ModEntity.WILDEBEEST.get(), this.level()));
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
      return new Animation[]{NO_ANIMATION, IDLE_TALK, IDLE_SHAKE, HOP, EAT};
   }

   @Override
   public Animation getAnimationEat() {
      return EAT;
   }
}
