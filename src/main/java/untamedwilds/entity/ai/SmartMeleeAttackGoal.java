package untamedwilds.entity.ai;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.util.EntityUtils;

public class SmartMeleeAttackGoal extends Goal {
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
   private final boolean doSkirmish;
   private final boolean isJumper;

   public SmartMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {
      this(entityIn, speedIn, useLongMemory, 0.0F, false, false);
   }

   public SmartMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory, float reach) {
      this(entityIn, speedIn, useLongMemory, reach, false, false);
   }

   public SmartMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory, float reach, boolean doSkirmish, boolean isJumper) {
      this.attacker = entityIn;
      this.speedTowardsTarget = speedIn;
      this.longMemory = useLongMemory;
      this.extraReach = reach;
      this.doSkirmish = doSkirmish;
      this.isJumper = isJumper;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      if (this.attacker.isBaby()) {
         return false;
      } else {
         if (this.attacker instanceof ComplexMobTerrestrial complex && complex.forceSleep > 0) {
            return false;
         }

         if (this.doSkirmish && this.attacker.getHealth() / this.attacker.getMaxHealth() <= 0.4F) {
            this.attacker.target = null;
            this.attacker.setTarget(null);
            this.attacker.setLastHurtByMob(null);
         }

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
      if (this.doSkirmish && this.attacker.getTarget() != null && this.attacker.getHealth() / this.attacker.getMaxHealth() <= 0.4F) {
         if (this.attacker.getTarget() instanceof PathfinderMob targetEntity) {
            Vec3 vec3d = DefaultRandomPos.getPosAway(targetEntity, 12, 4, new Vec3(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ()));
            if (vec3d != null) {
               this.attacker.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, this.speedTowardsTarget);
            }
         }

         this.attacker.setLastHurtByPlayer(null);
         this.attacker.setLastHurtByMob(null);
         this.attacker.target = null;
         this.attacker.setTarget(null);
         return false;
      } else {
         LivingEntity livingentity = this.attacker.getTarget();
         if (livingentity == null || this.attacker.getAirSupply() < 40 && !this.attacker.canBreatheUnderwater() || !livingentity.isAlive()) {
            return false;
         } else if (!this.longMemory) {
            return !this.attacker.getNavigation().isDone();
         } else {
            return !this.attacker.isWithinRestriction(livingentity.blockPosition())
               ? false
               : !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
         }
      }
   }

   public void start() {
      this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
      this.attacker.setAggressive(true);
      this.delayCounter = 0;
   }

   public void stop() {
      this.attacker.setTarget(null);
      this.attacker.setAggressive(false);
      this.attacker.getNavigation().stop();
   }

   public void tick() {
      LivingEntity livingentity = this.attacker.getTarget();
      this.attacker.getLookControl().setLookAt(Vec3.atCenterOf(livingentity.blockPosition()));
      double d0 = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getBoundingBox().minY, livingentity.getZ());
      this.delayCounter--;
      if (this.attacker.isInWater() && this.attacker.tickCount % 12 == 0 && livingentity.getBoundingBox().minY - 2.0 > this.attacker.getY()) {
         this.attacker.getJumpControl().jump();
      }

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

      BlockPos forwardNearPos = EntityUtils.getRelativeBlockPos(this.attacker, 1.2F, 0.0F);
      if (this.isJumper
         && this.attacker.onGround()
         && this.attacker.level().getBlockState(forwardNearPos.below()).isAir()
         && this.attacker.level().getBlockState(forwardNearPos.below(2)).isAir()
         && this.attacker.getSensing().hasLineOfSight(livingentity)) {
         BlockPos forwardFarPos = EntityUtils.getRelativeBlockPos(this.attacker, 5.0F, 0.0F);
         if (new Vec3((double)forwardFarPos.getX(), (double)forwardFarPos.getY(), (double)forwardFarPos.getZ())
               .distanceTo(livingentity.getPosition(0.0F))
            < this.attacker.getPosition(0.0F).distanceTo(livingentity.getPosition(0.0F))) {
            RandomSource rand = this.attacker.getRandom();

            for (int i = 0; i < 4; i++) {
               forwardFarPos.offset(rand.nextInt(2) - 1, rand.nextInt(2) - 1, rand.nextInt(2) - 1);
               if (this.attacker.getNavigation().isStableDestination(forwardFarPos)) {
                  Optional<Vec3> jump_vec = this.calculateOptimalJumpVector(this.attacker, Vec3.atCenterOf(forwardFarPos));
                  if (jump_vec.isPresent()) {
                     double d1 = jump_vec.get().length();
                     double d2 = 1.0
                        + d1
                        + (
                           this.attacker.hasEffect(MobEffects.JUMP)
                              ? (double)(0.1F * (float)(this.attacker.getEffect(MobEffects.JUMP).getAmplifier() + 1))
                              : 0.0
                        );
                     this.attacker.setDeltaMovement(jump_vec.get().x * d2 / d1, jump_vec.get().y, jump_vec.get().z * d2 / d1);
                     this.attacker.getNavigation().stop();
                     break;
                  }
               }
            }
         }
      }

      this.attackTick = Math.max(this.attackTick - 1, 0);
      this.checkAndPerformAttack(livingentity, d0);
   }

   protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
      double d0 = this.getAttackReachSqr(enemy);
      if (this.attacker.hasLineOfSight(enemy)
         && distToEnemySqr <= d0
         && (
            this.attackTick <= 0
               || this.attackTick <= 10 && this.attacker instanceof IAnimatedEntity animated && animated.getAnimation() == IAnimatedEntity.NO_ANIMATION
         )) {
         this.attackTick = 20;
         this.attacker.doHurtTarget(enemy);
      }
   }

   protected double getAttackReachSqr(LivingEntity attackTarget) {
      return (double)(this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth() + this.extraReach);
   }

   private Optional<Vec3> calculateOptimalJumpVector(PathfinderMob entityIn, Vec3 p_147658_) {
      Optional<Vec3> optional = Optional.empty();

      for (int i = 20; i < 55; i += 5) {
         Optional<Vec3> optional1 = this.calculateJumpVectorForAngle(entityIn, p_147658_, i);
         if (!optional.isPresent() || optional1.isPresent() && optional1.get().lengthSqr() < optional.get().lengthSqr()) {
            optional = optional1;
         }
      }

      return optional;
   }

   private Optional<Vec3> calculateJumpVectorForAngle(PathfinderMob entityIn, Vec3 p_147661_, int angleIn) {
      Vec3 vec3 = entityIn.position();
      Vec3 vec31 = new Vec3(p_147661_.x - vec3.x, 0.0, p_147661_.z - vec3.z).normalize().scale(0.5);
      p_147661_ = p_147661_.subtract(vec31);
      Vec3 vec32 = p_147661_.subtract(vec3);
      float f = (float)angleIn * (float) Math.PI / 180.0F;
      double d0 = Math.atan2(vec32.z, vec32.x);
      double d1 = vec32.subtract(0.0, vec32.y, 0.0).lengthSqr();
      double d2 = Math.sqrt(d1);
      double d3 = vec32.y;
      double d4 = Math.sin((double)(2.0F * f));
      double d6 = Math.pow(Math.cos((double)f), 2.0);
      double d7 = Math.sin((double)f);
      double d8 = Math.cos((double)f);
      double d9 = Math.sin(d0);
      double d10 = Math.cos(d0);
      double d11 = d1 * 0.08 / (d2 * d4 - 2.0 * d3 * d6);
      if (d11 < 0.0) {
         return Optional.empty();
      } else {
         double d12 = Math.sqrt(d11);
         double d13 = d12 * d8;
         double d14 = d12 * d7;
         int i = Mth.ceil(d2 / d13) * 2;
         double d15 = 0.0;
         Vec3 vec33 = null;

         for (int j = 0; j < i - 1; j++) {
            d15 += d2 / (double)i;
            double d16 = d7 / d8 * d15 - Math.pow(d15, 2.0) * 0.08 / (2.0 * d11 * Math.pow(d8, 2.0));
            double d17 = d15 * d10;
            double d18 = d15 * d9;
            Vec3 vec34 = new Vec3(vec3.x + d17, vec3.y + d16, vec3.z + d18);
            if (vec33 != null && !this.isClearTransition(entityIn, vec33, vec34)) {
               return Optional.empty();
            }

            vec33 = vec34;
         }

         return Optional.of(new Vec3(d13 * d10, d14, d13 * d9).scale(0.95F));
      }
   }

   private boolean isClearTransition(Mob p_147664_, Vec3 p_147665_, Vec3 p_147666_) {
      EntityDimensions entitydimensions = p_147664_.getDimensions(Pose.LONG_JUMPING);
      Vec3 vec3 = p_147666_.subtract(p_147665_);
      double d0 = (double)Math.min(entitydimensions.width, entitydimensions.height);
      int i = Mth.ceil(vec3.length() / d0);
      Vec3 vec31 = vec3.normalize();
      Vec3 vec32 = p_147665_;

      for (int j = 0; j < i; j++) {
         vec32 = j == i - 1 ? p_147666_ : vec32.add(vec31.scale(d0 * 0.9F));
         AABB aabb = entitydimensions.makeBoundingBox(vec32);
         if (!p_147664_.level().noCollision(p_147664_, aabb)) {
            return false;
         }
      }

      return true;
   }
}
