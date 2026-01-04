package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import untamedwilds.entity.ComplexMobAquatic;

public class FloatingPlantBlock extends BushBlock implements BonemealableBlock {
   protected static final VoxelShape SHAPE_NORMAL = Block.box(3.0, 0.0, 3.0, 13.0, 12.0, 13.0);

   public FloatingPlantBlock(Properties builder) {
      super(builder);
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      Vec3 vector3d = state.getOffset(worldIn, pos);
      return SHAPE_NORMAL.move(vector3d.x, vector3d.y, vector3d.z);
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.below();
      return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
   }

   protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
      FluidState fluidstate = worldIn.getFluidState(pos);
      FluidState fluidstate1 = worldIn.getFluidState(pos.above());
      return (fluidstate.getType() == Fluids.WATER || state.getBlock() instanceof IceBlock) && fluidstate1.getType() == Fluids.EMPTY;
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

   public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
      if (entityIn instanceof LivingEntity
         && !(entityIn instanceof WaterAnimal)
         && !(entityIn instanceof ComplexMobAquatic)
         && entityIn.isInWater()
         && !entityIn.isSteppingCarefully()) {
         entityIn.makeStuckInBlock(state, new Vec3(0.98F, 1.0, 0.98F));
         if (worldIn.getRandom().nextInt(20) == 0) {
            worldIn.playLocalSound(
               (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.WET_GRASS_STEP, SoundSource.AMBIENT, 1.0F, 1.0F, true
            );
         }
      }
   }
}
