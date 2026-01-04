package untamedwilds.init;

import java.util.function.Supplier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.item.ChumItem;
import untamedwilds.item.LardItem;
import untamedwilds.item.MobBottledItem;
import untamedwilds.item.MobBucketedItem;
import untamedwilds.item.MobEggItem;
import untamedwilds.item.MobSpawnItem;
import untamedwilds.item.OwnershipDeedItem;
import untamedwilds.item.debug.AnalyzerItem;
import untamedwilds.item.debug.EraserItem;
import untamedwilds.item.debug.GrowthTonicItem;
import untamedwilds.item.debug.HighlighterItem;
import untamedwilds.item.debug.IpecacItem;
import untamedwilds.item.debug.LovePotionItem;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.MOD
)
public class ModItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "untamedwilds");
   public static RegistryObject<Item> LOGO = createItem("logo", () -> new Item(new Properties().stacksTo(1).rarity(Rarity.EPIC)));
   public static RegistryObject<Item> OWNERSHIP_DEED = createItem("ownership_deed", () -> new OwnershipDeedItem(new Properties().stacksTo(1)));
   public static RegistryObject<Item> DEBUG_ERASER = createItem("debug_eraser", () -> new EraserItem(new Properties().stacksTo(1).rarity(Rarity.EPIC)));
   public static RegistryObject<Item> DEBUG_ANALYZER = createItem("debug_analyzer", () -> new AnalyzerItem(new Properties().stacksTo(1).rarity(Rarity.EPIC)));
   public static RegistryObject<Item> DEBUG_IPECAC = createItem("debug_ipecac", () -> new IpecacItem(new Properties().stacksTo(1).rarity(Rarity.EPIC)));
   public static RegistryObject<Item> DEBUG_LOVE_POTION = createItem(
      "debug_love_potion", () -> new LovePotionItem(new Properties().stacksTo(1).rarity(Rarity.EPIC))
   );
   public static RegistryObject<Item> DEBUG_GROWTH_TONIC = createItem(
      "debug_growth_tonic", () -> new GrowthTonicItem(new Properties().stacksTo(1).rarity(Rarity.EPIC))
   );
   public static RegistryObject<Item> DEBUG_HIGHLIGHTER = createItem(
      "debug_highlighter", () -> new HighlighterItem(new Properties().stacksTo(1).rarity(Rarity.EPIC))
   );
   public static RegistryObject<Item> MATERIAL_FAT = createItem(
      "material_fat", () -> new LardItem(new Properties().food(new Builder().nutrition(1).saturationMod(1.0F).meat().build()))
   );
   public static RegistryObject<Item> MATERIAL_BLUBBER = createItem(
      "material_blubber", () -> new LardItem(new Properties().food(new Builder().nutrition(1).saturationMod(1.0F).meat().build())) {
            public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
               return 1200;
            }
         }
   );
   public static RegistryObject<Item> MATERIAL_PEARL = createItem("material_pearl", () -> new Item(new Properties()));
   public static RegistryObject<Item> RARE_GIANT_PEARL = createItem("material_giant_pearl", () -> new Item(new Properties().rarity(Rarity.UNCOMMON)));
   public static RegistryObject<Item> MATERIAL_SNAKE_SKIN = createItem("material_snake_skin", () -> new Item(new Properties()));
   public static RegistryObject<Item> CHUM = createItem(
      "chum",
      () -> new ChumItem(
            new Properties()
               .food(new Builder().nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 1200, 0), 1.0F).meat().build())
         )
   );
   public static RegistryObject<Item> MATERIAL_SHARK_TOOTH = createItem("material_shark_tooth", () -> new Item(new Properties()));
   public static RegistryObject<Item> MEAT_BEAR_RAW = createItem(
      "food_bear_raw", () -> new Item(new Properties().food(new Builder().nutrition(3).saturationMod(0.6F).meat().build()))
   );
   public static RegistryObject<Item> MEAT_BEAR_COOKED = createItem(
      "food_bear_cooked", () -> new Item(new Properties().food(new Builder().nutrition(7).saturationMod(1.0F).meat().build()))
   );
   public static RegistryObject<Item> MEAT_TURTLE_RAW = createItem(
      "food_turtle_raw", () -> new Item(new Properties().food(new Builder().nutrition(2).saturationMod(0.3F).meat().build()))
   );
   public static RegistryObject<Item> MEAT_TURTLE_COOKED = createItem(
      "food_turtle_cooked", () -> new Item(new Properties().food(new Builder().nutrition(6).saturationMod(0.6F).meat().build()))
   );
   public static RegistryObject<Item> MEAT_HIPPO_RAW = createItem(
      "food_pachyderm_raw", () -> new Item(new Properties().food(new Builder().nutrition(3).saturationMod(0.7F).meat().build()))
   );
   public static RegistryObject<Item> MEAT_HIPPO_COOKED = createItem(
      "food_pachyderm_cooked", () -> new Item(new Properties().food(new Builder().nutrition(7).saturationMod(1.1F).meat().build()))
   );
   public static RegistryObject<Item> FOOD_TURTLE_SOUP = createItem(
      "food_turtle_soup", () -> new BowlFoodItem(new Properties().food(new Builder().nutrition(8).saturationMod(0.6F).build()).stacksTo(1))
   );
   public static RegistryObject<Item> FOOD_PEMMICAN = createItem(
      "food_pemmican", () -> new Item(new Properties().food(new Builder().nutrition(6).saturationMod(1.0F).build()))
   );
   public static RegistryObject<Item> VEGETABLE_AARDVARK_CUCUMBER = createItem(
      "food_aardvark_cucumber", () -> new Item(new Properties().food(new Builder().nutrition(3).saturationMod(0.2F).build()))
   );
   public static RegistryObject<Item> FOOD_HEMLOCK_STEW = createItem(
      "food_hemlock_stew",
      () -> new BowlFoodItem(
            new Properties()
               .food(new Builder().nutrition(6).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.POISON, 1200, 3), 1.0F).alwaysEat().build())
               .stacksTo(1)
         )
   );
   public static RegistryObject<Item> FOOD_HAKARL = createItem(
      "food_hakarl",
      () -> new Item(
            new Properties()
               .food(new Builder().nutrition(5).saturationMod(0.7F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.4F).build())
         )
   );
   public static RegistryObject<Item> HIDE_ASHEN = createItem("hide_ashen", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_BEIGE = createItem("hide_beige", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_BLACK = createItem("hide_black", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_BROWN = createItem("hide_brown", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_GOLDEN = createItem("hide_golden", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_GRAY = createItem("hide_gray", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_ORANGE = createItem("hide_orange", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_TAN = createItem("hide_tan", () -> new Item(new Properties()));
   public static RegistryObject<Item> HIDE_WHITE = createItem("hide_white", () -> new Item(new Properties()));
   public static RegistryObject<Item> SEED_TITAN_ARUM = createItem(
      "flora_titan_arum_corm", () -> new ItemNameBlockItem((Block)ModBlock.TITAN_ARUM.get(), new Properties())
   );
   public static RegistryObject<Item> SEED_ZIMBABWE_ALOE = createItem(
      "flora_zimbabwe_aloe_sapling", () -> new ItemNameBlockItem((Block)ModBlock.ZIMBABWE_ALOE.get(), new Properties())
   );
   public static RegistryObject<Item> WATER_HYACINTH_BLOCK = createItem(
      "flora_water_hyacinth_item", () -> new PlaceOnWaterBlockItem((Block)ModBlock.WATER_HYACINTH.get(), new Properties())
   );

   public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
      return ITEMS.register(name, supplier);
   }

   public static void registerSpawnItems() {
      ITEMS.register("egg_tarantula", () -> new MobEggItem(ModEntity.TARANTULA, new Properties()));
      ITEMS.register("bottle_tarantula", () -> new MobBottledItem(ModEntity.TARANTULA, new Properties()));
      ITEMS.register("egg_butterfly", () -> new MobEggItem(ModEntity.BUTTERFLY, new Properties()));
      ITEMS.register("bottle_butterfly", () -> new MobBottledItem(ModEntity.BUTTERFLY, new Properties()));
      ITEMS.register("egg_snake", () -> new MobEggItem(ModEntity.SNAKE, new Properties()));
      ITEMS.register("spawn_snake", () -> new MobSpawnItem(ModEntity.SNAKE, new Properties()));
      ITEMS.register("egg_softshell_turtle", () -> new MobEggItem(ModEntity.SOFTSHELL_TURTLE, new Properties()));
      ITEMS.register("spawn_softshell_turtle", () -> new MobSpawnItem(ModEntity.SOFTSHELL_TURTLE, new Properties()));
      ITEMS.register("egg_giant_clam", () -> new MobEggItem(ModEntity.GIANT_CLAM, new Properties()));
      ITEMS.register("spawn_giant_clam", () -> new MobSpawnItem(ModEntity.GIANT_CLAM, new Properties()));
      ITEMS.register("egg_sunfish", () -> new MobEggItem(ModEntity.SUNFISH, new Properties()));
      ITEMS.register("egg_trevally", () -> new MobEggItem(ModEntity.TREVALLY, new Properties()));
      ITEMS.register("bucket_trevally", () -> new MobBucketedItem(ModEntity.TREVALLY, Fluids.WATER, new Properties()));
      ITEMS.register("egg_arowana", () -> new MobEggItem(ModEntity.AROWANA, new Properties()));
      ITEMS.register("bucket_arowana", () -> new MobBucketedItem(ModEntity.AROWANA, Fluids.WATER, new Properties()));
      ITEMS.register("egg_football_fish", () -> new MobEggItem(ModEntity.FOOTBALL_FISH, new Properties()));
      ITEMS.register("bucket_football_fish", () -> new MobBucketedItem(ModEntity.FOOTBALL_FISH, Fluids.WATER, new Properties()));
      ITEMS.register("egg_giant_salamander", () -> new MobEggItem(ModEntity.GIANT_SALAMANDER, new Properties()));
      ITEMS.register("bucket_giant_salamander", () -> new MobBucketedItem(ModEntity.GIANT_SALAMANDER, Fluids.WATER, new Properties()));
      ITEMS.register("egg_newt", () -> new MobEggItem(ModEntity.NEWT, new Properties()));
      ITEMS.register("bucket_newt", () -> new MobBucketedItem(ModEntity.NEWT, Fluids.WATER, new Properties()));
      ITEMS.register("egg_tortoise", () -> new MobEggItem(ModEntity.TORTOISE, new Properties()));
      ITEMS.register("spawn_tortoise", () -> new MobSpawnItem(ModEntity.TORTOISE, new Properties()));
      ITEMS.register("egg_large_snake", () -> new MobEggItem(ModEntity.ANACONDA, new Properties()));
      ITEMS.register("egg_triggerfish", () -> new MobEggItem(ModEntity.TRIGGERFISH, new Properties()));
      ITEMS.register("bucket_triggerfish", () -> new MobBucketedItem(ModEntity.TRIGGERFISH, Fluids.WATER, new Properties()));
      ITEMS.register("egg_catfish", () -> new MobEggItem(ModEntity.CATFISH, new Properties()));
      ITEMS.register("bucket_catfish", () -> new MobBucketedItem(ModEntity.CATFISH, Fluids.WATER, new Properties()));
      ITEMS.register("egg_king_crab", () -> new MobEggItem(ModEntity.KING_CRAB, new Properties()));
      ITEMS.register("bucket_king_crab", () -> new MobBucketedItem(ModEntity.KING_CRAB, Fluids.WATER, new Properties()));
      ITEMS.register("egg_monitor", () -> new MobEggItem(ModEntity.MONITOR, new Properties()));
      ITEMS.register("egg_spadefish", () -> new MobEggItem(ModEntity.SPADEFISH, new Properties()));
      ITEMS.register("bucket_spadefish", () -> new MobBucketedItem(ModEntity.SPADEFISH, Fluids.WATER, new Properties()));
      ITEMS.register("egg_terror_bird", () -> new MobEggItem(ModEntity.TERROR_BIRD, new Properties()));
   }
}
