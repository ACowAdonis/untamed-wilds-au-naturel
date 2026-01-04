package untamedwilds.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import untamedwilds.block.blockentity.CritterBurrowBlockEntity;

public class CritterBurrowBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
   protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public CritterBurrowBlock(Properties properties) {
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
      return new CritterBurrowBlockEntity(pos, state);
   }

   public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
      return 10 + randomSource.nextInt(10);
   }

   public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
      if (worldIn.getBlockEntity(pos) instanceof CritterBurrowBlockEntity burrow) {
         burrow.releaseOrCreateMob(worldIn);
      }
   }

   public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
      if (!worldIn.isClientSide && !hand.equals(InteractionHand.OFF_HAND)) {
         CritterBurrowBlockEntity te = (CritterBurrowBlockEntity)worldIn.getBlockEntity(pos);
         if (!playerIn.isCreative() || te == null) {
            playerIn.sendSystemMessage(Component.translatable("block.burrow.state", new Object[]{te.getEntityType().getDescription().getString()}));
         } else if (playerIn.isSteppingCarefully()) {
            te.releaseOrCreateMob((ServerLevel)worldIn);
         } else {
            playerIn.sendSystemMessage(Component.translatable("This burrow contains " + te.getEntityType().getDescriptionId()).withStyle(ChatFormatting.ITALIC));
            playerIn.sendSystemMessage(Component.translatable("The variant is " + te.getVariant()).withStyle(ChatFormatting.ITALIC));
            playerIn.sendSystemMessage(
               Component.translatable(
                     "There are "
                        + (te.getInhabitants().size() + te.getCount())
                        + " mobs inside the burrow ("
                        + te.getInhabitants().size()
                        + " stored, and "
                        + te.getCount()
                        + " to be spawned)"
                  )
                  .withStyle(ChatFormatting.ITALIC)
            );
         }

         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }
}
