package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;

public class UndergrowthBlock extends BushBlock implements BonemealableBlock, IForgeShearable {
   public UndergrowthBlock(Properties properties) {
      super(properties);
   }

   protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return state.isFaceSturdy(worldIn, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
   }

   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
      if (entityIn instanceof Player && !entityIn.isSteppingCarefully()) {
         entityIn.makeStuckInBlock(state, new Vec3(0.95F, 1.0, 0.95F));
         if (worldIn.getRandom().nextInt(20) == 0) {
            worldIn.playLocalSound(
               (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.GRASS_STEP, SoundSource.AMBIENT, 1.0F, 1.0F, true
            );
         }
      }
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
         if (worldIn.isInWorldBounds(blockpos) && worldIn.isEmptyBlock(blockpos) && blockstate.canSurvive(worldIn, blockpos)) {
            worldIn.setBlock(blockpos, blockstate, 2);
         }
      }
   }
}
