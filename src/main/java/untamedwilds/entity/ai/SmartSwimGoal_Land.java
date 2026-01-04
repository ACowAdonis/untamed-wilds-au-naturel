package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMob;
import untamedwilds.util.EntityUtils;

public class SmartSwimGoal_Land extends Goal {
   private final ComplexMob entity;
   private final float speed;
   private Vec3 landTarget;
   private int searchCooldown;

   public SmartSwimGoal_Land(ComplexMob entityIn) {
      this(entityIn, 0.7F);
   }

   public SmartSwimGoal_Land(ComplexMob entityIn, float speedIn) {
      this.entity = entityIn;
      this.speed = speedIn;
      this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
      entityIn.getNavigation().setCanFloat(true);
   }

   public boolean canUse() {
      if (!this.entity.isInWater()) {
         return false;
      } else if (this.entity.getTarget() == null && this.entity.getNavigation().canFloat()) {
         double eyeHeight = (double)this.entity.getEyeHeight() - (this.entity.isBaby() ? -0.8 : 0.18F);
         return this.entity.getFluidHeight(FluidTags.WATER) > eyeHeight || this.entity.isInLava();
      } else {
         return false;
      }
   }

   public void start() {
      this.landTarget = null;
      this.searchCooldown = 0;
      if (!this.entity.canMove()) {
         this.entity.setSleeping(false);
         this.entity.setSitting(false);
         if (this.entity.getCommandInt() == 2) {
            this.entity.setCommandInt(0);
         }
      }
   }

   public boolean canContinueToUse() {
      return !this.entity.onGround() && this.entity.isInWater() && this.entity.getTarget() == null;
   }

   public void tick() {
      // Try to find and navigate toward land
      if (this.searchCooldown > 0) {
         this.searchCooldown--;
      }

      if ((this.landTarget == null || this.entity.getNavigation().isDone()) && this.searchCooldown <= 0) {
         // Search for land position
         Vec3 landPos = LandRandomPos.getPos(this.entity, 10, 7);
         if (landPos != null) {
            this.landTarget = landPos;
            this.entity.getNavigation().moveTo(landPos.x, landPos.y, landPos.z, this.speed * 1.5);
         } else {
            // No land found, strafe forward as fallback
            this.entity.getMoveControl().strafe(this.speed, 0.0F);
         }
         this.searchCooldown = 20; // Wait 1 second before searching again
      }

      // Jump when submerged or colliding
      boolean colliding = this.entity.level().collidesWithSuffocatingBlock(this.entity, this.entity.getBoundingBox().expandTowards(this.entity.getLookAngle()));
      if (this.entity.isEyeInFluid(FluidTags.WATER) || colliding) {
         this.entity.getJumpControl().jump();
      }

      if (this.entity.tickCount % 6 == 0) {
         EntityUtils.spawnParticlesOnEntity(this.entity.level(), this.entity, ParticleTypes.SPLASH, 4, 2);
      }
   }

   @Override
   public void stop() {
      this.landTarget = null;
   }
}
