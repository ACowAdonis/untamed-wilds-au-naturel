package untamedwilds.entity.ai.target;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.AABB;
import untamedwilds.entity.ComplexMob;

public class GuardPositionTarget<T extends LivingEntity> extends TargetGoal {
   protected BlockPos guardPos;
   protected final Class<T> targetClass;
   protected final GuardPositionTarget.Sorter sorter;
   protected Predicate<? super T> targetEntitySelector;
   protected int tryTicks;
   private int maxStayTicks;
   private boolean reachedTarget;

   public GuardPositionTarget(ComplexMob creature, Class<T> classTarget, boolean checkSight, Predicate<LivingEntity> targetSelector) {
      super(creature, checkSight, true);
      this.targetClass = classTarget;
      this.sorter = new GuardPositionTarget.Sorter(creature);
      this.setFlags(EnumSet.of(Flag.TARGET, Flag.MOVE, Flag.JUMP));
      this.targetEntitySelector = entity -> this.isValidTarget(entity, targetSelector);
   }

   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      if (!(entity instanceof Creeper) && !entity.equals(this.mob) && (predicate == null || predicate.test(entity))) {
         if (entity instanceof TamableAnimal tamable && tamable.isTame()) {
            if (this.mob instanceof TamableAnimal tamed && tamable.getOwner() != null && tamable.getOwner().equals(tamed.getOwner())) {
               return false;
            }

            if (this.mob.getTeam() != null && this.mob.getTeam().isAlliedTo(tamable.getTeam())) {
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
         if (this.mob instanceof ComplexMob cmob) {
            if (!cmob.isTame() || cmob.getCommandInt() != 3) {
               return false;
            }

            if (!cmob.isSitting()) {
               cmob.setSitting(true);
            }
         }

         if (this.guardPos == null) {
            this.guardPos = this.mob.blockPosition();
         }

         List<T> list = this.mob.level().getEntitiesOfClass(this.targetClass, this.getTargettableArea(this.getFollowDistance()), this.targetEntitySelector);
         if (!list.isEmpty()) {
            list.sort(this.sorter);
            this.targetMob = list.get(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      if (!super.canContinueToUse()) {
         if (this.reachedTarget) {
            if (this.mob instanceof ComplexMob cmob) {
               cmob.getNavigation().stop();
               cmob.setSitting(true);
            }

            return false;
         } else {
            return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= 1200;
         }
      } else {
         return true;
      }
   }

   AABB getTargettableArea(double targetDistance) {
      return this.mob.getBoundingBox().inflate(targetDistance, 4.0, targetDistance);
   }

   public void start() {
      if (!this.mob.getNavigation().isDone()) {
         this.mob.getNavigation().stop();
      }

      this.mob.setTarget(this.targetMob);
      this.tryTicks = 0;
      this.maxStayTicks = this.mob.getRandom().nextInt(this.mob.getRandom().nextInt(1200) + 1200) + 1200;
      this.reachedTarget = false;
      super.start();
   }

   public double acceptedDistance() {
      return 2.0;
   }

   public boolean requiresUpdateEveryTick() {
      return true;
   }

   private boolean reaquireTarget() {
      List<T> list = this.mob.level().getEntitiesOfClass(this.targetClass, this.getTargettableArea(this.getFollowDistance()), this.targetEntitySelector);
      if (!list.isEmpty()) {
         list.sort(this.sorter);
         this.targetMob = list.get(0);
         return true;
      } else {
         return false;
      }
   }

   public void tick() {
      if (this.mob.getTarget() != null && !this.mob.getTarget().isDeadOrDying()) {
         super.tick();
      } else if (!this.reaquireTarget()) {
         BlockPos blockpos = this.guardPos.above();
         ((ServerLevel)this.mob.level())
            .sendParticles(ParticleTypes.UNDERWATER, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 2, 0.0, 0.0, 0.0, 0.0);
         if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
            this.tryTicks++;
            if (this.shouldRecalculatePath()) {
               this.mob
                  .getNavigation()
                  .moveTo((double)((float)blockpos.getX()) + 0.5, (double)blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5, 1.0);
            }
         } else {
            this.reachedTarget = true;
            this.tryTicks--;
         }
      }
   }

   public boolean shouldRecalculatePath() {
      return this.tryTicks % 40 == 0;
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
