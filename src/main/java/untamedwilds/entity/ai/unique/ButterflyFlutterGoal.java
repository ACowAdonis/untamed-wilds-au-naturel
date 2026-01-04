package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.arthropod.EntityButterfly;

public class ButterflyFlutterGoal extends Goal {
   protected EntityButterfly taskOwner;
   protected float speedIn;

   public ButterflyFlutterGoal(EntityButterfly entityIn, float speed) {
      this.taskOwner = entityIn;
      this.speedIn = speed;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      return !this.taskOwner.isResting() && this.taskOwner.getRandom().nextInt(3) == 0;
   }

   public boolean canContinueToUse() {
      return this.taskOwner.getNavigation().isInProgress() && !this.taskOwner.isResting();
   }

   public void start() {
      Vec3 vec3 = this.findPos();
      if (vec3 != null) {
         this.taskOwner.getNavigation().moveTo(this.taskOwner.getNavigation().createPath(BlockPos.containing(vec3), 1), 1.0);
      }
   }

   @Nullable
   private Vec3 findPos() {
      Vec3 vec3 = this.taskOwner.getViewVector(0.0F);
      Vec3 vec32 = HoverRandomPos.getPos(this.taskOwner, 8, 7, vec3.x, vec3.z, (float) (Math.PI / 2), 3, 1);
      return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(this.taskOwner, 8, 4, -2, vec3.x, vec3.z, (float) (Math.PI / 2));
   }

   public void tick() {
      BlockPos blockpos = this.taskOwner.blockPosition();
      RandomSource rand = this.taskOwner.getRandom();
      if (this.taskOwner.flight_counter <= 0 && rand.nextInt(40) == 0 && this.taskOwner.level().getBlockState(blockpos).is(BlockTags.FLOWERS)) {
         this.taskOwner.setResting(true);
      }
   }
}
