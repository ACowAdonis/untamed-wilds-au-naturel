package untamedwilds.entity.ai;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.ComplexMobTerrestrial;

public class GotoSleepGoal extends Goal {
   private final ComplexMobTerrestrial creature;
   protected BlockPos target;
   private final int executionChance;
   private final double speed;
   private final boolean usesHome;

   public GotoSleepGoal(ComplexMobTerrestrial entityIn, double speedIn) {
      this(entityIn, speedIn, 200, true);
   }

   public GotoSleepGoal(ComplexMobTerrestrial entityIn, double speedIn, int chance) {
      this(entityIn, speedIn, chance, true);
   }

   public GotoSleepGoal(ComplexMobTerrestrial entityIn, double speedIn, int chance, boolean usesHome) {
      this.creature = entityIn;
      this.speed = speedIn;
      this.executionChance = chance;
      this.usesHome = usesHome;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
   }

   public boolean canUse() {
      if (this.creature.getRandom().nextInt(this.executionChance) == 0
         && this.creature.forceSleep >= 0
         && this.creature.getTarget() == null
         && !this.creature.isSitting()
         && (!this.creature.isTame() || this.creature.getCommandInt() == 0)) {
         if (!this.creature.isSleeping() || this.creature.forceSleep > 0 && (this.creature instanceof ComplexMobAmphibious || !this.creature.isInWater())) {
            if (this.creature.getCommandInt() == 0
               && !this.creature.isActive()
               && this.creature.canMove()
               && (!this.creature.isInWater() || this.creature instanceof ComplexMobAmphibious)) {
               if (!this.isValidShelter(this.creature.blockPosition()) && this.usesHome) {
                  if (this.creature.getHome() == BlockPos.ZERO
                     || !this.canEasilyReach(this.creature.getHome())
                     || this.creature.distanceToSqr(this.creature.getHomeAsVec()) > 100000.0) {
                     this.creature.setHome(BlockPos.ZERO);
                     BlockPos pos = this.checkForNewHome();
                     if (pos == null) {
                        return false;
                     }

                     this.creature.setHome(pos);
                  }

                  this.target = this.creature.getHome();
                  return true;
               } else {
                  this.creature.setHome(this.creature.blockPosition());
                  this.creature.setSleeping(true);
                  return false;
               }
            } else {
               return false;
            }
         } else {
            this.creature.setSleeping(false);
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean canEasilyReach(BlockPos target) {
      Path path = this.creature.getNavigation().createPath(target, 0);
      if (path == null) {
         return false;
      } else {
         Node pathpoint = path.getEndNode();
         if (pathpoint == null) {
            return false;
         } else {
            int i = pathpoint.x - Mth.floor((float)target.getX());
            int j = pathpoint.z - Mth.floor((float)target.getZ());
            return (double)(i * i + j * j) <= 2.25;
         }
      }
   }

   public void start() {
      this.creature.getNavigation().moveTo((double)this.target.getX(), (double)this.target.getY(), (double)this.target.getZ(), this.speed);
   }

   public boolean canContinueToUse() {
      return !this.creature.getNavigation().isDone();
   }

   @Nullable
   public BlockPos checkForNewHome() {
      RandomSource random = this.creature.getRandom();
      BlockPos blockpos = this.creature.blockPosition();

      for (int i = 0; i < 10; i++) {
         BlockPos blockpos1 = blockpos.offset(random.nextInt(12) - 6, random.nextInt(4) - 2, random.nextInt(12) - 6);
         if (this.isValidShelter(blockpos1) && this.creature.getWalkTargetValue(blockpos1) < 0.0F) {
            return blockpos1;
         }
      }

      return null;
   }

   private boolean isValidShelter(BlockPos blockPos) {
      return !this.creature.level().canSeeSky(blockPos);
   }
}
