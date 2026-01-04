package untamedwilds.block;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.ToolActions;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;

public class UndergrowthPoisonousBlock extends UndergrowthBlock implements BonemealableBlock, IPostGenUpdate, IForgeShearable {
   public static final IntegerProperty PROPERTY_AGE = BlockStateProperties.AGE_2;

   public UndergrowthPoisonousBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(PROPERTY_AGE, 0));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{PROPERTY_AGE});
   }

   public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
      return false;
   }

   @Override
   public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
      if (entityIn instanceof LivingEntity && !entityIn.isSteppingCarefully() && entityIn.getBbHeight() > 1.0F) {
         ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
         entityIn.makeStuckInBlock(state, new Vec3(0.95F, 1.0, 0.95F));
         if (worldIn.getRandom().nextInt(20) == 0) {
            worldIn.playLocalSound(
               (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.GRASS_STEP, SoundSource.AMBIENT, 1.0F, 1.0F, true
            );
         }
      }
   }

   @Nullable
   public BlockState getStateForWorldgen(LevelAccessor world, BlockPos pos) {
      BlockState blockstate = world.getBlockState(pos.below());
      if (blockstate.getBlock() != ModBlock.HEMLOCK.get()) {
         return blockstate.is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON) ? (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0) : null;
      } else {
         if (world.getFluidState(pos.below()).isEmpty() || world.getBlockState(pos.below(2)).getBlock() == ModBlock.HEMLOCK.get()) {
            world.setBlock(pos.below(), (BlockState)blockstate.setValue(PROPERTY_AGE, 1), 1);
         }

         BlockState blockstate1 = world.getBlockState(pos.above());
         return blockstate1.getBlock() != ModBlock.HEMLOCK.get()
            ? (BlockState)((Block)ModBlock.HEMLOCK.get()).defaultBlockState().setValue(PROPERTY_AGE, 0)
            : (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 1);
      }
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
      if (blockstate.getBlock() != ModBlock.HEMLOCK.get()) {
         return blockstate.is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON) ? (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0) : null;
      } else {
         if (context.getLevel().getFluidState(context.getClickedPos().below()).isEmpty()
            || context.getLevel().getBlockState(context.getClickedPos().below(2)).getBlock() == ModBlock.HEMLOCK.get()) {
            context.getLevel().setBlockAndUpdate(context.getClickedPos().below(), (BlockState)blockstate.setValue(PROPERTY_AGE, 1));
         }

         BlockState blockstate1 = context.getLevel().getBlockState(context.getClickedPos().above());
         return blockstate1.getBlock() != ModBlock.HEMLOCK.get()
            ? (BlockState)((Block)ModBlock.HEMLOCK.get()).defaultBlockState().setValue(PROPERTY_AGE, 0)
            : (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 1);
      }
   }

   public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
      if (!state.canSurvive(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      }
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.below()).is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON)
         || worldIn.getBlockState(pos.below()).getBlock() == ModBlock.HEMLOCK.get();
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!stateIn.canSurvive(worldIn, currentPos)) {
         worldIn.scheduleTick(currentPos, this, 1);
      }

      return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   @Override
   public void updatePostGen(LevelAccessor worldIn, BlockPos pos) {
      if (worldIn.getRandom().nextBoolean()) {
         worldIn.setBlock(pos, (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 1), 3);
         worldIn.setBlock(pos.above(), (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0), 3);
      }
   }

   @Override
   public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      int j = this.getNumReedBlocksBelow(worldIn, pos);
      return i + j + 1 < 3;
   }

   @Override
   public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      return true;
   }

   @Override
   public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      int j = this.getNumReedBlocksBelow(worldIn, pos);
      int k = i + j + 1;
      int l = 1 + rand.nextInt(2);

      for (int i1 = 0; i1 < l; i1++) {
         BlockPos blockpos = pos.above(i);
         BlockState blockstate = worldIn.getBlockState(blockpos);
         if (k >= 4 || !worldIn.isEmptyBlock(blockpos.above())) {
            return;
         }

         if ((Integer)blockstate.getValue(PROPERTY_AGE) != 2) {
            worldIn.setBlock(blockpos, (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 1), 3);
         }

         worldIn.setBlock(blockpos.above(), (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0), 3);
         i++;
         k++;
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
