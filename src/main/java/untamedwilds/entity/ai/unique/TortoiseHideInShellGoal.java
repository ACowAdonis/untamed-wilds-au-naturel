package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import untamedwilds.entity.ComplexMob;

public class TortoiseHideInShellGoal<T extends LivingEntity> extends Goal {
   protected final Class<T> classToAvoid;
   protected T avoidTarget;
   protected ComplexMob taskOwner;
   protected final float avoidDistance;
   private final TargetingConditions builtTargetSelector;

   public TortoiseHideInShellGoal(ComplexMob entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, Predicate<LivingEntity> targetSelector) {
      this.taskOwner = entityIn;
      this.classToAvoid = classToAvoidIn;
      this.avoidDistance = avoidDistanceIn;
      this.builtTargetSelector = TargetingConditions.forCombat().range((double)avoidDistanceIn).selector(targetSelector);
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if (this.taskOwner.tickCount % 40 != 0) {
         return false;
      } else if (this.taskOwner.getTarget() == null && this.taskOwner.getCommandInt() == 0 && !this.taskOwner.isTame()) {
         List<T> list = this.taskOwner
            .level()
            .getNearbyEntities(
               this.classToAvoid,
               this.builtTargetSelector,
               this.taskOwner,
               this.taskOwner.getBoundingBox().inflate((double)this.avoidDistance, 4.0, (double)this.avoidDistance)
            );
         if (list.isEmpty()) {
            this.taskOwner.setSitting(false);
            return false;
         } else {
            this.avoidTarget = list.get(0);
            return true;
         }
      } else {
         return false;
      }
   }

   public void start() {
      super.start();
      this.taskOwner.getNavigation().stop();
      this.taskOwner.setSitting(true);
   }

   public void stop() {
      super.stop();
   }

   public void tick() {
      if (this.taskOwner.getRandom().nextInt(40) == 0) {
         List<T> list = this.taskOwner
            .level()
            .getNearbyEntities(
               this.classToAvoid,
               this.builtTargetSelector,
               this.taskOwner,
               this.taskOwner.getBoundingBox().inflate((double)this.avoidDistance, 4.0, (double)this.avoidDistance)
            );
         if (list.isEmpty()) {
            this.taskOwner.setSitting(false);
         }
      }

      if (this.taskOwner.distanceTo(this.avoidTarget) > 10.0F) {
         this.taskOwner.setSitting(false);
      }

      super.tick();
   }

   public boolean canContinueToUse() {
      return !this.taskOwner.isSitting();
   }
}
