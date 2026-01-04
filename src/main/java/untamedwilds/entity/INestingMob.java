package untamedwilds.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

public interface INestingMob {
   boolean wantsToLayEggs();

   void setEggStatus(boolean var1);

   Block getNestType();

   boolean isValidNestBlock(BlockPos var1);

   default boolean isEggLayer() {
      return true;
   }
}
