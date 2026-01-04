package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.init.ModBlock;

public class FeatureSeaAnemone extends Feature<CountConfiguration> {
   public FeatureSeaAnemone(Codec<CountConfiguration> p_i231987_1_) {
      super(p_i231987_1_);
   }

   public boolean place(FeaturePlaceContext<CountConfiguration> context) {
      RandomSource rand = context.level().getRandom();
      WorldGenLevel world = context.level();
      BlockPos genPos = world.getHeightmapPos(Types.OCEAN_FLOOR, context.origin());
      if (((List)ConfigFeatureControl.dimensionFeatureBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         boolean flag = false;
         int originX = genPos.getX() + rand.nextInt(8) - rand.nextInt(8);
         int originY = genPos.getY();
         int originZ = genPos.getZ() + rand.nextInt(8) - rand.nextInt(8);
         MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos().set(originX, originY, originZ);
         if (blockpos$mutableblockpos.getY() > 44 && blockpos$mutableblockpos.getY() < 62) {
            Block type = switch (rand.nextInt(3)) {
               case 1 -> (Block)ModBlock.ANEMONE_SAND.get();
               case 2 -> (Block)ModBlock.ANEMONE_SEBAE.get();
               default -> (Block)ModBlock.ANEMONE_ROSE_BULB.get();
            };

            for (int j = 0; j < ((CountConfiguration)context.config()).count().sample(rand); j++) {
               // Use set() instead of cumulative move() to stay within chunk bounds
               blockpos$mutableblockpos.set(
                  originX + rand.nextInt(8) - rand.nextInt(8),
                  originY + rand.nextInt(3) - rand.nextInt(3),
                  originZ + rand.nextInt(8) - rand.nextInt(8)
               );
               int i1 = world.getHeight(Types.OCEAN_FLOOR, blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getZ());
               BlockState blockstate = type.defaultBlockState();
               if (i1 < 62 && world.getBlockState(blockpos$mutableblockpos).is(Blocks.WATER) && blockstate.canSurvive(world, blockpos$mutableblockpos)) {
                  world.setBlock(blockpos$mutableblockpos, blockstate, 2);
                  world.getChunk(blockpos$mutableblockpos).markPosForPostprocessing(blockpos$mutableblockpos.below());
                  flag = true;
               }
            }
         }

         return flag;
      }
   }
}
