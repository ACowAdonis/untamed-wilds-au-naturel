package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import untamedwilds.entity.mammal.EntityBear;

public class PandaBreakBamboo extends MoveToBlockGoal {
   private final EntityBear taskOwner;
   private final int executionChance;
   private int searchCooldown;
   private boolean continueTask;

   public PandaBreakBamboo(EntityBear entityIn, int chance) {
      super(entityIn, 1.0, 10, 3);
      this.taskOwner = entityIn;
      this.executionChance = chance;
      this.searchCooldown = 100;
      this.continueTask = true;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if (!this.taskOwner.isPanda() || this.taskOwner.getHunger() > 40 || this.taskOwner.getTarget() != null) {
         return false;
      } else if (this.taskOwner.getRandom().nextInt(this.executionChance) != 0) {
         return false;
      } else {
         this.continueTask = true;
         return super.canUse();
      }
   }

   public void start() {
      super.start();
   }

   public void stop() {
   }

   public void tick() {
      this.taskOwner
         .getLookControl()
         .setLookAt(
            (double)this.blockPos.getX(),
            (double)((float)this.blockPos.getY() + 1.5F),
            (double)this.blockPos.getZ(),
            10.0F,
            (float)this.taskOwner.getMaxHeadXRot()
         );
      if (this.isReachedTarget()) {
         this.taskOwner
            .getLookControl()
            .setLookAt(
               (double)this.blockPos.getX(),
               (double)((float)this.blockPos.getY() + 1.5F),
               (double)this.blockPos.getZ(),
               10.0F,
               (float)this.taskOwner.getMaxHeadXRot()
            );
         this.taskOwner.getNavigation().stop();
         this.taskOwner.setSitting(true);
         this.searchCooldown--;
         if (this.searchCooldown == 0) {
            this.searchCooldown = 100;
            this.taskOwner.level().destroyBlock(this.blockPos.above(), false);
            this.taskOwner.setAnimation(EntityBear.ATTACK_SWIPE);
            this.taskOwner.addHunger(8);
            this.continueTask = false;
         }
      }

      super.tick();
   }

   protected boolean isValidTarget(LevelReader worldIn, BlockPos posIn) {
      if (worldIn.getBlockState(posIn).getBlock() == Blocks.BAMBOO && worldIn.getBlockState(posIn.above()).getBlock() == Blocks.BAMBOO) {
         this.blockPos = posIn;
         return true;
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      if (this.continueTask && this.tryTicks <= 1200) {
         return true;
      } else {
         this.taskOwner.setSitting(false);
         return false;
      }
   }
}
