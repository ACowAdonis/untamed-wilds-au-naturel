package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public interface IPostGenUpdate {
   void updatePostGen(LevelAccessor var1, BlockPos var2);
}
