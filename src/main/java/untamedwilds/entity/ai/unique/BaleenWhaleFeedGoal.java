package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.mammal.EntityBaleenWhale;

public class BaleenWhaleFeedGoal extends Goal {
   private final EntityBaleenWhale taskOwner;
   private final int chance;
   private BlockPos targetPos;
   private int eatingCounter;

   public BaleenWhaleFeedGoal(EntityBaleenWhale entityIn, int chance) {
      this.taskOwner = entityIn;
      this.chance = chance;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      if (this.taskOwner.getRandom().nextInt(this.chance) == 0) {
         this.targetPos = this.getPosition();
         return this.targetPos != null;
      } else {
         return false;
      }
   }

   @Nullable
   protected BlockPos getPosition() {
      Vec3 vector3d = BehaviorUtils.getRandomSwimmablePos(this.taskOwner, 20, 7);
      if (vector3d != null && this.taskOwner.level().canSeeSky(this.taskOwner.blockPosition())) {
         int offset = 5 + this.taskOwner.getRandom().nextInt(7) - 4;
         return BlockPos.containing(
            vector3d.x(),
            (double)(this.taskOwner.level().getHeight(Types.OCEAN_FLOOR, (int)vector3d.x(), (int)vector3d.z()) + offset),
            vector3d.z()
         );
      } else {
         return vector3d != null ? BlockPos.containing(vector3d) : null;
      }
   }

   public boolean canContinueToUse() {
      return !this.taskOwner.getNavigation().isDone() && this.eatingCounter != 0;
   }

   public void start() {
      this.eatingCounter = 200;
      this.taskOwner.setFeeding(true);
      this.taskOwner
         .getNavigation()
         .moveTo((double)this.targetPos.getX() + 0.5, (double)this.targetPos.above().getY(), (double)this.targetPos.getZ() + 0.5, 1.5);
   }

   public void stop() {
      if (this.taskOwner.isFeeding()) {
         this.taskOwner.setFeeding(false);
      }

      super.stop();
   }

   public void tick() {
      if (this.eatingCounter > 0) {
         this.eatingCounter--;
      }
   }

   public boolean isInterruptable() {
      return false;
   }
}
