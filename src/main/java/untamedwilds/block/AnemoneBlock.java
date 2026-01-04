package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AnemoneBlock extends BushBlock implements LiquidBlockContainer {
   private static final VoxelShape SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 6.0, 10.0);

   public AnemoneBlock(Properties properties) {
      super(properties);
   }

   public VoxelShape getShape(BlockState p_53517_, BlockGetter p_53518_, BlockPos p_53519_, CollisionContext p_53520_) {
      Vec3 vec3 = p_53517_.getOffset(p_53518_, p_53519_);
      return SHAPE.move(vec3.x, vec3.y, vec3.z);
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

   public FluidState getFluidState(BlockState p_154537_) {
      return Fluids.WATER.getSource(false);
   }

   public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
      return false;
   }

   public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidIn) {
      return false;
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (!world.isClientSide && entity instanceof LivingEntity && !(entity instanceof TropicalFish)) {
         ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.POISON, 80, 0));
      }
   }
}
