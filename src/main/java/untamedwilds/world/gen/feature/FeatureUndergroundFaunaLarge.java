package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.world.FaunaHandler;
import untamedwilds.world.FaunaSpawn;

public class FeatureUndergroundFaunaLarge extends Feature<NoneFeatureConfiguration> {
   public FeatureUndergroundFaunaLarge(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public static List<PlacementModifier> placed() {
      return Arrays.asList(
         CountPlacement.of(1),
         InSquarePlacement.spread(),
         PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
         RandomOffsetPlacement.vertical(ConstantInt.of((Integer)ConfigFeatureControl.probUnderground.get())),
         BiomeFilter.biome()
      );
   }

   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> config) {
      WorldGenLevel world = config.level();
      BlockPos blockpos = config.origin();
      RandomSource rng = config.random();
      Optional<FaunaHandler.SpawnListEntry> entry = Optional.empty();
      MutableBlockPos setPos = new MutableBlockPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      int horiz = 2;
      int vert = 2;
      if (((List)ConfigMobControl.dimensionBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
               for (int k = -2; k < 3; k++) {
                  setPos.set(blockpos.getX() + i, blockpos.getY() + k, blockpos.getZ() + j);
                  if (world.isStateAtPosition(setPos, BlockStateBase::isAir)) {
                     for (int l = 0; l < 5; l++) {
                        if (entry.isEmpty()) {
                           entry = WeightedRandom.getRandomItem(rng, FaunaHandler.getSpawnableList(FaunaHandler.animalType.LARGE_UNDERGROUND));
                        }

                        if (entry.isPresent()) {
                           EntityType<?> type = entry.get().entityType;
                           if (type != null
                              && FaunaSpawn.performWorldGenSpawning(type, Type.NO_RESTRICTIONS, null, world, blockpos, rng, entry.get().getGroupCount())) {
                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }

         return true;
      }
   }
}
