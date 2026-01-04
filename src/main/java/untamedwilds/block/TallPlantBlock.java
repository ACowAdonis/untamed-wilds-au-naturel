package untamedwilds.block;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModTags;

public class TallPlantBlock extends Block implements BonemealableBlock, IPostGenUpdate {
   protected static final VoxelShape SHAPE_TRUNK = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
   protected static final VoxelShape SHAPE_STEM = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
   protected static final VoxelShape SHAPE_FLOWERING = Block.box(3.0, 0.0, 3.0, 13.0, 14.0, 13.0);
   public static final IntegerProperty PROPERTY_AGE = BlockStateProperties.AGE_5;
   public static final IntegerProperty PROPERTY_STAGE = BlockStateProperties.STAGE;

   public TallPlantBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PROPERTY_AGE, 0)).setValue(PROPERTY_STAGE, 0));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{PROPERTY_AGE, PROPERTY_STAGE});
   }

   protected ItemLike getSeedsItem() {
      return (ItemLike)ModItems.SEED_ZIMBABWE_ALOE.get();
   }

   public ItemStack getCloneItemStack(BlockGetter p_60261_, BlockPos p_60262_, BlockState p_60263_) {
      return new ItemStack(this.getSeedsItem());
   }

   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      if ((Integer)state.getValue(PROPERTY_STAGE) == 1) {
         return SHAPE_FLOWERING;
      } else {
         Vec3 vector3d = state.getOffset(worldIn, pos);
         VoxelShape shape = state.getValue(PROPERTY_AGE) == 1 && state.getValue(PROPERTY_STAGE) == 1 ? SHAPE_STEM : SHAPE_TRUNK;
         return shape.move(vector3d.x, vector3d.y, vector3d.z);
      }
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(PROPERTY_STAGE) == 1 ? Shapes.empty() : this.getShape(state, worldIn, pos, context);
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
      return blockstate.is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON) ? (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0) : null;
   }

   public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
      if (!state.canSurvive(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      }
   }

   public boolean isRandomlyTicking(BlockState state) {
      return (Integer)state.getValue(PROPERTY_STAGE) == 0;
   }

   public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
      if ((Integer)state.getValue(PROPERTY_STAGE) == 0 && random.nextInt(8) == 0 && worldIn.isEmptyBlock(pos.above()) && worldIn.getLightEmission(pos.above()) >= 9) {
         int i = this.getNumReedBlocksBelow(worldIn, pos) + 1;
         if (i < 4 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(3) == 0)) {
            this.grow(worldIn, pos, random, i);
            ForgeHooks.onCropsGrowPost(worldIn, pos, state);
         }
      }
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.below()).is(ModTags.ModBlockTags.ALOE_PLANTABLE_ON)
         || worldIn.getBlockState(pos.below()).getBlock() == ModBlock.ZIMBABWE_ALOE.get();
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!stateIn.canSurvive(worldIn, currentPos)) {
         worldIn.scheduleTick(currentPos, this, 1);
      }

      return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   @Override
   public void updatePostGen(LevelAccessor worldIn, BlockPos pos) {
      for (int i = 0; i < 3 && (worldIn.getBlockState(pos.above(i)).isAir() || worldIn.getBlockState(pos.above(i)).getBlock() == ModBlock.ZIMBABWE_ALOE.get()); i++) {
         worldIn.setBlock(pos.above(i), (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, Math.max(1, i + 3)), 3);
      }
   }

   public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      return (Integer)worldIn.getBlockState(pos.above(i)).getValue(PROPERTY_STAGE) != 1;
   }

   public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      int j = this.getNumReedBlocksBelow(worldIn, pos);
      int k = i + j + 1;
      if ((Integer)worldIn.getBlockState(pos).getValue(PROPERTY_AGE) == 0) {
         worldIn.setBlockAndUpdate(pos, (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 5));
      } else {
         BlockPos blockpos = pos.above(i);
         BlockState blockstate = worldIn.getBlockState(blockpos);
         if ((Integer)blockstate.getValue(PROPERTY_STAGE) == 1 || !worldIn.isEmptyBlock(blockpos.above())) {
            return;
         }

         this.grow(worldIn, blockpos, rand, k);
      }
   }

   protected void grow(Level worldIn, BlockPos posIn, RandomSource rand, int p_220258_5_) {
      if ((p_220258_5_ <= 2 || rand.nextInt(3) != 0) && p_220258_5_ <= 4) {
         worldIn.setBlockAndUpdate(posIn.above(), (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 5));
         worldIn.setBlockAndUpdate(posIn, (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 4));
         int j = 1;

         for (int i = 3; i >= 0 && worldIn.getBlockState(posIn.below(j)).getBlock() == ModBlock.ZIMBABWE_ALOE.get(); i--) {
            worldIn.setBlockAndUpdate(posIn.below(j), (BlockState)((Block)ModBlock.ZIMBABWE_ALOE.get()).defaultBlockState().setValue(PROPERTY_AGE, Math.max(i, 1)));
            j++;
         }

         worldIn.setBlockAndUpdate(posIn.above(), (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 5));
      } else {
         worldIn.setBlockAndUpdate(posIn.above(), (BlockState)((BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 5)).setValue(PROPERTY_STAGE, 1));
      }
   }

   public float getDestroyProgress(BlockState state, Player player, BlockGetter worldIn, BlockPos pos) {
      return player.getMainHandItem().canPerformAction(ToolActions.SWORD_DIG) ? 1.0F : super.getDestroyProgress(state, player, worldIn, pos);
   }

   protected int getNumReedBlocksAbove(BlockGetter worldIn, BlockPos pos) {
      int i = 0;

      while (worldIn.getBlockState(pos.above(i + 1)).getBlock() == ModBlock.TITAN_ARUM.get() || i > 20) {
         i++;
      }

      return i;
   }

   protected int getNumReedBlocksBelow(BlockGetter worldIn, BlockPos pos) {
      int i = 0;

      while (worldIn.getBlockState(pos.below(i + 1)).getBlock() == ModBlock.TITAN_ARUM.get() || i > 20) {
         i++;
      }

      return i;
   }
}
