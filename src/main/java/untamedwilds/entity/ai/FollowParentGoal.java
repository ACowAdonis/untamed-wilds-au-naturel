package untamedwilds.entity.ai;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import untamedwilds.entity.ComplexMob;

public class FollowParentGoal extends Goal {
   private final ComplexMob taskOwner;
   private ComplexMob parentAnimal;
   private final double moveSpeed;
   private int delayCounter;

   public FollowParentGoal(ComplexMob entityIn, double speedIn) {
      this.taskOwner = entityIn;
      this.moveSpeed = speedIn;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.TARGET));
   }

   public boolean canUse() {
      if (this.taskOwner.isBaby() && this.taskOwner.getTarget() == null && this.taskOwner.canMove()) {
         List<? extends ComplexMob> list = this.taskOwner.level().getEntitiesOfClass(this.taskOwner.getClass(), this.taskOwner.getBoundingBox().inflate(8.0, 4.0, 8.0));
         ComplexMob entityanimal = null;
         double d0 = Double.MAX_VALUE;

         for (ComplexMob entityanimal1 : list) {
            if (!entityanimal1.isBaby() && entityanimal1.getVariant() == this.taskOwner.getVariant()) {
               double d1 = this.taskOwner.distanceToSqr(entityanimal1);
               if (d1 <= d0) {
                  d0 = d1;
                  entityanimal = entityanimal1;
               }
            }
         }

         if (entityanimal == null) {
            return false;
         } else if (d0 < 9.0) {
            return false;
         } else {
            this.parentAnimal = entityanimal;
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      if (this.taskOwner.getAge() >= 0) {
         return false;
      } else if (!this.parentAnimal.isAlive()) {
         return false;
      } else {
         double d0 = this.taskOwner.distanceToSqr(this.parentAnimal);
         return d0 >= 9.0 && d0 <= 256.0;
      }
   }

   public void start() {
      this.delayCounter = 0;
   }

   public void stop() {
      this.parentAnimal = null;
   }

   public void tick() {
      if (--this.delayCounter <= 0) {
         this.delayCounter = 10;
         this.taskOwner.getNavigation().moveTo(this.parentAnimal, this.moveSpeed);
      }
   }
}
