package untamedwilds.entity.ai.target;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.AABB;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;

public class HuntMobTarget<T extends LivingEntity> extends TargetGoal {
   protected final Class<T> targetClass;
   protected final HuntMobTarget.Sorter sorter;
   protected Predicate<? super T> targetEntitySelector;
   private final int threshold;
   private final boolean isCannibal;

   // Cache for entity searches to reduce expensive lookups
   private static final int CACHE_DURATION_TICKS = 20; // Cache results for 1 second
   private List<T> cachedTargets = null;
   private int cacheTickCounter = 0;

   public HuntMobTarget(ComplexMob creature, Class<T> classTarget, boolean checkSight, boolean isCannibal, Predicate<LivingEntity> targetSelector) {
      this(creature, classTarget, checkSight, 200, isCannibal, targetSelector);
   }

   public HuntMobTarget(
      ComplexMob creature, Class<T> classTarget, boolean checkSight, int hungerThreshold, boolean isCannibal, Predicate<LivingEntity> targetSelector
   ) {
      super(creature, checkSight, true);
      this.targetClass = classTarget;
      this.sorter = new HuntMobTarget.Sorter(creature);
      this.setFlags(EnumSet.of(Flag.TARGET));
      this.threshold = hungerThreshold;
      this.isCannibal = isCannibal;
      this.targetEntitySelector = entity -> this.isValidTarget(entity, targetSelector);
   }

   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      if (!(entity instanceof Creeper)
         && !entity.equals(this.mob)
         && ((Boolean)ConfigGamerules.attackUndead.get() || entity.getMobType() != MobType.UNDEAD)
         && (!(entity instanceof ComplexMob cmob) || cmob.canBeTargeted())
         && (predicate == null || predicate.test(entity))) {
         if (!this.isCannibal && this.mob.getClass() == entity.getClass() && this.mob instanceof ComplexMob attacker) {
            ComplexMob defender = (ComplexMob)entity;
            if (attacker.getVariant() == defender.getVariant()) {
               return false;
            }
         }

         return this.canAttack(entity, TargetingConditions.forCombat().range(this.getFollowDistance()));
      } else {
         return false;
      }
   }

   public boolean canUse() {
      if (!this.mob.isBaby() && !(this.mob.getHealth() < this.mob.getMaxHealth() / 3.0F)) {
         if (this.mob instanceof ComplexMob) {
            if (((ComplexMob)this.mob).huntingCooldown != 0) {
               return false;
            }

            if (this.mob instanceof ComplexMobTerrestrial tamed && (tamed.isTame() || tamed.getHunger() > this.threshold)) {
               return false;
            }
         }

         // Use cached results if available and not expired
         this.cacheTickCounter++;
         List<T> list;
         if (this.cachedTargets != null && this.cacheTickCounter < CACHE_DURATION_TICKS) {
            list = this.cachedTargets;
         } else {
            // Cache expired or empty, perform new search
            list = this.mob.level().getEntitiesOfClass(this.targetClass, this.getTargettableArea(this.getFollowDistance()), this.targetEntitySelector);
            this.cachedTargets = list;
            this.cacheTickCounter = 0;
         }

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

   AABB getTargettableArea(double targetDistance) {
      return this.mob.getBoundingBox().inflate(targetDistance, 4.0, targetDistance);
   }

   public void start() {
      if (!this.mob.getNavigation().isDone()) {
         this.mob.getNavigation().stop();
      }

      if (this.mob instanceof ComplexMob) {
         ((ComplexMob)this.mob).huntingCooldown = 6000;
      }

      this.mob.setTarget(this.targetMob);
      super.start();
   }

   public boolean canContinueToUse() {
      return super.canContinueToUse();
   }

   public static class Sorter implements Comparator<Entity> {
      private final Entity entity;

      private Sorter(Entity entityIn) {
         this.entity = entityIn;
      }

      public int compare(Entity entity_1, Entity entity_2) {
         double dist_1 = this.entity.distanceToSqr(entity_1);
         double dist_2 = this.entity.distanceToSqr(entity_2);
         if (dist_1 < dist_2) {
            return -1;
         } else {
            return dist_1 > dist_2 ? 1 : 0;
         }
      }
   }
}
