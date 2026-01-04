package untamedwilds.entity.ai;

import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.IPackEntity;

public class FishWanderAsSchoolGoal extends RandomSwimmingGoal {
   private final ComplexMob taskOwner;
   private final int maxDist;

   public FishWanderAsSchoolGoal(ComplexMobAquatic entityIn) {
      this(entityIn, 1.0, 20, 5);
   }

   public FishWanderAsSchoolGoal(ComplexMobAquatic entityIn, double speedIn, int chance, int maxDist) {
      super(entityIn, speedIn, chance);
      this.maxDist = maxDist;
      this.taskOwner = entityIn;
   }

   public boolean canUse() {
      if (!(this.mob instanceof IPackEntity)) {
         return false;
      } else {
         return this.taskOwner.herd != null && this.taskOwner.herd.getLeader() == this.taskOwner ? super.canUse() : false;
      }
   }

   protected Vec3 getPosition() {
      return BehaviorUtils.getRandomSwimmablePos(this.mob, 20, 7);
   }

   public void start() {
      this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);

      for (ComplexMob herd_member : this.taskOwner.herd.creatureList) {
         if (this.taskOwner.distanceTo(herd_member) < (float)this.maxDist) {
            double posX = this.wantedX + (herd_member.getX() - this.taskOwner.getX());
            double posY = this.wantedY + (herd_member.getY() - this.taskOwner.getY());
            double posZ = this.wantedZ + (herd_member.getZ() - this.taskOwner.getZ());
            herd_member.getNavigation().moveTo(posX, posY, posZ, this.speedModifier);
         }
      }
   }
}
