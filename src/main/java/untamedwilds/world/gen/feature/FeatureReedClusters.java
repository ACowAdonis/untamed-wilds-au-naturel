package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags.Biomes;
import untamedwilds.block.ReedBlock;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;

public class FeatureReedClusters extends Feature<NoneFeatureConfiguration> {
   public FeatureReedClusters(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext context) {
      RandomSource rand = context.level().getRandom();
      WorldGenLevel world = context.level();
      BlockPos genPos = world.getHeightmapPos(Types.OCEAN_FLOOR, context.origin());
      if (((List)ConfigFeatureControl.dimensionFeatureBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         boolean flag = false;
         MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();
         int originX = genPos.getX() + 8;
         int originY = genPos.getY();
         int originZ = genPos.getZ() + 8;
         blockpos$mutableblockpos.set(originX, originY, originZ);
         Holder<Biome> biome = world.getBiome(blockpos$mutableblockpos);
         int attempts = world.getBiome(genPos).is(BiomeTags.IS_RIVER) ? 3 : 1;

         for (int k = 0; k < attempts; k++) {
            for (int i = 0; i < 16; i++) {
               // Use set() instead of cumulative move() to stay within chunk bounds
               blockpos$mutableblockpos.set(
                  originX + rand.nextInt(6) - rand.nextInt(6),
                  originY + rand.nextInt(2) - rand.nextInt(2),
                  originZ + rand.nextInt(6) - rand.nextInt(6)
               );
               if (world.getFluidState(blockpos$mutableblockpos.above(3)).isEmpty()
                  && world.getBlockState(blockpos$mutableblockpos.below()).is(ModTags.ModBlockTags.REEDS_PLANTABLE_ON)
                  && this.isValidBiome(biome)
                  && (
                     !world.getBlockState(blockpos$mutableblockpos).isFaceSturdy(context.level(), blockpos$mutableblockpos, Direction.UP)
                        || world.getBlockState(blockpos$mutableblockpos).getBlock() == Blocks.WATER && world.isEmptyBlock(blockpos$mutableblockpos.above())
                  )) {
                  int height = rand.nextInt(4);

                  for (int j = 0; j <= height; j++) {
                     int fluidstate = world.getFluidState(blockpos$mutableblockpos.above(j)).isEmpty() ? 1 : 2;
                     BlockState blockstate = ((ReedBlock)ModBlock.COMMON_REED.get()).getStateForWorldgen(world, blockpos$mutableblockpos.above(j));
                     if (blockstate != null) {
                        world.setBlock(
                           blockpos$mutableblockpos.above(j), (BlockState)blockstate.setValue(ReedBlock.PROPERTY_AGE, j == height ? 0 : fluidstate), 2
                        );
                     }
                  }

                  world.getChunk(blockpos$mutableblockpos).markPosForPostprocessing(blockpos$mutableblockpos.below());
                  flag = true;
               }
            }
         }

         return flag;
      }
   }

   private boolean isValidBiome(Holder<Biome> biomeIn) {
      return biomeIn.is(BiomeTags.IS_RIVER) || biomeIn.is(BiomeTags.IS_JUNGLE) || biomeIn.is(Biomes.IS_SWAMP);
   }
}
