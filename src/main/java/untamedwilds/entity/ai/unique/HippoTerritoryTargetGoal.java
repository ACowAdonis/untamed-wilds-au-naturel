package untamedwilds.entity.ai.unique;

import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ai.target.HuntMobTarget;

public class HippoTerritoryTargetGoal<T extends LivingEntity> extends HuntMobTarget<T> {
   public HippoTerritoryTargetGoal(ComplexMob creature, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
      super(creature, classTarget, checkSight, 200, false, null);
      this.targetEntitySelector = entity -> targetSelector != null && !targetSelector.test(entity)
            ? false
            : TargetingConditions.forCombat().test(creature, entity) && this.canAttack(entity, TargetingConditions.DEFAULT);
   }

   @Override
   public boolean canUse() {
      return !(this.mob.getHealth() < this.mob.getMaxHealth() / 2.0F) && !this.mob.isBaby() && this.mob.isInWater() ? super.canUse() : false;
   }
}
