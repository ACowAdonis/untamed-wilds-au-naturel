package untamedwilds.entity.ai.target;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;

public class HuntWoundedTarget<T extends LivingEntity> extends HuntMobTarget<T> {
   private final int executionChance;

   public HuntWoundedTarget(ComplexMob creature, Class<T> classTarget, boolean checkSight) {
      this(creature, classTarget, 300, checkSight, null);
   }

   public HuntWoundedTarget(ComplexMob creature, Class<T> classTarget, int chance, boolean checkSight, Predicate<LivingEntity> targetSelector) {
      super(creature, classTarget, checkSight, 200, false, targetSelector);
      this.executionChance = chance;
   }

   @Override
   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      if (!(entity instanceof Creeper)
         && !entity.equals(this.mob)
         && ((Boolean)ConfigGamerules.attackUndead.get() || entity.getMobType() != MobType.UNDEAD)
         && !entity.isVehicle()
         && (predicate == null || predicate.test(entity))
         && !((double)(entity.getHealth() / entity.getMaxHealth()) > 0.8)) {
         return ComplexMob.getEcoLevel(entity) < ComplexMob.getEcoLevel(this.mob)
               && this.mob.getClass() == entity.getClass()
               && this.mob instanceof ComplexMob attacker
               && entity instanceof ComplexMob defender
               && attacker.getVariant() == defender.getVariant()
            ? false
            : this.canAttack(entity, TargetingConditions.forCombat().range(this.getFollowDistance()));
      } else {
         return false;
      }
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
