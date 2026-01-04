package untamedwilds.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.BiomeModifier.Phase;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.config.ConfigMobControl;

public record UntamedWildsBiomeModifier(
   TagKey<Biome> dimension,
   List<HolderSet<Biome>> biomes,
   List<HolderSet<Biome>> blacklist,
   Decoration decoration,
   Holder<PlacedFeature> feature,
   String configOption
) implements BiomeModifier {
   public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(
      Keys.BIOME_MODIFIER_SERIALIZERS, "untamedwilds"
   );
   public static final RegistryObject<Codec<UntamedWildsBiomeModifier>> BIOME_MODIFIER_SERIALIZER = BIOME_MODIFIER_SERIALIZERS.register(
      "biome_modifier_serializer",
      () -> RecordCodecBuilder.create(
            builder -> builder.group(
                     TagKey.codec(Registries.BIOME).fieldOf("dimension").forGetter(UntamedWildsBiomeModifier::dimension),
                     Biome.LIST_CODEC.listOf().fieldOf("biomes").forGetter(UntamedWildsBiomeModifier::biomes),
                     Biome.LIST_CODEC.listOf().fieldOf("blacklist").forGetter(UntamedWildsBiomeModifier::blacklist),
                     Decoration.CODEC.fieldOf("decoration").forGetter(UntamedWildsBiomeModifier::decoration),
                     PlacedFeature.CODEC.fieldOf("feature").forGetter(UntamedWildsBiomeModifier::feature),
                     PrimitiveCodec.STRING.fieldOf("configOption").forGetter(UntamedWildsBiomeModifier::configOption)
                  )
                  .apply(builder, UntamedWildsBiomeModifier::new)
         )
   );

   public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
      BooleanValue option = ConfigFeatureControl.options.get(this.configOption);
      if (this.configOption.isEmpty()
         || option != null && (Boolean)option.get()
         || option == null && (Integer)ConfigFeatureControl.probUnderground.get() != 0 && (Boolean)ConfigMobControl.masterSpawner.get()) {
         if (phase != Phase.ADD) {
            return;
         }

         if (!biome.is(this.dimension)) {
            return;
         }

         if (this.blacklist.stream().anyMatch(set -> set.contains(biome))) {
            return;
         }

         if (this.biomes.isEmpty() || this.biomes.stream().anyMatch(set -> set.contains(biome))) {
            builder.getGenerationSettings().addFeature(this.decoration, this.feature);
         }
      }
   }

   public Codec<? extends BiomeModifier> codec() {
      return (Codec<? extends BiomeModifier>)BIOME_MODIFIER_SERIALIZER.get();
   }
}
