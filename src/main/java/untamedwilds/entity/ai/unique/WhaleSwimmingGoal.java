package untamedwilds.entity.ai.unique;

import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.mammal.EntityBaleenWhale;

public class WhaleSwimmingGoal extends RandomSwimmingGoal {
   private final EntityBaleenWhale taskOwner;

   public WhaleSwimmingGoal(EntityBaleenWhale entity) {
      super(entity, 1.0, 20);
      this.taskOwner = entity;
   }

   @Nullable
   protected Vec3 getPosition() {
      Vec3 vector3d = BehaviorUtils.getRandomSwimmablePos(this.taskOwner, 10, 7);
      int offset = 5 + this.taskOwner.getRandom().nextInt(7) - 4;
      if (vector3d != null) {
         return this.taskOwner.level().canSeeSky(this.taskOwner.blockPosition())
            ? new Vec3(
               vector3d.x(),
               (double)(this.taskOwner.level().getHeight(Types.WORLD_SURFACE, (int)vector3d.x(), (int)vector3d.z()) - offset),
               vector3d.z()
            )
            : vector3d.add(0.0, (double)(-offset), 0.0);
      } else {
         return null;
      }
   }
}
