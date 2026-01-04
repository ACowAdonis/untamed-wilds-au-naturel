package untamedwilds.entity.ai.unique;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMobAquatic;

public class WhaleBreachGoal extends JumpGoal {
   private static final int[] STEPS_TO_CHECK = new int[]{0, 8, 9, 12, 14};
   private final ComplexMobAquatic whale;
   private final int interval;
   private boolean breached;

   public WhaleBreachGoal(ComplexMobAquatic p_25168_, int p_25169_) {
      this.whale = p_25168_;
      this.interval = reducedTickDelay(p_25169_);
   }

   public boolean canUse() {
      if (this.whale.getRandom().nextInt(this.interval) != 0) {
         return false;
      } else {
         Direction direction = this.whale.getMotionDirection();
         int i = direction.getStepX();
         int j = direction.getStepZ();
         BlockPos blockpos = this.whale.blockPosition();

         for (int k : STEPS_TO_CHECK) {
            if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean waterIsClear(BlockPos p_25173_, int p_25174_, int p_25175_, int p_25176_) {
      BlockPos blockpos = p_25173_.offset(p_25174_ * p_25176_, 0, p_25175_ * p_25176_);
      return this.whale.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.whale.level().getBlockState(blockpos).blocksMotion();
   }

   private boolean surfaceIsClear(BlockPos p_25179_, int p_25180_, int p_25181_, int p_25182_) {
      return this.whale.level().getBlockState(p_25179_.offset(p_25180_ * p_25182_, 1, p_25181_ * p_25182_)).isAir()
         && this.whale.level().getBlockState(p_25179_.offset(p_25180_ * p_25182_, 2, p_25181_ * p_25182_)).isAir();
   }

   public boolean canContinueToUse() {
      double d0 = this.whale.getDeltaMovement().y;
      return (!(d0 * d0 < 0.03F) || this.whale.getXRot() == 0.0F || !(Math.abs(this.whale.getXRot()) < 10.0F) || !this.whale.isInWater())
         && !this.whale.onGround();
   }

   public boolean isInterruptable() {
      return false;
   }

   public void start() {
      Direction direction = this.whale.getMotionDirection();
      this.whale.setDeltaMovement(this.whale.getDeltaMovement().add((double)direction.getStepX() * 0.6, 0.7, (double)direction.getStepZ() * 0.6));
      this.whale.getNavigation().stop();
   }

   public void stop() {
      this.whale.setXRot(0.0F);
   }

   public void tick() {
      boolean flag = this.breached;
      if (!flag) {
         FluidState fluidstate = this.whale.level().getFluidState(this.whale.blockPosition());
         this.breached = fluidstate.is(FluidTags.WATER);
      }

      if (this.breached && !flag) {
         this.whale.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
      }

      Vec3 vec3 = this.whale.getDeltaMovement();
      if (vec3.y * vec3.y < 0.03F && this.whale.getXRot() != 0.0F) {
         this.whale.setXRot(Mth.rotLerp(this.whale.getXRot(), 0.0F, 0.2F));
      } else if (vec3.length() > 1.0E-5F) {
         double d0 = vec3.horizontalDistance();
         double d1 = Math.atan2(-vec3.y, d0) * 180.0F / (float)Math.PI;
         this.whale.setXRot((float)d1);
      }
   }
}
