package untamedwilds.entity.ai.target;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;

public class ProtectChildrenTarget<T extends LivingEntity> extends HuntMobTarget<T> {
   private Mob protectTarget;

   // Cache for children search to reduce expensive lookups
   private static final int CHILD_CACHE_DURATION_TICKS = 40; // Cache children search for 2 seconds
   private List<? extends Mob> cachedChildren = null;
   private int childCacheTickCounter = 0;

   public ProtectChildrenTarget(ComplexMob creature, Class<T> classTarget, boolean checkSight, Predicate<LivingEntity> targetSelector) {
      super(creature, classTarget, checkSight, 200, false, targetSelector);
   }

   @Override
   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      if (!(entity instanceof Creeper)
         && !entity.equals(this.mob)
         && ((Boolean)ConfigGamerules.attackUndead.get() || entity.getMobType() != MobType.UNDEAD)
         && (predicate == null || predicate.test(entity))) {
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
      if (this.mob.isBaby()) {
         return false;
      } else {
         if (this.mob instanceof TamableAnimal tamable && tamable.isTame()) {
            return false;
         }

         if (this.mob instanceof ComplexMob temp) {
            // Use cached children search if available and not expired
            this.childCacheTickCounter++;
            List<? extends Mob> children;
            if (this.cachedChildren != null && this.childCacheTickCounter < CHILD_CACHE_DURATION_TICKS) {
               children = this.cachedChildren;
            } else {
               // Cache expired or empty, perform new search
               children = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0, 4.0, 8.0));
               this.cachedChildren = children;
               this.childCacheTickCounter = 0;
            }

            for (Mob child : children) {
               if (child.isBaby() && ((ComplexMob)child).getVariant() == temp.getVariant()) {
                  this.protectTarget = child;
                  // Use parent class caching for threat search via super.canUse() logic
                  List<T> list = this.mob.level().getEntitiesOfClass(this.targetClass, this.getTargettableArea(this.getFollowDistance()), this.targetEntitySelector);
                  if (list.isEmpty()) {
                     return false;
                  }

                  list.sort(this.sorter);
                  this.targetMob = list.get(0);
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public boolean canContinueToUse() {
      if (this.protectTarget.distanceTo(this.mob) > 12.0F) {
         this.mob.setTarget(null);
         this.targetMob = null;
         this.mob.getNavigation().moveTo(this.protectTarget, 1.0);
         return false;
      } else {
         return super.canContinueToUse();
      }
   }

   protected double getFollowDistance() {
      return super.getFollowDistance() * 0.5;
   }
}
