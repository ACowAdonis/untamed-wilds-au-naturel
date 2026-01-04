package untamedwilds.entity.ai;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMob;

public class SmartPanicGoal extends Goal {
   public static final int WATER_CHECK_DISTANCE_VERTICAL = 1;
   protected final PathfinderMob taskOwner;
   protected final double speedModifier;
   protected double posX;
   protected double posY;
   protected double posZ;
   protected boolean isRunning;
   protected boolean chainFlee;

   public SmartPanicGoal(PathfinderMob entityIn, double speedIn) {
      this(entityIn, speedIn, false);
   }

   public SmartPanicGoal(PathfinderMob p_25691_, double p_25692_, boolean chainFlee) {
      this.taskOwner = p_25691_;
      this.speedModifier = p_25692_;
      this.chainFlee = chainFlee;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if (!this.shouldPanic()) {
         return false;
      } else {
         if (this.taskOwner.isOnFire()) {
            BlockPos blockpos = this.lookForWater(this.taskOwner.level(), this.taskOwner, 5);
            if (blockpos != null) {
               this.posX = (double)blockpos.getX();
               this.posY = (double)blockpos.getY();
               this.posZ = (double)blockpos.getZ();
               return true;
            }
         }

         return this.findRandomPosition();
      }
   }

   protected boolean shouldPanic() {
      return this.taskOwner.getLastHurtByMob() != null || this.taskOwner.isFreezing() || this.taskOwner.isOnFire();
   }

   protected boolean findRandomPosition() {
      Vec3 vec3;
      if (this.taskOwner.getLastHurtByMob() != null) {
         Entity lastHurt = this.taskOwner.getLastHurtByMob();
         vec3 = DefaultRandomPos.getPosAway(this.taskOwner, 5, 4, new Vec3(lastHurt.getX(), lastHurt.getY(), lastHurt.getZ()));
      } else {
         vec3 = DefaultRandomPos.getPos(this.taskOwner, 5, 4);
      }

      if (vec3 == null) {
         return false;
      } else {
         this.posX = vec3.x;
         this.posY = vec3.y;
         this.posZ = vec3.z;
         return true;
      }
   }

   public boolean isRunning() {
      return this.isRunning;
   }

   public void start() {
      if (this.chainFlee && this.taskOwner instanceof ComplexMob mob && mob.herd != null) {
         for (ComplexMob creature : mob.herd.creatureList) {
            if (this.taskOwner.getLastHurtByMob() == null || !(this.taskOwner.distanceTo(this.taskOwner.getLastHurtByMob()) > 10.0F)) {
               creature.setLastHurtByMob(this.taskOwner.getLastHurtByMob());
            }
         }
      }

      this.taskOwner.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
      this.isRunning = true;
   }

   public void stop() {
      this.isRunning = false;
   }

   public boolean canContinueToUse() {
      return this.taskOwner.getLastHurtByMob() != null && this.taskOwner.distanceTo(this.taskOwner.getLastHurtByMob()) > 10.0F ? false : !this.taskOwner.getNavigation().isDone();
   }

   @Nullable
   protected BlockPos lookForWater(BlockGetter p_198173_, Entity p_198174_, int p_198175_) {
      BlockPos blockpos = p_198174_.blockPosition();
      return !p_198173_.getBlockState(blockpos).getCollisionShape(p_198173_, blockpos).isEmpty()
         ? null
         : (BlockPos)BlockPos.findClosestMatch(p_198174_.blockPosition(), p_198175_, 1, p_196649_ -> p_198173_.getFluidState(p_196649_).is(FluidTags.WATER))
            .orElse(null);
   }
}
