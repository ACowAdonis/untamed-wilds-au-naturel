package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import untamedwilds.block.blockentity.EggBlockEntity;

public class StrangeEggBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
   protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public StrangeEggBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, Boolean.FALSE));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = this.defaultBlockState();
      FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
      return (BlockState)blockstate.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collision) {
      return SHAPE;
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.below();
      return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
   }

   protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return !state.getCollisionShape(worldIn, pos).getFaceShape(Direction.UP).isEmpty();
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
      }

      if (!this.canSurvive(stateIn, worldIn, currentPos)) {
         worldIn.destroyBlock(currentPos, false);
      }

      return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return new EggBlockEntity(pos, state);
   }

   public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
      boolean canHatch = worldIn.isWaterAt(pos);
      if (worldIn.getBlockEntity(pos) instanceof EggBlockEntity egg) {
         egg.setCanSpawn(canHatch);
         egg.releaseOrCreateMob(worldIn);
      }
   }
}
