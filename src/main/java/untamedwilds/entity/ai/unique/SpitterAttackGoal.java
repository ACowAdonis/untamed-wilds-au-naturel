package untamedwilds.entity.ai.unique;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.Objects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.relict.EntitySpitter;

public class SpitterAttackGoal extends Goal {
   protected final EntitySpitter attacker;
   protected int attackTick;
   private final double speedTowardsTarget;
   private final boolean longMemory;
   private Path path;
   private int delayCounter;
   private double targetX;
   private double targetY;
   private double targetZ;
   private final float extraReach;
   private long field_220720_k;
   private int failedPathFindingPenalty = 0;
   private final boolean canPenalize = false;

   public SpitterAttackGoal(EntitySpitter entityIn, double speedIn, boolean useLongMemory, float reach) {
      this.attacker = entityIn;
      this.speedTowardsTarget = speedIn;
      this.longMemory = useLongMemory;
      this.extraReach = reach;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      long i = this.attacker.level().getGameTime();
      if (i - this.field_220720_k < 20L) {
         return false;
      } else {
         this.field_220720_k = i;
         LivingEntity livingentity = this.attacker.getTarget();
         if (livingentity == null) {
            return false;
         } else if (!livingentity.isAlive()) {
            return false;
         } else {
            this.path = this.attacker.getNavigation().createPath(livingentity, 0);
            return this.path != null;
         }
      }
   }

   public boolean canContinueToUse() {
      LivingEntity livingentity = this.attacker.getTarget();
      if (livingentity == null || this.attacker.getAirSupply() < 40 && this.attacker.canDrownInFluidType(Fluids.WATER.getFluidType()) || !livingentity.isAlive()
         )
       {
         return false;
      } else if (!this.longMemory) {
         return !this.attacker.getNavigation().isDone();
      } else {
         return !this.attacker.isWithinRestriction(livingentity.blockPosition())
            ? false
            : !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
      }
   }

   public void start() {
      if (!this.attacker.isBaby()) {
         this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
      }

      this.attacker.setAggressive(true);
      this.delayCounter = 0;

      for (EntitySpitter entityanimal1 : this.attacker.level().getEntitiesOfClass(EntitySpitter.class, this.attacker.getBoundingBox().inflate(12.0, 8.0, 12.0))) {
         if (entityanimal1.getVariant() == this.attacker.getVariant()) {
            entityanimal1.setTarget(this.attacker.getTarget());
         }
      }
   }

   public void stop() {
      this.attacker.setTarget(null);
      this.attacker.setAggressive(false);
      this.attacker.getNavigation().stop();
   }

   public void tick() {
      LivingEntity livingentity = this.attacker.getTarget();
      if (livingentity != null) {
         this.attacker.getLookControl().setLookAt(Vec3.atCenterOf(livingentity.blockPosition()));
         double d0 = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ());
         this.delayCounter--;
         if (this.attacker.isInWater() && this.attacker.tickCount % 12 == 0 && livingentity.getBoundingBox().minY - 2.0 > this.attacker.getY()) {
            this.attacker.getJumpControl().jump();
         }

         if (this.attacker.getAnimation() == EntitySpitter.NO_ANIMATION
            && this.attacker.getSensing().hasLineOfSight(livingentity)
            && this.attacker.getRandom().nextInt(this.attacker.isBaby() ? 80 : 200) == 0
            && d0 > 12.0) {
            this.attacker.getLookControl().setLookAt(livingentity);
            this.attacker.setAnimation(EntitySpitter.ATTACK_SPIT);
         } else if (!this.attacker.isBaby()
            && (this.longMemory || this.attacker.getSensing().hasLineOfSight(livingentity))
            && this.delayCounter <= 0
            && (
               this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0
                  || livingentity.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0
                  || this.attacker.getRandom().nextFloat() < 0.05F
            )) {
            this.targetX = livingentity.getX();
            this.targetY = livingentity.getBoundingBox().minY;
            this.targetZ = livingentity.getZ();
            this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
            Objects.requireNonNull(this);
            if (d0 > 1024.0) {
               this.delayCounter += 10;
            } else if (d0 > 256.0) {
               this.delayCounter += 5;
            }

            if (!this.attacker.getNavigation().moveTo(livingentity, this.speedTowardsTarget)) {
               this.delayCounter += 15;
            }
         }

         this.attackTick = Math.max(this.attackTick - 1, 0);
         this.checkAndPerformAttack(livingentity, d0);
      }
   }

   protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
      double d0 = this.getAttackReachSqr(enemy);
      if (this.attacker.hasLineOfSight(enemy)
         && distToEnemySqr <= d0
         && (this.attackTick <= 0 || this.attackTick <= 10 && this.attacker.getAnimation() == IAnimatedEntity.NO_ANIMATION)) {
         this.attackTick = 20;
         this.attacker.doHurtTarget(enemy);
      }
   }

   protected double getAttackReachSqr(LivingEntity attackTarget) {
      return (double)(this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth() + this.extraReach);
   }
}
