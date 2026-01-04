package untamedwilds.entity;

import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;

public abstract class ComplexMobAquatic extends ComplexMob {
   public ComplexMobAquatic(EntityType<? extends ComplexMob> entity, Level worldIn) {
      super(entity, worldIn);
      this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
      this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
      this.lookControl = new SmoothSwimmingLookControl(this, 10);
   }

   public void onInsideBubbleColumn(boolean downwards) {
   }

   public boolean canDrownInFluidType(FluidType type) {
      return type != ForgeMod.WATER_TYPE.get();
   }

   public boolean isPushedByFluid(FluidType type) {
      return type != ForgeMod.WATER_TYPE.get();
   }

   public MobType getMobType() {
      return MobType.WATER;
   }

   protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
      return p_213348_2_.height * 0.2F;
   }

   public void baseTick() {
      int i = this.getAirSupply();
      super.baseTick();
      this.updateAir(i);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
   }

   protected PathNavigation createNavigation(Level worldIn) {
      return new WaterBoundPathNavigation(this, worldIn);
   }

   @Override
   public void aiStep() {
      this.handleOutOfWaterBehavior();
      super.aiStep();
   }

   protected void updateAir(int air) {
      if (this.isAlive() && !this.isInWaterOrBubble()) {
         this.setAirSupply(air - 1);
         if (this.getAirSupply() == -20) {
            this.setAirSupply(0);
            this.hurt(this.damageSources().drown(), 2.0F);
         }
      } else {
         this.setAirSupply(300);
      }
   }

   protected void handleOutOfWaterBehavior() {
      if (!this.isInWater() && this.onGround() && this.verticalCollision) {
         this.setDeltaMovement(
            this.getDeltaMovement()
               .add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F))
         );
         this.setOnGround(false);
         this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
      }
   }

   protected abstract SoundEvent getFlopSound();

   protected SoundEvent getSwimSound() {
      return SoundEvents.FISH_SWIM;
   }

   public void travel(Vec3 movement) {
      if (!this.level().isClientSide() && this.isInWater()) {
         this.moveRelative(this.getSpeed(), movement);
         this.move(MoverType.SELF, this.getDeltaMovement());
         this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
         if (this.getTarget() == null) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
         }
      } else {
         super.travel(movement);
      }
   }

   protected static class SwimGoal extends RandomSwimmingGoal {
      public int heightFromBottom;

      public SwimGoal(ComplexMobAquatic entity) {
         this(entity, -1);
      }

      public SwimGoal(ComplexMobAquatic entity, int offset) {
         super(entity, 1.0, 20);
         this.heightFromBottom = offset;
      }

      public SwimGoal(ComplexMobAquatic entity, int offset, int chance) {
         super(entity, 1.0, chance);
         this.heightFromBottom = offset;
      }

      public boolean canUse() {
         return super.canUse();
      }

      @Nullable
      protected Vec3 getPosition() {
         Vec3 vector3d = BehaviorUtils.getRandomSwimmablePos(this.mob, 10, 7);
         if (vector3d != null && this.heightFromBottom > 0 && this.mob.level().canSeeSkyFromBelowWater(this.mob.blockPosition())) {
            int offset = this.heightFromBottom + this.mob.level().getRandom().nextInt(7) - 4;
            return new Vec3(
               vector3d.x(),
               (double)(this.mob.level().getHeight(Types.OCEAN_FLOOR, (int)vector3d.x(), (int)vector3d.z()) + offset),
               vector3d.z()
            );
         } else {
            return vector3d;
         }
      }
   }
}
