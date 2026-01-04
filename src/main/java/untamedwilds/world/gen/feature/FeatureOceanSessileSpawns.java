package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.world.FaunaHandler;
import untamedwilds.world.FaunaSpawn;

public class FeatureOceanSessileSpawns extends Feature<NoneFeatureConfiguration> {
   public FeatureOceanSessileSpawns(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext context) {
      RandomSource rand = context.level().getRandom();
      BlockPos pos = context.origin();
      WorldGenLevel world = context.level();
      if (((List)ConfigMobControl.dimensionBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         for (int i = 0; i < 5; i++) {
            Optional<FaunaHandler.SpawnListEntry> entry = WeightedRandom.getRandomItem(rand, FaunaHandler.getSpawnableList(FaunaHandler.animalType.BENTHOS));
            if (entry.isPresent()
               && FaunaSpawn.performWorldGenSpawning(
                  entry.get().entityType, Type.NO_RESTRICTIONS, Types.OCEAN_FLOOR, world, pos, rand, entry.get().getGroupCount()
               )) {
               return true;
            }
         }

         return false;
      }
   }
}
