package untamedwilds.entity.ai;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMobAmphibious;

public class AmphibiousTransition extends RandomStrollGoal {
   private final ComplexMobAmphibious taskOwner;

   public AmphibiousTransition(ComplexMobAmphibious entityIn, double speedIn) {
      this(entityIn, speedIn, 120);
   }

   public AmphibiousTransition(ComplexMobAmphibious entityIn, double speedIn, int chance) {
      super(entityIn, speedIn, chance);
      this.taskOwner = entityIn;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
   }

   public boolean canUse() {
      return this.taskOwner.canMove() && this.taskOwner.getTarget() == null ? super.canUse() : false;
   }

   public boolean canContinueToUse() {
      return !this.taskOwner.getNavigation().isDone();
   }

   @Nullable
   protected Vec3 getPosition() {
      int rand = this.taskOwner.getRandom().nextInt(2);
      switch (rand) {
         case 0:
            if (this.taskOwner.wantsToBeInWater()) {
               return BehaviorUtils.getRandomSwimmablePos(this.taskOwner, 10, 7);
            }
            break;
         case 1:
            if (this.taskOwner.wantsToBeOnLand()) {
               return LandRandomPos.getPos(this.taskOwner, 10, 7);
            }
      }

      return null;
   }
}
