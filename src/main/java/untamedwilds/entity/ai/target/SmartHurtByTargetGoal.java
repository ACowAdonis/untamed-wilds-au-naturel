package untamedwilds.entity.ai.target;

import java.util.EnumSet;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class SmartHurtByTargetGoal extends HurtByTargetGoal {
   public SmartHurtByTargetGoal(PathfinderMob p_26039_, Class<?>... p_26040_) {
      super(p_26039_, p_26040_);
      this.setFlags(EnumSet.of(Flag.TARGET));
   }

   public void start() {
      if (this.mob.isBaby()) {
         this.alertOthers();
      }

      super.start();
   }
}
