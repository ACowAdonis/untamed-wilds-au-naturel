package untamedwilds.entity.ai.unique;

import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.fish.EntityShark;

public class SharkSwimmingGoal extends RandomSwimmingGoal {
   private final EntityShark taskOwner;

   public SharkSwimmingGoal(EntityShark entity) {
      super(entity, 1.0, 20);
      this.taskOwner = entity;
   }

   @Nullable
   protected Vec3 getPosition() {
      Vec3 vector3d = BehaviorUtils.getRandomSwimmablePos(this.taskOwner, 10, 7);
      if (this.taskOwner.isBottomDweller() && vector3d != null && this.taskOwner.level().canSeeSkyFromBelowWater(this.taskOwner.blockPosition())) {
         int offset = 5 + this.taskOwner.getRandom().nextInt(7) - 4;
         return new Vec3(
            vector3d.x(),
            (double)(this.taskOwner.level().getHeight(Types.OCEAN_FLOOR, (int)vector3d.x(), (int)vector3d.z()) + offset),
            vector3d.z()
         );
      } else {
         return vector3d;
      }
   }
}
