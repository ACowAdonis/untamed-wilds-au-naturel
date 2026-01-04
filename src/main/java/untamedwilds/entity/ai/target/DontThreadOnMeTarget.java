package untamedwilds.entity.ai.target;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;

public class DontThreadOnMeTarget<T extends LivingEntity> extends TargetGoal {
   protected final Class<T> targetClass;
   protected Predicate<T> targetEntitySelector;
   private int runningTicks;

   public DontThreadOnMeTarget(Mob entityIn, Class<T> targetClassIn, boolean checkSight) {
      this(entityIn, targetClassIn, checkSight, false);
   }

   public DontThreadOnMeTarget(Mob entityIn, Class<T> targetClassIn, boolean checkSight, boolean nearbyOnlyIn) {
      super(entityIn, checkSight, nearbyOnlyIn);
      this.targetClass = targetClassIn;
      this.setFlags(EnumSet.of(Flag.TARGET));
      this.targetEntitySelector = entity -> this.isValidTarget(entity, null);
   }

   protected boolean isValidTarget(LivingEntity entity, @Nullable Predicate<LivingEntity> predicate) {
      if (!(entity instanceof Creeper)
         && !entity.equals(this.mob)
         && ((Boolean)ConfigGamerules.attackUndead.get() || entity.getMobType() != MobType.UNDEAD)
         && (predicate == null || predicate.test(entity))) {
         return this.mob.getClass() == entity.getClass()
               && this.mob instanceof ComplexMob attacker
               && entity instanceof ComplexMob defender
               && attacker.getVariant() == defender.getVariant()
            ? false
            : entity.getBoundingBox().intersects(this.mob.getBoundingBox()) && this.canAttack(entity, TargetingConditions.forCombat().range(this.getFollowDistance()));
      } else {
         return false;
      }
   }

   public boolean canUse() {
      if (!(Boolean)ConfigGamerules.contactAgression.get()) {
         return false;
      } else {
         List<T> list = this.mob.level().getEntitiesOfClass(this.targetClass, this.mob.getBoundingBox().inflate(1.0), this.targetEntitySelector);
         if (list.isEmpty()) {
            return false;
         } else {
            this.targetMob = list.get(0);
            return true;
         }
      }
   }

   public void start() {
      this.mob.setTarget(this.targetMob);
      this.runningTicks = 60;
      super.start();
   }

   public boolean canContinueToUse() {
      this.runningTicks--;
      if (this.runningTicks < 1) {
         this.mob.setTarget(null);
         return false;
      } else {
         return super.canContinueToUse();
      }
   }
}
