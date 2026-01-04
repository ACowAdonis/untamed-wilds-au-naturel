package untamedwilds.entity.ai.unique;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ai.target.HuntMobTarget;
import untamedwilds.entity.relict.EntitySpitter;

public class SpitterTerritorialityGoal<T extends LivingEntity> extends HuntMobTarget<T> {
   private final int executionChance;
   private final float threshold;

   public SpitterTerritorialityGoal(ComplexMob creature, Class<T> classTarget, boolean checkSight) {
      this(creature, classTarget, 300, checkSight, 0.0F, null);
   }

   public SpitterTerritorialityGoal(
      ComplexMob creature, Class<T> classTarget, int chance, boolean checkSight, float threshold, Predicate<LivingEntity> targetSelector
   ) {
      super(creature, classTarget, checkSight, 200, false, targetSelector);
      this.executionChance = chance;
      this.threshold = threshold;
   }

   @Override
   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      return entity instanceof EntitySpitter
            && ((EntitySpitter)entity).getGender() == ((EntitySpitter)this.mob).getGender()
            && !entity.isBaby()
            && !entity.equals(this.mob)
            && !((EntitySpitter)this.mob).isTame()
            && (predicate == null || predicate.test(entity))
            && !(entity.getHealth() / entity.getMaxHealth() < this.threshold)
         ? this.canAttack(entity, TargetingConditions.forCombat().range(this.getFollowDistance()))
         : false;
   }

   @Override
   public boolean canUse() {
      if (!this.mob.isBaby() && this.mob.getRandom().nextInt(this.executionChance) == 0) {
         List<T> list = this.mob
            .level()
            .getEntitiesOfClass(this.targetClass, this.mob.getBoundingBox().inflate(this.getFollowDistance(), 12.0, this.getFollowDistance()), this.targetEntitySelector);
         if (list.isEmpty()) {
            return false;
         } else {
            list.sort(this.sorter);
            this.targetMob = list.get(0);
            return true;
         }
      } else {
         return false;
      }
   }
}
