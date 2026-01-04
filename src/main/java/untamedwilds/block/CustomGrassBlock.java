package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomGrassBlock extends FlowerBlock implements BonemealableBlock {
   protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

   public CustomGrassBlock(MobEffect p_53512_, int p_53513_, Properties p_53514_) {
      super(() -> p_53512_, p_53513_, p_53514_);
   }

   public VoxelShape getShape(BlockState p_53517_, BlockGetter p_53518_, BlockPos p_53519_, CollisionContext p_53520_) {
      Vec3 vec3 = p_53517_.getOffset(p_53518_, p_53519_);
      return SHAPE.move(vec3.x, vec3.y, vec3.z);
   }

   public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean p_153800_) {
      return p_153797_.getBlockState(p_153798_.above()).isAir();
   }

   public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_) {
      return true;
   }

   public void performBonemeal(ServerLevel p_57298_, RandomSource p_57299_, BlockPos p_57300_, BlockState p_57301_) {
      popResource(p_57298_, p_57300_, new ItemStack(this));
   }
}
