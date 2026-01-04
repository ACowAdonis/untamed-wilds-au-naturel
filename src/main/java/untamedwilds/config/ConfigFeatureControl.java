package untamedwilds.config;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigFeatureControl {
   public static BooleanValue addAnemones;
   public static BooleanValue addReeds;
   public static BooleanValue addFlora;
   public static BooleanValue addAlgae;
   public static BooleanValue addTreeOrchids;
   public static BooleanValue addBurrows;
   public static ConfigValue<List<? extends String>> reedBlacklist;
   public static ConfigValue<List<? extends String>> floraBlacklist;
   public static ConfigValue<List<? extends String>> algaeBlacklist;
   public static IntValue freqReeds;
   public static IntValue freqFlora;
   public static IntValue freqAlgae;
   public static IntValue freqCritter;
   public static IntValue freqWater;
   public static IntValue freqSessile;
   public static IntValue freqOcean;
   public static IntValue freqApex;
   public static IntValue freqHerbivores;
   public static IntValue probUnderground;
   public static ConfigValue<List<? extends String>> dimensionFeatureBlacklist;
   public static HashMap<String, BooleanValue> options = new HashMap<>();
   private final Builder builder;

   ConfigFeatureControl(Builder builder) {
      this.builder = builder;
      builder.comment("Options pertaining to blocks and their world generation");
      addAnemones = this.define("gencontrol.anemone", true, "Controls whether to add Anemones and their associated items to oceans.");
      addReeds = this.define("gencontrol.reeds", true, "Controls whether to spawn Reeds in River/Swamp biomes");
      addFlora = this.define("gencontrol.bush", true, "Controls whether to spawn random Flora in the world");
      addTreeOrchids = this.define("gencontrol.tree_orchid", true, "Controls whether to add Tree Orchids (NOT YET IMPLEMENTED)");
      addAlgae = this.define("gencontrol.algae", true, "Controls whether to spawn Amazon Sword in Swamp/Jungle biomes");
      addBurrows = this.define(
         "gencontrol.burrows", true, "Controls whether to use Burrows to spawn Critters, if disabled, Critters will spawn in the world like normal mobs."
      );
      reedBlacklist = builder.comment("Prevent spawns of Reeds in these biomes")
         .defineList("gencontrol.reed_blacklist", Lists.newArrayList(), string -> string instanceof String);
      freqReeds = builder.comment("Frequency of Reeds, 1 in N chunks will generate Reeds (0 to disable)")
         .defineInRange("gencontrol.freqreeds", 4, 0, Integer.MAX_VALUE);
      floraBlacklist = builder.comment("Prevent spawns of Flora in these biomes")
         .defineList("gencontrol.flora_blacklist", Lists.newArrayList(), string -> string instanceof String);
      freqFlora = builder.comment("Frequency of Flora, 1 in N chunks will generate random Flora (0 to disable)")
         .defineInRange("gencontrol.freqflora", 4, 0, Integer.MAX_VALUE);
      algaeBlacklist = builder.comment("Prevent spawns of Algae in these biomes")
         .defineList("gencontrol.algae_blacklist", Arrays.asList("minecraft:frozen_ocean", "minecraft:deep_frozen_ocean"), string -> string instanceof String);
      freqAlgae = builder.comment("Frequency of Algae, abstract value (0 to disable)").defineInRange("gencontrol.freqalgae", 1, 0, Integer.MAX_VALUE);
      freqCritter = builder.comment("Frequency of Critters, 1 in N chunks will generate with Critters (0 to disable)")
         .defineInRange("gencontrol.freqcritter", 6, 0, Integer.MAX_VALUE);
      freqSessile = builder.comment("Frequency of Sessile Ocean Mobs, 1 in N chunks will generate with Sessile Mobs (0 to disable)")
         .defineInRange("gencontrol.freqsessile", 8, 0, Integer.MAX_VALUE);
      freqOcean = builder.comment("Frequency of Ocean Mobs, 1 in N chunks will generate with Ocean Mobs (0 to disable)")
         .defineInRange("gencontrol.freqocean", 16, 0, Integer.MAX_VALUE);
      freqApex = builder.comment("Frequency of Apex Predators, 1 in N chunks will generate with an Apex Predator (0 to disable)")
         .defineInRange("gencontrol.freqapex", 64, 0, Integer.MAX_VALUE);
      freqHerbivores = builder.comment("Frequency of Herbivores, 1 in N chunks will generate with an Apex Predator (0 to disable)")
         .defineInRange("gencontrol.freqherbivore", 48, 0, Integer.MAX_VALUE);
      freqWater = builder.comment("Frequency of Freshwater Mobs, 1 in N chunks will generate with Freshwater Mobs (0 to disable)")
         .defineInRange("gencontrol.freqwater", 8, 0, Integer.MAX_VALUE);
      probUnderground = builder.comment("Frequency of Underground mobs, N attempts to spawn a mob will be made on each chunk (0 to disable)")
         .defineInRange("gencontrol.probunderground", 1, 0, Integer.MAX_VALUE);
      dimensionFeatureBlacklist = builder.comment("Prevent flora and other blocks (besides Burrows) from generating in the defined dimensions.")
         .defineList("gencontrol.dimensionFeatureBlacklist", Lists.newArrayList(), string -> string instanceof String);
   }

   private BooleanValue define(String name, boolean value, String comment) {
      BooleanValue option = this.builder.comment(comment).define(name, value);
      options.put(name, option);
      return option;
   }
}
