package untamedwilds.block;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EpyphitePlantBlock extends HorizontalDirectionalBlock {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
   private static final VoxelShape WEST_AABB = Block.box(1.0, 1.0, 15.0, 15.0, 15.0, 16.0);
   private static final VoxelShape EAST_AABB = Block.box(1.0, 1.0, 0.0, 15.0, 15.0, 1.0);
   private static final VoxelShape NORTH_AABB = Block.box(15.0, 1.0, 1.0, 16.0, 15.0, 15.0);
   private static final VoxelShape SOUTH_AABB = Block.box(0.0, 1.0, 1.0, 1.0, 15.0, 15.0);

   public EpyphitePlantBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING});
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return switch ((Direction)state.getValue(FACING)) {
         case NORTH -> NORTH_AABB;
         case SOUTH -> SOUTH_AABB;
         case WEST -> WEST_AABB;
         case EAST -> EAST_AABB;
         default -> NORTH_AABB;
      };
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      Direction direction = (Direction)state.getValue(FACING);
      BlockPos blockpos = pos.relative(direction.getOpposite());
      BlockState blockstate = worldIn.getBlockState(blockpos);
      return blockstate.isFaceSturdy(worldIn, blockpos, direction);
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = super.getStateForPlacement(context);
      LevelReader iworldreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      Direction[] adirection = context.getNearestLookingDirections();

      for (Direction direction : adirection) {
         if (direction.getAxis().isHorizontal()) {
            blockstate = (BlockState)blockstate.setValue(FACING, direction.getOpposite());
            if (blockstate.canSurvive(iworldreader, blockpos)) {
               return blockstate;
            }
         }
      }

      return null;
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      return facing.getOpposite() == stateIn.getValue(FACING) && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
   }
}
