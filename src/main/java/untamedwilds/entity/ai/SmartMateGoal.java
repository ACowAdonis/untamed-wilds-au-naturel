package untamedwilds.entity.ai;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INestingMob;

public class SmartMateGoal extends Goal {
   private final ComplexMob taskOwner;
   private final Level world;
   private final int executionChance;
   private final Class<? extends ComplexMob> mateClass;
   private ComplexMob targetMate;
   private int spawnBabyDelay;
   private final double moveSpeed;

   public SmartMateGoal(ComplexMob entityIn, double speedIn) {
      this(entityIn, speedIn, 120, (Class<? extends ComplexMob>)entityIn.getClass());
   }

   private SmartMateGoal(ComplexMob entityIn, double speedIn, int chance, Class<? extends ComplexMob> mateClass) {
      this.taskOwner = entityIn;
      this.world = entityIn.level();
      this.mateClass = mateClass;
      this.executionChance = chance;
      this.moveSpeed = speedIn;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      if (this.taskOwner.isInLove() && this.taskOwner.getAge() == 0 && this.taskOwner.getRandom().nextInt(this.executionChance) == 0) {
         this.targetMate = this.getNearbyMate();
         return this.targetMate != null;
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      return this.targetMate.isAlive() && this.taskOwner.getAge() == 0 && this.spawnBabyDelay < 200;
   }

   public void stop() {
      this.targetMate = null;
      this.spawnBabyDelay = 0;
   }

   public void tick() {
      this.taskOwner.getLookControl().setLookAt(this.targetMate, 10.0F, (float)this.taskOwner.getHeadRotSpeed());
      this.taskOwner.getNavigation().moveTo(this.targetMate.getX(), this.targetMate.getY(), this.targetMate.getZ(), this.moveSpeed);
      this.spawnBabyDelay++;
      if (this.spawnBabyDelay >= 100 && this.taskOwner.distanceToSqr(this.targetMate) < 9.0) {
         this.taskOwner.resetLove();
         this.targetMate.resetLove();
         if (this.world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.world
               .addFreshEntity(
                  new ExperienceOrb(
                     this.world, this.taskOwner.getX(), this.taskOwner.getY(), this.taskOwner.getZ(), this.taskOwner.getRandom().nextInt(7) + 1
                  )
               );
         }

         this.taskOwner.setAge(this.taskOwner.getPregnancyTime());
         this.targetMate.setAge(this.taskOwner.getPregnancyTime());
         if (this.taskOwner instanceof INestingMob nestingMob && nestingMob.isEggLayer()) {
            if (this.taskOwner.isMale() && (Boolean)ConfigGamerules.genderedBreeding.get()) {
               ((INestingMob)this.targetMate).setEggStatus(true);
            } else {
               ((INestingMob)this.taskOwner).setEggStatus(true);
            }

            return;
         }

         if ((Boolean)ConfigGamerules.easyBreeding.get()) {
            if (this.taskOwner.isMale() && (Boolean)ConfigGamerules.genderedBreeding.get()) {
               this.targetMate.breed();
            } else {
               this.taskOwner.breed();
            }
         }
      }
   }

   private ComplexMob getNearbyMate() {
      List<? extends ComplexMob> list = this.world.getEntitiesOfClass(this.mateClass, this.taskOwner.getBoundingBox().inflate(8.0));
      list.remove(this.taskOwner);
      double d0 = Double.MAX_VALUE;
      ComplexMob entityanimal = null;

      for (ComplexMob potentialMates : list) {
         if (this.canMateWith(this.taskOwner, potentialMates) && this.taskOwner.distanceToSqr(potentialMates) < d0) {
            entityanimal = potentialMates;
            d0 = this.taskOwner.distanceToSqr(potentialMates);
         }
      }

      return entityanimal;
   }

   private boolean canMateWith(ComplexMob father, ComplexMob mother) {
      if ((!(Boolean)ConfigGamerules.genderedBreeding.get() || father.getGender() != mother.getGender()) && father.getVariant() == mother.getVariant()) {
         if (father instanceof INestingMob nesting && (nesting.wantsToLayEggs() || ((INestingMob)mother).wantsToLayEggs())) {
            return false;
         }

         return (Boolean)ConfigGamerules.playerBreeding.get() || father.wantsToBreed() && mother.wantsToBreed();
      } else {
         return false;
      }
   }
}
