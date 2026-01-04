package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.init.ModBlock;

public class FeatureFloatingPlants extends Feature<NoneFeatureConfiguration> {
   public FeatureFloatingPlants(Codec<NoneFeatureConfiguration> codec) {
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

         for (int i = 0; i < 64; i++) {
            // Use set() instead of cumulative move() to stay within chunk bounds
            blockpos$mutableblockpos.set(
               originX + rand.nextInt(8) - rand.nextInt(8),
               originY + rand.nextInt(2) - rand.nextInt(2),
               originZ + rand.nextInt(8) - rand.nextInt(8)
            );
            if (world.getFluidState(blockpos$mutableblockpos.below()).getType() == Fluids.WATER && world.isEmptyBlock(blockpos$mutableblockpos)) {
               world.setBlock(blockpos$mutableblockpos, ((Block)ModBlock.WATER_HYACINTH.get()).defaultBlockState(), 2);
               world.getChunk(blockpos$mutableblockpos).markPosForPostprocessing(blockpos$mutableblockpos.below());
               flag = true;
            }
         }

         return flag;
      }
   }
}
