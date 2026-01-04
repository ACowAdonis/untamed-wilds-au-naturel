package untamedwilds.entity.ai;

import java.util.EnumSet;
import java.util.Objects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

public class MeleeAttackCircle extends Goal {
   protected final PathfinderMob attacker;
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
   private byte invert = 1;

   public MeleeAttackCircle(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {
      this(entityIn, speedIn, useLongMemory, 0.0F);
   }

   public MeleeAttackCircle(PathfinderMob entityIn, double speedIn, boolean useLongMemory, float reach) {
      this.attacker = entityIn;
      this.speedTowardsTarget = speedIn;
      this.longMemory = useLongMemory;
      this.extraReach = reach;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.TARGET, Flag.LOOK));
   }

   public boolean canUse() {
      if (this.attacker.isBaby()) {
         return false;
      } else {
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
   }

   public boolean canContinueToUse() {
      LivingEntity livingentity = this.attacker.getTarget();
      if (livingentity == null || this.attacker.getAirSupply() < 40 && !this.attacker.canBreatheUnderwater() || !livingentity.isAlive()) {
         return false;
      } else {
         return !this.longMemory
            ? !this.attacker.getNavigation().isDone()
            : !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
      }
   }

   public void start() {
      this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
      this.attacker.setAggressive(true);
      this.delayCounter = 0;
   }

   public void stop() {
      LivingEntity livingentity = this.attacker.getTarget();
      if (livingentity == null || !TargetingConditions.forCombat().test(this.attacker, livingentity)) {
         this.attacker.setTarget(null);
      }

      this.attacker.setAggressive(false);
      this.attacker.getNavigation().stop();
   }

   public void tick() {
      LivingEntity livingentity = this.attacker.getTarget();
      double d0 = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ());
      this.delayCounter--;
      if ((this.longMemory || this.attacker.getSensing().hasLineOfSight(livingentity))
         && this.delayCounter <= 0
         && (
            this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0
               || livingentity.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0
               || this.attacker.getRandom().nextFloat() < 0.05F
         )) {
         this.targetX = livingentity.getX();
         this.targetY = livingentity.getBoundingBox().minY;
         this.targetZ = livingentity.getZ();
         if (this.attacker.tickCount % 400 == 0) {
            this.invert *= -1;
         }

         if (this.attacker.tickCount % 400 > 80) {
            if (this.attacker.getTarget() != null && this.attacker.tickCount % 10 == 0) {
               double x = this.attacker.getTarget().getX() + Math.cos((double)((float)this.attacker.tickCount / 60.0F)) * 10.0 * (double)this.invert;
               double z = this.attacker.getTarget().getZ() + Math.sin((double)((float)this.attacker.tickCount / 60.0F)) * 10.0 * (double)this.invert;
               this.attacker.getNavigation().moveTo(x, this.attacker.getTarget().getY(), z, 2.3F);
            }
         } else {
            this.attacker.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
            Objects.requireNonNull(this);
            if (d0 > 1024.0) {
               this.delayCounter += 10;
            } else if (d0 > 256.0) {
               this.delayCounter += 5;
            }

            if (!this.attacker.getNavigation().moveTo(livingentity, this.speedTowardsTarget * 1.5)) {
               this.delayCounter += 15;
            }
         }
      }

      this.attackTick = Math.max(this.attackTick - 1, 0);
      this.checkAndPerformAttack(livingentity, this.attacker.distanceToSqr(this.targetX, this.targetY, this.targetZ));
   }

   protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
      double d0 = this.getAttackReachSqr(enemy);
      if (this.attacker.hasLineOfSight(enemy) && distToEnemySqr <= d0 && this.attackTick <= 0) {
         this.attackTick = 20;
         this.attacker.doHurtTarget(enemy);
         this.attacker.getLookControl().setLookAt(enemy, 30.0F, 30.0F);
      }
   }

   protected double getAttackReachSqr(LivingEntity attackTarget) {
      return (double)(this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth() + this.extraReach);
   }
}
