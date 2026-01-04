package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.mammal.EntityBison;
import untamedwilds.entity.mammal.EntityRhino;
import untamedwilds.util.EntityUtils;

public class MeleeAttackCharger extends Goal {
   private final int executionChance;
   private final float speed;
   private final ComplexMobTerrestrial taskOwner;
   private double chargeX;
   private double chargeY;
   private double chargeZ;
   private int charge;

   public MeleeAttackCharger(ComplexMobTerrestrial entityIn, float speedIn, int chance) {
      this.taskOwner = entityIn;
      this.speed = speedIn;
      this.executionChance = chance;
      this.charge = 0;
      this.setFlags(EnumSet.of(Flag.TARGET, Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      LivingEntity chargeTarget = this.taskOwner.getTarget();
      if (!this.taskOwner.isBaby() && chargeTarget != null && this.taskOwner.onGround() && this.taskOwner.getRandom().nextInt(this.executionChance) == 0) {
         double distance = (double)this.taskOwner.distanceTo(chargeTarget);
         if (!(distance < 2.0) && !(distance > 24.0) && this.taskOwner.onGround()) {
            Vec3 chargePos = EntityUtils.getOvershootPath(this.taskOwner, chargeTarget, 10.0);
            boolean canSeeTargetFromDest = this.taskOwner.getSensing().hasLineOfSight(chargeTarget);
            if (canSeeTargetFromDest) {
               this.chargeX = chargePos.x;
               this.chargeY = chargePos.y;
               this.chargeZ = chargePos.z;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void start() {
      this.charge = 50;
      if (this.taskOwner instanceof EntityBison) {
         this.taskOwner.setAnimation(EntityBison.ATTACK_THREATEN);
      }

      if (this.taskOwner instanceof EntityRhino) {
         this.taskOwner.setAnimation(EntityRhino.ATTACK_THREATEN);
      }
   }

   public boolean canContinueToUse() {
      return this.charge > 0 || !this.taskOwner.getNavigation().isDone();
   }

   public void tick() {
      this.taskOwner.getLookControl().setLookAt(this.chargeX, this.chargeY - 1.0, this.chargeZ);
      if (this.charge > 0) {
         if (--this.charge == 0) {
            this.taskOwner.getNavigation().moveTo(this.chargeX, this.chargeY, this.chargeZ, (double)(this.speed * 1.2F));
         } else {
            this.taskOwner.setSprinting(true);
         }
      } else {
         AABB offset_box = this.taskOwner
            .getBoundingBox()
            .move(
               Math.cos(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2,
               0.0,
               Math.sin(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2
            );

         for (LivingEntity entityHit : this.taskOwner.level().getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), this.taskOwner, offset_box)) {
            if (!(entityHit instanceof EntityRhino) && !entityHit.equals(this.taskOwner) && this.taskOwner.hasLineOfSight(entityHit)) {
               this.taskOwner.doHurtTarget(entityHit);
            }
         }
      }
   }

   public void stop() {
      this.charge = 0;
      this.taskOwner.setSprinting(false);
   }
}
