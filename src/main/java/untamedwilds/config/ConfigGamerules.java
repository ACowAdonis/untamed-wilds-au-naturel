package untamedwilds.config;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigGamerules {
   public static BooleanValue naturalBreeding;
   public static BooleanValue hardcoreBreeding;
   public static BooleanValue easyBreeding;
   public static BooleanValue genderedBreeding;
   public static BooleanValue hardcoreDeath;
   public static BooleanValue playerBreeding;
   public static BooleanValue randomSpecies;
   public static BooleanValue scientificNames;
   public static BooleanValue grazerGriefing;
   public static BooleanValue mobGriefing;
   public static BooleanValue angrySleepers;
   public static BooleanValue contactAgression;
   public static BooleanValue mobsLayEggs;
   public static BooleanValue spyglassBehaviorChange;
   public static IntValue spyglassCheckRange;
   public static BooleanValue sleepBehaviour;
   public static BooleanValue easyMobCapturing;
   public static BooleanValue preventMobsOnWater;
   public static IntValue cycleLength;
   public static DoubleValue rareSkinChance;
   public static BooleanValue wildRareSkins;
   public static BooleanValue attackUndead;
   public static BooleanValue predatorPlayerHunting;
   public static DoubleValue predatorHungryHuntChance;
   public static DoubleValue predatorManEaterChance;

   ConfigGamerules(Builder builder) {
      builder.comment("Options pertaining to global Gamerules");
      naturalBreeding = builder.comment("Defines whether animals should breed without Player intervention.").define("gamerules.natural_breeding", true);
      hardcoreBreeding = builder.comment("Adds additional restrictions to mob breeding, including Biome/Temperature requirements and Overcrowding.")
         .define("gamerules.hardcore_breeding", false);
      easyBreeding = builder.comment("Pregnancy time is only used as a cooldown, babies pop out instantly like in Vanilla.")
         .define("gamerules.easy_breeding", false);
      genderedBreeding = builder.comment(
            "Whether breeding requires a Male and a Female to produce offspring/eggs. (Warning: may lead to uncontrolled spawns of eggs)"
         )
         .define("gamerules.gendered_breeding", true);
      playerBreeding = builder.comment("Defines whether players can trigger breeding by feeding a creature's favourite item, like in vanilla.")
         .define("gamerules.player_breeding", false);
      randomSpecies = builder.comment("Allows mobs to spawn as fully random species, ignoring Biomes and Rarity.").define("gamerules.random_species", false);
      rareSkinChance = builder.comment("Chance for a mob, out of 1, to have it's Skin replaced by a Rare skin (if any are defined through assets)")
         .defineInRange("gamerules.rare_skin_chance", 0.05, 0.0, 1.0);
      wildRareSkins = builder.comment("Should mobs with Rare skins generate in the wild (if defined through assets)").define("gamerules.wild_rare_skins", true);
      spyglassBehaviorChange = builder.comment("Should the vanilla Spyglass display information when a player looks at a mob. Set to false to disable.")
         .define("gamerules.spyglass_behavior_change", true);
      spyglassCheckRange = builder.comment(
            "Range up to which the Spyglass will identify mobs and give information, this length is not in blocks, and is roughly equivalent to a Render distance of 12."
         )
         .defineInRange("gamerules.spyglass_range", 5000, 0, Integer.MAX_VALUE);
      hardcoreDeath = builder.comment(
            "Disable this option to have tamed mobs respawn in their home with half a Heart if they were to 'die' (IMPORTANT: This gamerule is NOT fully functional and using it as a free get-out-of-jail card is bound to be disappointing, use at your own risk)."
         )
         .define("gamerules.hardcore_death", true);
      scientificNames = builder.comment("Features scientific names in various descriptions (eg. for mobs inside Cage Traps).")
         .define("gamerules.scientific_names", true);
      grazerGriefing = builder.comment("Should 'Grazing' mobs destroy Tall Grass and/or turn Grass into dirt blocks (like Vanilla Sheep do).")
         .define("gamerules.grazer_griefing", true);
      mobGriefing = builder.comment("Should mobs potentially destroy the terrain? Keep in mind 'mobGriefing' is still required")
         .define("gamerules.mob_griefing", false);
      mobsLayEggs = builder.comment("If set to false, prevents mobs from dropping eggs").define("gamerules.mobs_drop_eggs", false);
      angrySleepers = builder.comment("Defines whether certain large predators will be angered if a player approaches them while they are sleeping.")
         .define("gamerules.angry_sleepers", true);
      contactAgression = builder.comment("Defines whether certain critters will become angry if a mob/player 'steps' on them, by coming too close.")
         .define("gamerules.contact_agression", true);
      attackUndead = builder.comment(
            "Defines whether animals should actively target and hunt Undead mobs. Disabling this option should make mobs less prone to dying due to angering a Zombie horde"
         )
         .define("gamerules.attack_undead", true);
      predatorPlayerHunting = builder.comment(
            "Enables large predators (bears, big cats, hyenas) to occasionally view players as prey. Sneaking players are exempt."
         )
         .define("gamerules.predator_player_hunting", true);
      predatorHungryHuntChance = builder.comment(
            "When a predator is hungry (hunger <= 30), chance per second to target a nearby player as prey. 0.02 = 2%"
         )
         .defineInRange("gamerules.predator_hungry_hunt_chance", 0.02, 0.0, 1.0);
      predatorManEaterChance = builder.comment(
            "Chance on spawn for a predator to become a 'man-eater' that will hunt players regardless of hunger. 0.05 = 5%"
         )
         .defineInRange("gamerules.predator_man_eater_chance", 0.05, 0.0, 1.0);
      preventMobsOnWater = builder.comment("Defines whether non-aquatic mobs are allowed to spawn in water. Leads to less mobs in the world.")
         .define("gamerules.block_spawns_in_water", false);
      sleepBehaviour = builder.comment("Should the 'Sleeping' behaviour run? Disabling this option also disables the activity")
         .define("gamerules.mob_sleeping", true);
      easyMobCapturing = builder.comment("If set to false, makes mobs a lot harder to catch by preventing the capture of hostile mobs")
         .define("gamerules.easy_mob_capture", true);
      cycleLength = builder.comment(
            new String[]{
               "Defines how long a cycle should last, cycles are used to scale the gestation and breeding periods",
               "Example values: 24000 - Day, 168000 - Week, 720000 - Month, 8760000 - Year"
            }
         )
         .defineInRange("gamerules.cycle_length", 24000, 0, 8760000);
   }
}
