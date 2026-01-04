package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import untamedwilds.entity.ComplexMob;

public class SmartLookAtGoal extends Goal {
   protected final ComplexMob taskOwner;
   protected Entity closestEntity;
   protected final float maxDistance;
   private int lookTime;
   private final int chance;
   private double lookX;
   private double lookZ;
   protected final Class<? extends LivingEntity> watchedClass;
   protected final TargetingConditions SHOULD_LOOK;

   public SmartLookAtGoal(ComplexMob entityIn, Class<? extends LivingEntity> targetClass, float maxDistance) {
      this(entityIn, targetClass, maxDistance, 60);
   }

   public SmartLookAtGoal(ComplexMob entityIn, Class<? extends LivingEntity> targetClass, float maxDistance, int chanceIn) {
      this.taskOwner = entityIn;
      this.watchedClass = targetClass;
      this.maxDistance = maxDistance;
      this.chance = chanceIn;
      this.setFlags(EnumSet.of(Flag.LOOK));
      if (targetClass == Player.class) {
         this.SHOULD_LOOK = TargetingConditions.forNonCombat()
            .range((double)maxDistance)
            .selector(p_25531_ -> EntitySelector.notRiding(entityIn).test(p_25531_));
      } else {
         this.SHOULD_LOOK = TargetingConditions.forNonCombat().range((double)maxDistance);
      }
   }

   public boolean canUse() {
      if (this.taskOwner.isSleeping()) {
         return false;
      } else if (this.taskOwner.getRandom().nextInt(this.chance) != 0) {
         return false;
      } else {
         if (this.taskOwner.getTarget() != null) {
            this.closestEntity = this.taskOwner.getTarget();
         }

         if (this.watchedClass == Player.class) {
            this.closestEntity = this.taskOwner
               .level()
               .getNearestPlayer(
                  this.SHOULD_LOOK,
                  this.taskOwner,
                  this.taskOwner.getX(),
                  this.taskOwner.getY() + (double)this.taskOwner.getEyeHeight(),
                  this.taskOwner.getZ()
               );
         } else {
            this.closestEntity = this.taskOwner
               .level()
               .getNearestEntity(
                  this.watchedClass,
                  this.SHOULD_LOOK,
                  this.taskOwner,
                  this.taskOwner.getX(),
                  this.taskOwner.getY() + (double)this.taskOwner.getEyeHeight(),
                  this.taskOwner.getZ(),
                  this.taskOwner.getBoundingBox().inflate((double)this.maxDistance, 3.0, (double)this.maxDistance)
               );
         }

         return this.closestEntity != null || this.taskOwner.getRandom().nextInt(20) != 0;
      }
   }

   public boolean canContinueToUse() {
      return this.lookTime > 0;
   }

   public void start() {
      if (this.closestEntity == null) {
         double d0 = (Math.PI * 2) * this.taskOwner.getRandom().nextDouble();
         this.lookX = Math.cos(d0);
         this.lookZ = Math.sin(d0);
         this.lookTime = 20 + this.taskOwner.getRandom().nextInt(20);
      }

      this.lookTime = 40 + this.taskOwner.getRandom().nextInt(40);
   }

   public void stop() {
      this.closestEntity = null;
   }

   public void tick() {
      if (this.closestEntity == null) {
         this.taskOwner
            .getLookControl()
            .setLookAt(this.taskOwner.getX() + this.lookX, this.taskOwner.getY() + (double)this.taskOwner.getEyeHeight(), this.taskOwner.getZ());
      } else {
         this.taskOwner
            .getLookControl()
            .setLookAt(
               this.closestEntity.getX(),
               this.closestEntity.getY() + (double)this.closestEntity.getEyeHeight(),
               this.closestEntity.getZ(),
               20.0F,
               (float)this.taskOwner.getHeadRotSpeed()
            );
      }

      this.lookTime--;
   }
}
