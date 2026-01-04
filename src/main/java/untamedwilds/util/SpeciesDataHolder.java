package untamedwilds.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import untamedwilds.entity.ComplexMobTerrestrial;

public class SpeciesDataHolder {
   public static final Codec<SpeciesDataHolder> CODEC = RecordCodecBuilder.create(
      p_237051_0_ -> p_237051_0_.group(
               Codec.STRING.fieldOf("name").orElse("").forGetter(p_237056_0_ -> p_237056_0_.name),
               Codec.INT.fieldOf("variant").orElse(0).forGetter(p_237054_0_ -> p_237054_0_.variant),
               Codec.FLOAT.fieldOf("scale").orElse(1.0F).forGetter(p_237055_0_ -> p_237055_0_.modelScale),
               Codec.INT.fieldOf("rarity").orElse(-1).forGetter(p_237054_0_ -> p_237054_0_.rarity),
               Codec.FLOAT.fieldOf("attack").orElse(-1.0F).forGetter(p_237055_0_ -> p_237055_0_.attack),
               Codec.FLOAT.fieldOf("health").orElse(-1.0F).forGetter(p_237055_0_ -> p_237055_0_.health),
               ComplexMobTerrestrial.ActivityType.CODEC
                  .fieldOf("activityType")
                  .orElse(ComplexMobTerrestrial.ActivityType.INSOMNIAC)
                  .forGetter(p_237052_0_ -> p_237052_0_.activityType),
               Codec.STRING.fieldOf("favourite_food").orElse("").forGetter(p_237052_0_ -> p_237052_0_.favouriteFood_input),
               Codec.INT.fieldOf("growing_time").orElse(-1).forGetter(p_237054_0_ -> p_237054_0_.growing_time),
               Codec.INT.fieldOf("offspring").orElse(-1).forGetter(p_237054_0_ -> p_237054_0_.offspring),
               Codec.INT.fieldOf("skins").orElse(10).forGetter(p_237054_0_ -> p_237054_0_.skins),
               Codec.STRING.fieldOf("breeding_season").orElse("NONE").forGetter(p_237054_0_ -> p_237054_0_.breeding_season),
               Codec.unboundedMap(Codec.STRING, SoundEvent.DIRECT_CODEC)
                  .fieldOf("sounds")
                  .orElse(Collections.emptyMap())
                  .forGetter(p_237052_0_ -> p_237052_0_.sounds),
               Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("flags").orElse(Collections.emptyMap()).forGetter(p_237054_0_ -> p_237054_0_.flags),
               Codec.STRING.listOf().listOf().fieldOf("spawnBiomes").orElse(new ArrayList<List<String>>()).forGetter((SpeciesDataHolder p_237052_0_) -> p_237052_0_.spawnBiomes)
            )
            .apply(p_237051_0_, SpeciesDataHolder::new)
   );
   private final String name;
   private final int variant;
   private final Float modelScale;
   private final int rarity;
   private final float attack;
   private final float health;
   private final ComplexMobTerrestrial.ActivityType activityType;
   private final String favouriteFood_input;
   private final ItemStack favouriteFood;
   private final int growing_time;
   private final int offspring;
   private final int skins;
   private final String breeding_season;
   private final Map<String, SoundEvent> sounds;
   private final Map<String, Integer> flags;
   private final List<List<String>> spawnBiomes;
   private final List<List<SpeciesDataHolder.BiomeTestHolder>> spawnBiomeData;

   public SpeciesDataHolder(
      String p_i232114_1_,
      int variant,
      float p_i232114_2_,
      int p_i232114_3_,
      float attack,
      float health,
      ComplexMobTerrestrial.ActivityType activityType,
      String favourite_food,
      int growing_time,
      int offspring,
      int skins,
      String breeding_season,
      Map<String, SoundEvent> sounds,
      Map<String, Integer> flags,
      List<List<String>> spawn_biomes
   ) {
      this.name = p_i232114_1_;
      this.variant = variant;
      this.modelScale = p_i232114_2_;
      this.rarity = p_i232114_3_;
      this.attack = attack;
      this.health = health;
      this.activityType = activityType;
      this.favouriteFood_input = favourite_food;
      this.favouriteFood = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(this.favouriteFood_input)));
      this.growing_time = growing_time;
      this.offspring = offspring;
      this.skins = skins;
      this.breeding_season = breeding_season;
      this.sounds = sounds;
      this.flags = flags;
      this.spawnBiomes = spawn_biomes;
      this.spawnBiomeData = new ArrayList<>();

      for (List<String> sublist : this.spawnBiomes) {
         List<SpeciesDataHolder.BiomeTestHolder> subsublist = new ArrayList<>();

         for (String condition : sublist) {
            String key = condition;
            if (condition.contains("|")) {
               key = condition.split("\\|")[1];
            }

            SpeciesDataHolder.ConditionTypes type = getTypeOfCondition(condition);
            SpeciesDataHolder.ConditionModifiers modifier = getModifierFromString(condition);
            SpeciesDataHolder.BiomeTestHolder testHolder = new SpeciesDataHolder.BiomeTestHolder(key, type, modifier);
            subsublist.add(testHolder);
         }

         this.spawnBiomeData.add(subsublist);
      }
   }

   public String getString() {
      return this.name + ": Scale: " + this.modelScale + " Rarity: " + this.rarity + " Spawn Biomes: " + this.spawnBiomes;
   }

   public String getName() {
      return this.name;
   }

   public int getVariant() {
      return this.variant;
   }

   public Float getModelScale() {
      return this.modelScale;
   }

   public Integer getRarity() {
      return this.rarity;
   }

   public Float getAttack() {
      return this.attack;
   }

   public Float getHealth() {
      return this.health;
   }

   public ComplexMobTerrestrial.ActivityType getActivityType() {
      return this.activityType;
   }

   @Nullable
   public ItemStack getFavouriteFood() {
      return this.favouriteFood;
   }

   public Integer getGrowingTime() {
      return this.growing_time;
   }

   public Integer getOffspring() {
      return this.offspring;
   }

   public Integer getSkins() {
      return this.skins;
   }

   public String getBreedingSeason() {
      return this.breeding_season;
   }

   public Map<String, SoundEvent> getSounds() {
      return this.sounds;
   }

   public Map<String, Integer> getFlags() {
      return this.flags;
   }

   public List<List<SpeciesDataHolder.BiomeTestHolder>> getBiomeCategories() {
      return this.spawnBiomeData;
   }

   public static SpeciesDataHolder.ConditionModifiers getModifierFromString(String strIn) {
      if (strIn.contains("!")) {
         return SpeciesDataHolder.ConditionModifiers.INVERTED;
      } else {
         return strIn.contains("#") ? SpeciesDataHolder.ConditionModifiers.PRIORITY : SpeciesDataHolder.ConditionModifiers.NONE;
      }
   }

   public static SpeciesDataHolder.ConditionTypes getTypeOfCondition(String strIn) {
      String clean = strIn.replaceAll("[!#]", "");
      if (clean.contains("|")) {
         String str = clean.split("\\|")[0];
         switch (str) {
            case "tag":
               return SpeciesDataHolder.ConditionTypes.BIOME_TAG;
            case "resource":
               return SpeciesDataHolder.ConditionTypes.REGISTRY_NAME;
         }
      }

      return SpeciesDataHolder.ConditionTypes.BIOME_TAG;
   }

   public static class BiomeTestHolder {
      private final String key;
      private final SpeciesDataHolder.ConditionTypes type;
      private final SpeciesDataHolder.ConditionModifiers modifier;

      public BiomeTestHolder(String key, SpeciesDataHolder.ConditionTypes typeIn, SpeciesDataHolder.ConditionModifiers modifierIn) {
         this.key = key;
         this.type = typeIn;
         this.modifier = modifierIn;
      }

      public boolean isValidBiome(Holder<Biome> biomekey, Biome biome) {
         boolean result = switch (this.type) {
            case BIOME_TAG -> biomekey.is(TagKey.create(Registries.BIOME, new ResourceLocation(this.key)))
            || biomekey.is(TagKey.create(Registries.BIOME, new ResourceLocation("forge", this.key)));
            case REGISTRY_NAME -> ((ResourceKey)biomekey.unwrapKey().get()).location().equals(new ResourceLocation(this.key));
         };
         return this.modifier == SpeciesDataHolder.ConditionModifiers.INVERTED ? !result : result;
      }
   }

   public static enum ConditionModifiers {
      NONE(" "),
      INVERTED(" Inverted "),
      PRIORITY(" Priority ");

      public String type;

      private ConditionModifiers(String type) {
         this.type = type;
      }

      public String getString() {
         return this.type;
      }
   }

   public static enum ConditionTypes {
      BIOME_TAG("Tag"),
      REGISTRY_NAME("Resource Location");

      public String type;

      private ConditionTypes(String type) {
         this.type = type;
      }

      public String getString() {
         return this.type;
      }
   }
}
