package untamedwilds.world;

import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.world.gen.feature.FeatureApexPredators;
import untamedwilds.world.gen.feature.FeatureCritterBurrow;
import untamedwilds.world.gen.feature.FeatureCritters;
import untamedwilds.world.gen.feature.FeatureDenseWater;
import untamedwilds.world.gen.feature.FeatureFloatingPlants;
import untamedwilds.world.gen.feature.FeatureHerbivores;
import untamedwilds.world.gen.feature.FeatureOceanSessileSpawns;
import untamedwilds.world.gen.feature.FeatureOceanSwimming;
import untamedwilds.world.gen.feature.FeatureReedClusters;
import untamedwilds.world.gen.feature.FeatureSeaAnemone;
import untamedwilds.world.gen.feature.FeatureUndergroundFaunaLarge;
import untamedwilds.world.gen.feature.FeatureUnderwaterAlgae;
import untamedwilds.world.gen.feature.FeatureVegetation;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.MOD
)
public class UntamedWildsGenerator {
   public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, "untamedwilds");
   public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registries.PLACED_FEATURE, "untamedwilds");
   public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "untamedwilds");
   public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATION = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, "untamedwilds");
   public static final Map<String, Float> biodiversity_levels = new HashMap<>();
   private static final RegistryObject<Feature<CountConfiguration>> SEA_ANEMONE = regFeature(
      "sea_anemone", () -> new FeatureSeaAnemone(CountConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> REEDS = regFeature(
      "reeds", () -> new FeatureReedClusters(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> ALGAE = regFeature(
      "algae", () -> new FeatureUnderwaterAlgae(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> VEGETATION = regFeature(
      "vegetation", () -> new FeatureVegetation(ProbabilityFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOATING_VEGETATION = regFeature(
      "floating_vegetation", () -> new FeatureFloatingPlants(NoneFeatureConfiguration.CODEC)
   );
   public static final RegistryObject<TreeDecoratorType<?>> TREE_ORCHID = TREE_DECORATION.register(
      "orchid", () -> new TreeDecoratorType(TreeDecorator.CODEC)
   );
   public static final RegistryObject<Feature<NoneFeatureConfiguration>> UNDERGROUND = regFeature(
      "underground", () -> new FeatureUndergroundFaunaLarge(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> APEX = regFeature(
      "apex_predator", () -> new FeatureApexPredators(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> HERBIVORES = regFeature(
      "herbivores", () -> new FeatureHerbivores(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> CRITTERS = regFeature(
      "critter", () -> new FeatureCritters(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> SESSILE = regFeature(
      "sessile", () -> new FeatureOceanSessileSpawns(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> OCEAN = regFeature(
      "ocean_rare", () -> new FeatureOceanSwimming(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> DENSE_WATER = regFeature(
      "dense_water", () -> new FeatureDenseWater(NoneFeatureConfiguration.CODEC)
   );
   private static final RegistryObject<Feature<NoneFeatureConfiguration>> CRITTER_BURROW = regFeature(
      "burrow", () -> new FeatureCritterBurrow(NoneFeatureConfiguration.CODEC)
   );

   private static <B extends Feature<?>> RegistryObject<B> regFeature(String name, Supplier<? extends B> supplier) {
      return FEATURES.register(name, supplier);
   }

   public static Map<ResourceLocation, BiomeModifier> generateModifierByLocation(RegistryOps<JsonElement> registryOps) {
      Map<ResourceLocation, BiomeModifier> map = new HashMap<>();
      addFeature(
         map,
         "sessile",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)SESSILE.get(), FeatureConfiguration.NONE), "")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqSessile.get()))
            .tag(Arrays.asList(BiomeTags.IS_OCEAN))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      addFeature(
         map,
         "ocean_rare",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)OCEAN.get(), FeatureConfiguration.NONE), "")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqOcean.get()))
            .tag(Arrays.asList(BiomeTags.IS_OCEAN))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      addFeature(
         map,
         "sea_anemone",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)SEA_ANEMONE.get(), new CountConfiguration(4)), "gencontrol.anemone")
            .placementModifier(RarityFilter.onAverageOnceEvery(6))
            .blacklist(Arrays.asList(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN))
            .decoration(Decoration.VEGETAL_DECORATION)
      );
      addFeature(
         map,
         "floating_vegetation",
         new UntamedWildsGenerator.Builder(
               registryOps, new ConfiguredFeature((Feature)FLOATING_VEGETATION.get(), FeatureConfiguration.NONE), "gencontrol.algae"
            )
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqAlgae.get()))
            .tag(Arrays.asList(BiomeTags.IS_JUNGLE))
            .decoration(Decoration.VEGETAL_DECORATION)
      );
      addFeature(
         map,
         "reeds",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)REEDS.get(), FeatureConfiguration.NONE), "gencontrol.reeds")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqReeds.get()))
            .blacklist(
               ((List<String>)ConfigFeatureControl.reedBlacklist.get())
                  .stream()
                  .map(s -> ResourceKey.create(Registries.BIOME, new ResourceLocation(s)))
                  .collect(Collectors.toList())
            )
            .decoration(Decoration.VEGETAL_DECORATION)
      );
      addFeature(
         map,
         "algae",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)ALGAE.get(), FeatureConfiguration.NONE), "gencontrol.algae")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqAlgae.get()))
            .blacklist(
               ((List<String>)ConfigFeatureControl.algaeBlacklist.get())
                  .stream()
                  .map(s -> ResourceKey.create(Registries.BIOME, new ResourceLocation(s)))
                  .collect(Collectors.toList())
            )
            .decoration(Decoration.VEGETAL_DECORATION)
      );
      addFeature(
         map,
         "vegetation",
         new UntamedWildsGenerator.Builder(
               registryOps, new ConfiguredFeature((Feature)VEGETATION.get(), new ProbabilityFeatureConfiguration(0.25F)), "gencontrol.bush"
            )
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqFlora.get()))
            .blacklist(
               ((List<String>)ConfigFeatureControl.floraBlacklist.get())
                  .stream()
                  .map(s -> ResourceKey.create(Registries.BIOME, new ResourceLocation(s)))
                  .collect(Collectors.toList())
            )
            .decoration(Decoration.VEGETAL_DECORATION)
      );
      addFeature(
         map,
         "dense_water",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)DENSE_WATER.get(), FeatureConfiguration.NONE), "")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqWater.get()))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      addFeature(
         map,
         "burrow",
         new UntamedWildsGenerator.Builder(
               registryOps,
               new ConfiguredFeature(
                  ConfigFeatureControl.addBurrows.get() ? (Feature)CRITTER_BURROW.get() : (Feature)CRITTERS.get(), FeatureConfiguration.NONE
               ),
               ""
            )
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqCritter.get()))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      addFeature(
         map,
         "apex_predator",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)APEX.get(), FeatureConfiguration.NONE), "")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqApex.get()))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      addFeature(
         map,
         "herbivores",
         new UntamedWildsGenerator.Builder(registryOps, new ConfiguredFeature((Feature)HERBIVORES.get(), FeatureConfiguration.NONE), "")
            .placementModifier(RarityFilter.onAverageOnceEvery((Integer)ConfigFeatureControl.freqHerbivores.get()))
            .decoration(Decoration.TOP_LAYER_MODIFICATION)
      );
      return map;
   }

   private static void addFeature(Map<ResourceLocation, BiomeModifier> map, String placedFeatureName, UntamedWildsGenerator.Builder builder) {
      BiomeModifier modifier = builder.build();
      ResourceLocation location = new ResourceLocation("untamedwilds", placedFeatureName);
      map.put(location, modifier);
   }

   public static float getBioDiversityLevel(ResourceLocation biome) {
      String key = biome.getPath();
      return biodiversity_levels.containsKey(key) ? biodiversity_levels.get(key) : 0.6F;
   }

   private static class Builder {
      private final ConfiguredFeature<?, ?> placedFeatureName;
      private TagKey<Biome> dimension = BiomeTags.IS_OVERWORLD;
      private final List<PlacementModifier> placementModifiers = new ArrayList<>();
      private final List<TagKey<Biome>> biomeTags = new ArrayList<>();
      private final List<Holder<Biome>> blacklist = new ArrayList<>();
      private final List<Holder<Biome>> extraBiomes = new ArrayList<>();
      private final RegistryOps<JsonElement> registryOps;
      private Decoration decoration = Decoration.VEGETAL_DECORATION;
      private final String configOption;

      public Builder(RegistryOps<JsonElement> registryOps, ConfiguredFeature<?, ?> placedFeature, String configOption) {
         this.registryOps = registryOps;
         this.placedFeatureName = placedFeature;
         this.configOption = configOption;
      }

      public UntamedWildsGenerator.Builder dimension(TagKey<Biome> tag) {
         this.dimension = tag;
         return this;
      }

      public UntamedWildsGenerator.Builder placementModifier(PlacementModifier placementModifier) {
         this.placementModifiers.add(placementModifier);
         return this;
      }

      public UntamedWildsGenerator.Builder placementModifiers(List<PlacementModifier> placementModifiers) {
         this.placementModifiers.addAll(placementModifiers);
         return this;
      }

      public UntamedWildsGenerator.Builder tag(List<TagKey<Biome>> tags) {
         this.biomeTags.addAll(tags);
         return this;
      }

      public UntamedWildsGenerator.Builder blacklist(List<ResourceKey<Biome>> biomes) {
         for (ResourceKey<Biome> biome : biomes) {
            this.blacklist.add((Holder<Biome>)((HolderGetter)this.registryOps.getter(Registries.BIOME).get()).get(biome).get());
         }

         return this;
      }

      public UntamedWildsGenerator.Builder extraBiomes(ResourceKey<Biome>... biomes) {
         for (ResourceKey<Biome> biome : biomes) {
            this.extraBiomes.add((Holder<Biome>)((HolderGetter)this.registryOps.getter(Registries.BIOME).get()).get(biome).get());
         }

         return this;
      }

      public UntamedWildsGenerator.Builder decoration(Decoration decoration) {
         this.decoration = decoration;
         return this;
      }

      private static HolderSet<Biome> getBiomesByTag(RegistryOps<JsonElement> registryOps, TagKey<Biome> tag) {
         return HolderSet.emptyNamed((HolderOwner)registryOps.owner(Registries.BIOME).orElseThrow(), tag);
      }

      public UntamedWildsBiomeModifier build() {
         List<HolderSet<Biome>> biomesSet = new ArrayList<>(
            this.biomeTags.stream().map(tag -> getBiomesByTag(this.registryOps, (TagKey<Biome>)tag)).collect(Collectors.toList())
         );
         List<HolderSet<Biome>> blacklistSet = new ArrayList<>();
         blacklistSet.add(HolderSet.direct(this.blacklist));
         Holder<PlacedFeature> placedFeature = Holder.direct(new PlacedFeature(Holder.direct(this.placedFeatureName), this.placementModifiers));
         return new UntamedWildsBiomeModifier(this.dimension, biomesSet, blacklistSet, this.decoration, placedFeature, this.configOption);
      }
   }
}
