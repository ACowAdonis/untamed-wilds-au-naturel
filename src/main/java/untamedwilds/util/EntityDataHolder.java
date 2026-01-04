package untamedwilds.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import untamedwilds.UntamedWilds;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.init.ModSounds;

public class EntityDataHolder {
   public static final Codec<EntityDataHolder> CODEC = RecordCodecBuilder.create(
      p_237051_0_ -> p_237051_0_.group(
               Codec.STRING.fieldOf("name").orElse("").forGetter(p_237056_0_ -> p_237056_0_.name),
               Codec.FLOAT.fieldOf("scale").orElse(1.0F).forGetter(p_237055_0_ -> p_237055_0_.modelScale),
               Codec.INT.fieldOf("rarity").orElse(0).forGetter(p_237054_0_ -> p_237054_0_.rarity),
               Codec.FLOAT.fieldOf("attack").orElse(-1.0F).forGetter(p_237054_0_ -> p_237054_0_.attack),
               Codec.FLOAT.fieldOf("health").orElse(-1.0F).forGetter(p_237054_0_ -> p_237054_0_.health),
               ComplexMobTerrestrial.ActivityType.CODEC
                  .fieldOf("activityType")
                  .orElse(ComplexMobTerrestrial.ActivityType.INSOMNIAC)
                  .forGetter(p_237052_0_ -> p_237052_0_.activityType),
               Codec.STRING.fieldOf("favourite_food").orElse("").forGetter(p_237052_0_ -> p_237052_0_.favouriteFood_input),
               Codec.INT.fieldOf("growing_time").orElse(1).forGetter(p_237054_0_ -> p_237054_0_.growing_time),
               Codec.INT.fieldOf("offspring").orElse(1).forGetter(p_237054_0_ -> p_237054_0_.offspring),
               Codec.STRING.fieldOf("breeding_season").orElse("ANY").forGetter(p_237054_0_ -> p_237054_0_.breeding_season),
               Codec.unboundedMap(Codec.STRING, Codec.STRING).fieldOf("sounds").orElse(Collections.emptyMap()).forGetter(p_237052_0_ -> p_237052_0_.sounds),
               Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("flags").orElse(Collections.emptyMap()).forGetter(p_237054_0_ -> p_237054_0_.flags),
               SpeciesDataHolder.CODEC.listOf().fieldOf("species").orElse(new ArrayList<SpeciesDataHolder>()).forGetter((EntityDataHolder p_237052_0_) -> p_237052_0_.speciesData)
            )
            .apply(p_237051_0_, EntityDataHolder::new)
   );
   private final String name;
   private final float modelScale;
   private final int rarity;
   private final float attack;
   private final float health;
   private final ComplexMobTerrestrial.ActivityType activityType;
   private final String favouriteFood_input;
   private final ItemStack favouriteFood;
   private final int growing_time;
   private final int offspring;
   private final String breeding_season;
   public final Map<String, String> sounds;
   private final Map<String, Integer> flags;
   private final List<SpeciesDataHolder> speciesData;

   public EntityDataHolder(
      String p_i232114_1_,
      float p_i232114_2_,
      int p_i232114_3_,
      float attack,
      float health,
      ComplexMobTerrestrial.ActivityType activityType,
      String favouriteFood,
      int growing_time,
      int offspring,
      String breeding,
      Map<String, String> sounds,
      Map<String, Integer> flags,
      List<SpeciesDataHolder> speciesData
   ) {
      this.name = p_i232114_1_;
      this.modelScale = p_i232114_2_;
      this.rarity = p_i232114_3_;
      this.attack = attack;
      this.health = health;
      this.activityType = activityType;
      this.favouriteFood_input = favouriteFood;
      this.favouriteFood = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(this.favouriteFood_input)));
      this.growing_time = growing_time;
      this.offspring = offspring;
      this.breeding_season = breeding;
      this.sounds = sounds;
      this.flags = flags;
      this.speciesData = speciesData;
   }

   public String getString() {
      return this.name
         + ": Scale: "
         + this.modelScale
         + " Rarity: "
         + this.rarity
         + " Attack: "
         + this.attack
         + " Health: "
         + this.health
         + " Ambient Sound: "
         + this.sounds;
   }

   public void printSpeciesData() {
      for (SpeciesDataHolder speciesDatum : this.speciesData) {
         UntamedWilds.LOGGER.info(speciesDatum.getString());
      }
   }

   public String getName(int i) {
      return Objects.equals(this.speciesData.get(i).getName(), "") ? this.name : this.speciesData.get(Math.min(this.speciesData.size() - 1, i)).getName();
   }

   public float getScale(int i) {
      return this.speciesData.get(i).getModelScale() < 0.0F ? this.modelScale : this.speciesData.get(i).getModelScale();
   }

   public int getRarity(int i) {
      return this.speciesData.get(i).getRarity() < 0 ? this.rarity : this.speciesData.get(i).getRarity();
   }

   public float getAttack(int i) {
      return this.speciesData.get(i).getAttack() < 0.0F ? this.modelScale : this.speciesData.get(i).getAttack();
   }

   public float getHealth(int i) {
      return this.speciesData.get(i).getHealth() < 0.0F ? this.modelScale : this.speciesData.get(i).getHealth();
   }

   public ComplexMobTerrestrial.ActivityType getActivityType(int i) {
      return this.speciesData.get(i).getActivityType() == ComplexMobTerrestrial.ActivityType.INSOMNIAC
         ? this.activityType
         : this.speciesData.get(i).getActivityType();
   }

   public ItemStack getFavouriteFood(int i) {
      return this.speciesData.get(i).getFavouriteFood().getItem().builtInRegistryHolder().key().location().toString().equals("minecraft:air")
         ? this.favouriteFood
         : this.speciesData.get(i).getFavouriteFood();
   }

   public int getGrowingTime(int i) {
      return this.speciesData.get(i).getGrowingTime() < 0 ? this.growing_time : this.speciesData.get(i).getGrowingTime();
   }

   public int getOffspring(int i) {
      return this.speciesData.get(i).getOffspring() < 0 ? this.offspring : this.speciesData.get(i).getOffspring();
   }

   public Integer getSkins(int i) {
      return this.speciesData.get(i).getSkins();
   }

   public String getBreedingSeason(int i) {
      return this.speciesData.get(i).getBreedingSeason().equals("NONE") ? this.breeding_season : this.speciesData.get(i).getBreedingSeason();
   }

   @Nullable
   public SoundEvent getSoundsWithAlt(int i, String sound_id, SoundEvent alt_sound) {
      SoundEvent event = this.getSounds(i, sound_id);
      return event == null ? alt_sound : event;
   }

   @Nullable
   public SoundEvent getSounds(int i, String sound_id) {
      if (i != 99 && i >= 0) {
         if (!this.speciesData.get(i).getSounds().isEmpty() && this.speciesData.get(i).getSounds().get(sound_id) != null) {
            return this.speciesData.get(i).getSounds().get(sound_id);
         } else {
            return this.sounds.containsKey(sound_id) && ForgeRegistries.SOUND_EVENTS.containsKey(ResourceLocation.tryParse(this.sounds.get(sound_id)))
               ? (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.tryParse(this.sounds.get(sound_id)))
               : ModSounds.NOTHING;
         }
      } else {
         return ModSounds.NOTHING;
      }
   }

   public Map<String, String> getBaseSounds() {
      return this.sounds;
   }

   public Integer getGroupCount(int i) {
      return !this.speciesData.get(i).getFlags().isEmpty() && this.speciesData.get(i).getFlags().get("groupCount") != null
         ? this.speciesData.get(i).getFlags().get("groupCount")
         : 1;
   }

   public int getFlags(int i, String flag) {
      if (!this.speciesData.get(i).getFlags().isEmpty() && this.speciesData.get(i).getFlags().get(flag) != null) {
         return this.speciesData.get(i).getFlags().get(flag);
      } else if (!this.flags.containsKey(flag)) {
         UntamedWilds.LOGGER.error("Couldn't find " + flag + " flag in ENTITY_DATA");
         return 0;
      } else {
         return this.flags.get(flag);
      }
   }

   public List<SpeciesDataHolder> getSpeciesData() {
      return this.speciesData;
   }
}
