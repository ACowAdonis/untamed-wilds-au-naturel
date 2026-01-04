package untamedwilds.entity.ai.target;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.ISpecies;

public class AngrySleeperTarget<T extends LivingEntity> extends TargetGoal {
   protected final Class<T> targetClass;
   protected final int targetChance;
   protected LivingEntity target;
   protected ComplexMobTerrestrial taskOwner;
   protected Predicate<? super T> targetEntitySelector;
   private int runningTicks;

   public AngrySleeperTarget(ComplexMobTerrestrial entityIn, Class<T> targetClassIn, boolean checkSight) {
      this(entityIn, targetClassIn, checkSight, false);
   }

   public AngrySleeperTarget(ComplexMobTerrestrial entityIn, Class<T> targetClassIn, boolean checkSight, boolean nearbyOnlyIn) {
      this(entityIn, targetClassIn, 4, checkSight, nearbyOnlyIn);
   }

   public AngrySleeperTarget(ComplexMobTerrestrial entityIn, Class<T> targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn) {
      super(entityIn, checkSight, nearbyOnlyIn);
      this.targetClass = targetClassIn;
      this.targetChance = targetChanceIn;
      this.setFlags(EnumSet.of(Flag.TARGET));
      this.runningTicks = 1000;
      this.taskOwner = entityIn;
      this.targetEntitySelector = entity -> {
         if (!(entity instanceof Creeper) && ComplexMob.getEcoLevel(this.taskOwner) <= ComplexMob.getEcoLevel(entity) * 2) {
            if (this.taskOwner.getClass() == entity.getClass() && this.taskOwner instanceof ISpecies && entity instanceof ISpecies) {
               ComplexMob attacker = this.taskOwner;
               ComplexMob defender = (ComplexMob)entity;
               if (attacker.getVariant() == defender.getVariant()) {
                  return false;
               }
            }

            if (entity instanceof Player player && (player.isSteppingCarefully() || player.isCreative() || player.isSpectator())) {
               return false;
            }

            return TargetingConditions.forCombat().test(this.taskOwner, entity) && this.canAttack(entity, TargetingConditions.DEFAULT);
         } else {
            return false;
         }
      };
   }

   public boolean canUse() {
      if ((Boolean)ConfigGamerules.angrySleepers.get()
         && !this.taskOwner.isBaby()
         && this.taskOwner.isSleeping()
         && !this.taskOwner.isTame()
         && this.taskOwner.forceSleep == 0) {
         List<T> list = this.mob.level().getEntitiesOfClass(this.targetClass, this.mob.getBoundingBox().inflate(6.0, 4.0, 6.0), this.targetEntitySelector);
         if (!list.isEmpty()) {
            LivingEntity player = list.get(0);
            this.taskOwner.setSleeping(false);
            this.target = player;
         }

         return true;
      } else {
         return false;
      }
   }

   public void start() {
      this.taskOwner.setTarget(this.target);
      this.taskOwner.forceSleep = -300;
      super.start();
   }

   public boolean canContinueToUse() {
      this.runningTicks--;
      return this.runningTicks < 1 ? false : super.canContinueToUse();
   }
}
