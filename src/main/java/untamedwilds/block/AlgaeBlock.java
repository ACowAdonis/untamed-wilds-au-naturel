package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.Nullable;

public class AlgaeBlock extends BushBlock implements BonemealableBlock, LiquidBlockContainer, IForgeShearable {
   protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);

   public AlgaeBlock(Properties properties) {
      super(properties);
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return SHAPE;
   }

   protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return state.isFaceSturdy(worldIn, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
      return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(context) : null;
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockState blockstate = super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      if (!blockstate.isAir()) {
         worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
      }

      return blockstate;
   }

   public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
      return true;
   }

   public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      BlockState blockstate = worldIn.getBlockState(pos);

      for (int k = 0; k < 3; k++) {
         BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, 1 - rand.nextInt(3), rand.nextInt(3) - 1);
         if (worldIn.isInWorldBounds(blockpos)
            && worldIn.isEmptyBlock(blockpos)
            && worldIn.getBlockState(blockpos).is(Blocks.WATER)
            && blockstate.canSurvive(worldIn, blockpos)) {
            worldIn.setBlock(blockpos, blockstate, 2);
         }
      }
   }

   public FluidState getFluidState(BlockState p_154537_) {
      return Fluids.WATER.getSource(false);
   }

   public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
      return false;
   }

   public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidIn) {
      return false;
   }
}
