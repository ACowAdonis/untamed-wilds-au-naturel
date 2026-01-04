package untamedwilds.entity.ai;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import untamedwilds.entity.ComplexMobTerrestrial;

public class RaidCropsGoal extends MoveToBlockGoal {
   private final ComplexMobTerrestrial taskOwner;

   public RaidCropsGoal(ComplexMobTerrestrial entityIn) {
      super(entityIn, 1.0, 16, 4);
      this.taskOwner = entityIn;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if (this.taskOwner.isTame() || !ForgeEventFactory.getMobGriefingEvent(this.taskOwner.level(), this.taskOwner)) {
         return false;
      } else if (this.taskOwner.getHunger() > 80 || this.taskOwner.getTarget() != null) {
         return false;
      } else {
         return this.taskOwner.getRandom().nextInt(120) != 0 ? false : this.findNearestBlock();
      }
   }

   public void tick() {
      if (this.taskOwner.distanceToSqr((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ()) < 4.0) {
         BlockState block = this.taskOwner.level().getBlockState(this.blockPos);
         if (block.getBlock() instanceof CropBlock) {
            Builder lootparams$builder = new Builder((ServerLevel)this.taskOwner.level())
               .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.blockPos))
               .withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
            List<ItemStack> drops = block.getDrops(lootparams$builder);
            if (!drops.isEmpty()) {
               this.taskOwner.addHunger(Math.max(drops.size() * 10, 10));
               this.taskOwner.level().destroyBlock(this.blockPos, false);
               this.taskOwner.getNavigation().stop();
            }
         }
      }
   }

   protected boolean isValidTarget(LevelReader worldIn, BlockPos posIn) {
      return worldIn.getBlockState(posIn).is(BlockTags.CROPS);
   }

   public boolean canContinueToUse() {
      return this.taskOwner.getHunger() <= 80 && !this.taskOwner.level().isEmptyBlock(this.blockPos) ? super.canContinueToUse() : false;
   }
}
