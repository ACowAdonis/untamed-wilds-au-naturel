package untamedwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import untamedwilds.init.ModBlock;

public class SharkMeatBlock extends RotatedPillarBlock {
   public static IntegerProperty PROGRESS = IntegerProperty.create("progress", 0, 7);

   public SharkMeatBlock(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)super.defaultBlockState().setValue(PROGRESS, 0));
   }

   public boolean isRandomlyTicking(BlockState state) {
      return true;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{PROGRESS});
      super.createBlockStateDefinition(builder);
   }

   public int getMaxProgress() {
      return 7;
   }

   public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
      if (!level.isClientSide) {
         float chance = 0.0F;
         if (level.getBlockState(pos.below()).is(BlockTags.SAND) || level.getBlockState(pos.below()).getBlock().equals(Blocks.GRAVEL)) {
            chance += 0.1F;
         }

         if (level.getBlockState(pos.above()).is(BlockTags.SAND) || level.getBlockState(pos.above()).getBlock().equals(Blocks.GRAVEL)) {
            chance += 0.1F;
         } else if (level.getBlockState(pos.above()).is(BlockTags.ANVIL)) {
            chance += 0.4F;
         }

         if (level.getRandom().nextFloat() <= chance) {
            if ((Integer)state.getValue(PROGRESS) == this.getMaxProgress()) {
               level.setBlock(pos, ((Block)ModBlock.SHARK_MEAT_FERMENTED.get()).withPropertiesOf(state), 3);
            } else {
               level.setBlock(pos, (BlockState)state.setValue(PROGRESS, (Integer)state.getValue(PROGRESS) + 1), 3);
            }
         }
      }
   }

   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
      super.animateTick(state, level, pos, random);
      if (level.getBlockState(pos.above()).is(BlockTags.SAND) || level.getBlockState(pos.above()).getBlock().equals(Blocks.GRAVEL)) {
         level.addParticle(
            ParticleTypes.MYCELIUM,
            (double)pos.getX() + (double)random.nextFloat(),
            (double)pos.getY() + 2.1,
            (double)pos.getZ() + (double)random.nextFloat(),
            0.0,
            0.0,
            0.0
         );
      } else if (random.nextInt(10) == 0) {
         level.addParticle(
            ParticleTypes.MYCELIUM,
            (double)pos.getX() + (double)random.nextFloat(),
            (double)pos.getY() + 1.1,
            (double)pos.getZ() + (double)random.nextFloat(),
            0.0,
            0.0,
            0.0
         );
      }
   }
}
