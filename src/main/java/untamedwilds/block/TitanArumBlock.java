package untamedwilds.block;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModTags;

public class TitanArumBlock extends Block implements BonemealableBlock, IPostGenUpdate {
   protected static final VoxelShape SHAPE_NORMAL = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
   protected static final VoxelShape SHAPE_SPATHE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
   protected static final VoxelShape SHAPE_CORM = Block.box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
   public static final IntegerProperty PROPERTY_AGE = BlockStateProperties.AGE_5;
   public static final IntegerProperty PROPERTY_STAGE = BlockStateProperties.STAGE;

   public TitanArumBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PROPERTY_AGE, 0)).setValue(PROPERTY_STAGE, 0));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{PROPERTY_AGE, PROPERTY_STAGE});
   }

   public ItemStack getCloneItemStack(BlockState stateIn, HitResult hitResultIn, BlockGetter blockGetterIn, BlockPos posIn, Player playerIn) {
      return new ItemStack((ItemLike)ModItems.SEED_TITAN_ARUM.get());
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      if ((Integer)state.getValue(PROPERTY_AGE) == 0) {
         return SHAPE_CORM;
      } else {
         Vec3 vector3d = state.getOffset(worldIn, pos);
         VoxelShape shape = state.getValue(PROPERTY_AGE) == 1 && state.getValue(PROPERTY_STAGE) == 1 ? SHAPE_NORMAL : SHAPE_SPATHE;
         return shape.move(vector3d.x, vector3d.y, vector3d.z);
      }
   }

   public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
      if (!state.canSurvive(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      }
   }

   public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
      if ((Integer)state.getValue(PROPERTY_STAGE) == 0 && random.nextInt(8) == 0 && worldIn.isEmptyBlock(pos.above()) && worldIn.getLightEmission(pos.above()) >= 9) {
         int i = this.getNumReedBlocksBelow(worldIn, pos) + 1;
         if (i < 4 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(3) == 0)) {
            this.grow(state, worldIn, pos, random, i);
            ForgeHooks.onCropsGrowPost(worldIn, pos, state);
         }
      }

      if ((Integer)state.getValue(PROPERTY_STAGE) == 1 && (Integer)state.getValue(PROPERTY_AGE) > 1) {
         this.spawnParticles(worldIn, pos, ParticleTypes.DRIPPING_HONEY);

         for (Mob mob : worldIn.getEntitiesOfClass(
            Mob.class,
            new AABB(
               (double)(pos.getX() - 16),
               (double)(pos.getY() - 4),
               (double)(pos.getZ() - 16),
               (double)(pos.getX() + 16),
               (double)(pos.getY() + 4),
               (double)(pos.getZ() + 16)
            )
         )) {
            if (mob.getTarget() == null && (mob.getMobType() == MobType.ARTHROPOD || mob.getMobType() == MobType.UNDEAD)) {
               mob.getNavigation()
                  .moveTo(
                     (double)(pos.getX() + random.nextInt(6) - 3), (double)pos.getY(), (double)(pos.getZ() + random.nextInt(6) - 3), 1.0
                  );
            }
         }
      }
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
      return blockstate.is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON) ? (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 0) : null;
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.below()).is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON)
         || worldIn.getBlockState(pos.below()).getBlock() == ModBlock.TITAN_ARUM.get();
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!stateIn.canSurvive(worldIn, currentPos)) {
         worldIn.scheduleTick(currentPos, this, 1);
      }

      return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   protected void grow(BlockState blockStateIn, Level worldIn, BlockPos posIn, RandomSource rand, int p_220258_5_) {
      int l = (Integer)blockStateIn.getValue(PROPERTY_AGE);
      if (l == 0) {
         worldIn.setBlockAndUpdate(posIn, (BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, 1));
      } else {
         worldIn.setBlock(posIn.above(), (BlockState)((BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, Math.min(3, l + 1))).setValue(PROPERTY_STAGE, 0), 3);
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

   private void makeAreaOfEffectCloud(Level worldIn, BlockPos pos) {
      AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(
         worldIn,
         (double)pos.getX() + 0.5,
         (double)(pos.getY() - (Integer)worldIn.getBlockState(pos).getValue(PROPERTY_AGE)),
         (double)pos.getZ() + 0.5
      );
      areaeffectcloudentity.setRadius(6.0F);
      areaeffectcloudentity.setRadiusOnUse(-0.2F);
      areaeffectcloudentity.setWaitTime(10);
      areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / ((float)areaeffectcloudentity.getDuration() * 0.5F));
      areaeffectcloudentity.setFixedColor(5599028);
      areaeffectcloudentity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 0, true, false));
      worldIn.addFreshEntity(areaeffectcloudentity);
   }

   @Override
   public void updatePostGen(LevelAccessor worldIn, BlockPos pos) {
      for (int i = 0; i < 3 && (worldIn.getBlockState(pos.above(i)).isAir() || worldIn.getBlockState(pos.above(i)).getBlock() == ModBlock.TITAN_ARUM.get()); i++) {
         worldIn.setBlock(pos.above(i), (BlockState)((BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, i + 1)).setValue(PROPERTY_STAGE, 1), 3);
      }
   }

   private <T extends ParticleOptions> void spawnParticles(Level worldIn, BlockPos pos, T particle) {
      RandomSource random = worldIn.getRandom();

      for (int i = 0; i < 6; i++) {
         float d3 = random.nextFloat() * 0.02F;
         float d1 = random.nextFloat() * 0.02F;
         float d2 = random.nextFloat() * 0.02F;
         ((ServerLevel)worldIn)
            .sendParticles(
               particle,
               (double)pos.getX() + 0.275 + (double)random.nextFloat() * 0.45,
               (double)((float)pos.getY() + random.nextFloat()),
               (double)pos.getZ() + 0.275 + (double)random.nextFloat() * 0.45,
               1,
               (double)d3,
               (double)d1,
               (double)d2,
               0.02F
            );
      }
   }

   public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      int j = this.getNumReedBlocksBelow(worldIn, pos);
      return i + j + 1 < 4 && (Integer)worldIn.getBlockState(pos.above(i)).getValue(PROPERTY_STAGE) != 1;
   }

   public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
      int i = this.getNumReedBlocksAbove(worldIn, pos);
      int j = this.getNumReedBlocksBelow(worldIn, pos);
      int k = i + j + 1;
      if (j < 3) {
         BlockPos blockpos = pos.above(i);
         BlockState blockstate = worldIn.getBlockState(blockpos);
         if (worldIn.getBlockState(pos.below()).getBlock() == ModBlock.TITAN_ARUM.get()
            || (Integer)blockstate.getValue(PROPERTY_STAGE) == 1
            || !worldIn.isEmptyBlock(blockpos.above())) {
            return;
         }

         if (k >= 3) {
            this.makeAreaOfEffectCloud(worldIn, pos);

            for (int var12 = 0; var12 < 3 && worldIn.getBlockState(pos.above(var12)).getBlock() == ModBlock.TITAN_ARUM.get(); var12++) {
               worldIn.setBlock(
                  pos.above(var12),
                  (BlockState)((BlockState)this.defaultBlockState().setValue(PROPERTY_AGE, (Integer)worldIn.getBlockState(pos.above(var12)).getValue(PROPERTY_AGE)))
                     .setValue(PROPERTY_STAGE, 1),
                  3
               );
            }

            for (int l = 0; l < 2; l++) {
               BlockPos seed_pos = pos.offset(rand.nextInt(3) - 1, 1 - rand.nextInt(3), rand.nextInt(3) - 1);
               if (worldIn.isInWorldBounds(seed_pos) && !worldIn.getBlockState(seed_pos).is(Blocks.WATER) && blockstate.canSurvive(worldIn, seed_pos)) {
                  worldIn.setBlock(seed_pos, (BlockState)((Block)ModBlock.TITAN_ARUM.get()).defaultBlockState().setValue(PROPERTY_STAGE, 1), 2);
               }
            }

            return;
         }

         this.grow(blockstate, worldIn, blockpos, rand, k);
      }
   }
}
