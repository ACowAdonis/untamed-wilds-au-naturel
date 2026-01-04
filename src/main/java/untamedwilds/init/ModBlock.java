package untamedwilds.init;

import java.util.function.Supplier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.block.AlgaeBlock;
import untamedwilds.block.AnemoneBlock;
import untamedwilds.block.CageBlock;
import untamedwilds.block.CarpetBlock;
import untamedwilds.block.CritterBurrowBlock;
import untamedwilds.block.CustomGrassBlock;
import untamedwilds.block.EpyphitePlantBlock;
import untamedwilds.block.FeederBlock;
import untamedwilds.block.FloatingPlantBlock;
import untamedwilds.block.LardBlock;
import untamedwilds.block.NestReptileBlock;
import untamedwilds.block.ReedBlock;
import untamedwilds.block.SharkMeatBlock;
import untamedwilds.block.StrangeEggBlock;
import untamedwilds.block.TallGrassBlock;
import untamedwilds.block.TallPlantBlock;
import untamedwilds.block.TitanArumBlock;
import untamedwilds.block.UndergrowthBlock;
import untamedwilds.block.UndergrowthPoisonousBlock;
import untamedwilds.block.blockentity.CageBlockEntity;
import untamedwilds.block.blockentity.CritterBurrowBlockEntity;
import untamedwilds.block.blockentity.EggBlockEntity;
import untamedwilds.block.blockentity.ReptileNestBlockEntity;

@EventBusSubscriber(
   modid = "untamedwilds",
   bus = Bus.MOD
)
public class ModBlock {
   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "untamedwilds");
   public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "untamedwilds");
   public static RegistryObject<Block> CARPET_STRAW = createBlock(
      "carpet_straw", () -> new CarpetBlock(Properties.of().mapColor(MapColor.SAND).ignitedByLava().destroyTime(0.1F).sound(SoundType.CROP))
   );
   public static RegistryObject<Block> CARPET_ASHEN = createBlock(
      "carpet_ashen", () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_GRAY).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BEIGE = createBlock(
      "carpet_beige", () -> new CarpetBlock(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BLACK = createBlock(
      "carpet_black", () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_BLACK).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BROWN = createBlock(
      "carpet_brown", () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_GOLDEN = createBlock(
      "carpet_golden", () -> new CarpetBlock(Properties.of().mapColor(MapColor.GOLD).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_GRAY = createBlock(
      "carpet_gray", () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_ORANGE = createBlock(
      "carpet_orange", () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_ORANGE).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_TAN = createBlock(
      "carpet_tan", () -> new CarpetBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_WHITE = createBlock(
      "carpet_white", () -> new CarpetBlock(Properties.of().mapColor(MapColor.SNOW).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BIGCAT_JAGUAR = createBlock(
      "carpet_bigcat_jaguar",
      () -> new CarpetBlock(Properties.of().mapColor(MapColor.GOLD).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BIGCAT_LEOPARD = createBlock(
      "carpet_bigcat_leopard",
      () -> new CarpetBlock(Properties.of().mapColor(MapColor.GOLD).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BIGCAT_SNOW = createBlock(
      "carpet_bigcat_snow_leopard",
      () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_BLACK).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> CARPET_BIGCAT_TIGER = createBlock(
      "carpet_bigcat_tiger",
      () -> new CarpetBlock(Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().destroyTime(0.1F).sound(SoundType.WOOL))
   );
   public static RegistryObject<Block> SHARK_MEAT = createBlock(
      "shark_meat", () -> new SharkMeatBlock(Properties.of().mapColor(MapColor.COLOR_GRAY).destroyTime(1.0F).sound(SoundType.CORAL_BLOCK).randomTicks())
   );
   public static RegistryObject<Block> SHARK_MEAT_FERMENTED = createBlock(
      "shark_meat_fermented", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).destroyTime(1.0F).sound(SoundType.CORAL_BLOCK))
   );
   public static RegistryObject<Block> LARD_BLOCK = createBlock(
      "block_lard", () -> new LardBlock(Properties.of().mapColor(MapColor.CLAY).ignitedByLava().destroyTime(0.1F).sound(SoundType.SLIME_BLOCK))
   );
   public static RegistryObject<Block> PEARL_BLOCK = createBlock(
      "block_pearl", () -> new Block(Properties.of().mapColor(MapColor.COLOR_CYAN).destroyTime(5.0F).sound(SoundType.STONE))
   );
   public static RegistryObject<Block> TRAP_CAGE = createBlock(
      "trap_cage", () -> new CageBlock(Properties.of().mapColor(MapColor.WOOD).ignitedByLava().destroyTime(3.0F).sound(SoundType.WOOD))
   );
   public static RegistryObject<Block> FEEDER = createBlock(
      "feeder",
      () -> new FeederBlock(
            Properties.of()
               .mapColor(MapColor.TERRACOTTA_ORANGE)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.3F)
               .sound(SoundType.STONE)
               .noOcclusion()
               .randomTicks()
         )
   );
   public static RegistryObject<Block> ANEMONE_ROSE_BULB = createBlock(
      "anemone_rose_bulb",
      () -> new AnemoneBlock(
            Properties.of()
               .mapColor(MapColor.COLOR_RED)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.1F)
               .sound(SoundType.SLIME_BLOCK)
               .dynamicShape()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> ANEMONE_SAND = createBlock(
      "anemone_sand",
      () -> new AnemoneBlock(
            Properties.of()
               .mapColor(MapColor.COLOR_PINK)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.1F)
               .sound(SoundType.SLIME_BLOCK)
               .dynamicShape()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> ANEMONE_SEBAE = createBlock(
      "anemone_sebae",
      () -> new AnemoneBlock(
            Properties.of()
               .mapColor(MapColor.COLOR_LIGHT_GRAY)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.1F)
               .sound(SoundType.SLIME_BLOCK)
               .dynamicShape()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> COMMON_REED = createBlock(
      "flora_common_reed",
      () -> new ReedBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.1F)
               .sound(SoundType.VINE)
               .noCollission()
               .randomTicks()
               .offsetType(OffsetType.XYZ)
         ),
      100
   );
   public static RegistryObject<Block> BUSH_TEMPERATE = createBlock(
      "flora_bush_temperate",
      () -> new UndergrowthBlock(
            Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).destroyTime(1.0F).sound(SoundType.AZALEA_LEAVES).noCollission()
         )
   );
   public static RegistryObject<Block> BUSH_CREOSOTE = createBlock(
      "flora_bush_creosote",
      () -> new UndergrowthBlock(
            Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).destroyTime(1.0F).sound(SoundType.AZALEA_LEAVES).noCollission()
         )
   );
   public static RegistryObject<Block> ELEPHANT_EAR = createBlock(
      "flora_elephant_ear",
      () -> new UndergrowthBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(1.0F)
               .sound(SoundType.WET_GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> HEMLOCK = createBlock(
      "flora_hemlock",
      () -> new UndergrowthPoisonousBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.1F)
               .sound(SoundType.GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> YARROW = createBlock(
      "flora_yarrow",
      () -> new CustomGrassBlock(
            MobEffects.REGENERATION,
            4,
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> JUNEGRASS = createBlock(
      "flora_junegrass",
      () -> new CustomGrassBlock(
            MobEffects.UNLUCK,
            4,
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> CANOLA = createBlock(
      "flora_canola",
      () -> new CustomGrassBlock(
            MobEffects.DAMAGE_BOOST,
            4,
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> ZIMBABWE_ALOE = createItemlessBlock(
      "flora_zimbabwe_aloe",
      () -> new TallPlantBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(1.0F)
               .sound(SoundType.WOOD)
               .dynamicShape()
               .randomTicks()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> PAMPAS_GRASS = createBlock(
      "flora_pampas_grass",
      () -> new TallGrassBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(1.0F)
               .sound(SoundType.GRASS)
               .dynamicShape()
               .randomTicks()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> WATER_HYACINTH = createItemlessBlock(
      "flora_water_hyacinth",
      () -> new FloatingPlantBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.LILY_PAD)
               .noCollission()
               .offsetType(OffsetType.XZ)
         )
   );
   public static RegistryObject<Block> AMAZON_SWORD = createBlock(
      "flora_amazon_sword",
      () -> new AlgaeBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.WET_GRASS)
               .noCollission()
               .offsetType(OffsetType.XYZ)
         )
   );
   public static RegistryObject<Block> EELGRASS = createBlock(
      "flora_eelgrass",
      () -> new AlgaeBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(0.0F)
               .sound(SoundType.WET_GRASS)
               .noCollission()
               .offsetType(OffsetType.XZ)
         )
   );
   public static RegistryObject<Block> ORCHID_MAGENTA = createBlock(
      "flora_orchid_magenta",
      () -> new EpyphitePlantBlock(
            Properties.of().mapColor(MapColor.COLOR_MAGENTA).pushReaction(PushReaction.DESTROY).destroyTime(0.0F).sound(SoundType.VINE)
         )
   );
   public static RegistryObject<Block> ORCHID_PURPLE = createBlock(
      "flora_orchid_purple",
      () -> new EpyphitePlantBlock(
            Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).destroyTime(0.0F).sound(SoundType.VINE)
         )
   );
   public static RegistryObject<Block> ORCHID_PINK = createBlock(
      "flora_orchid_pink",
      () -> new EpyphitePlantBlock(
            Properties.of().mapColor(MapColor.COLOR_PINK).pushReaction(PushReaction.DESTROY).destroyTime(0.0F).sound(SoundType.VINE)
         )
   );
   public static RegistryObject<Block> ORCHID_RED = createBlock(
      "flora_orchid_red",
      () -> new EpyphitePlantBlock(
            Properties.of().mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).destroyTime(0.0F).sound(SoundType.VINE)
         )
   );
   public static RegistryObject<Block> TITAN_ARUM = createItemlessBlock(
      "flora_titan_arum",
      () -> new TitanArumBlock(
            Properties.of()
               .mapColor(MapColor.PLANT)
               .pushReaction(PushReaction.DESTROY)
               .destroyTime(2.0F)
               .sound(SoundType.WET_GRASS)
               .dynamicShape()
               .randomTicks()
               .offsetType(OffsetType.XZ)
         )
   );
   public static RegistryObject<Block> NEST_REPTILE = createBlock(
      "nest_reptile",
      () -> new NestReptileBlock(
            Properties.of().mapColor(MapColor.DIRT).pushReaction(PushReaction.DESTROY).destroyTime(1.0F).sound(SoundType.GRAVEL).randomTicks()
         )
   );
   public static RegistryObject<Block> NEST_AMPHIBIAN = createBlock(
      "nest_amphibian",
      () -> new NestReptileBlock(
            Properties.of().mapColor(MapColor.WATER).pushReaction(PushReaction.DESTROY).destroyTime(0.0F).sound(SoundType.SLIME_BLOCK).randomTicks()
         )
   );
   public static RegistryObject<Block> EGG_SPITTER = createBlock(
      "egg_spitter", () -> new StrangeEggBlock(Properties.of().mapColor(MapColor.COLOR_PURPLE).destroyTime(0.5F).sound(SoundType.SLIME_BLOCK).randomTicks())
   );
   public static RegistryObject<Block> BURROW = createBlock(
      "block_burrow",
      () -> new CritterBurrowBlock(Properties.of().mapColor(MapColor.DIRT).destroyTime(1.0F).sound(SoundType.GRAVEL).noCollission().randomTicks())
   );
   public static final RegistryObject<BlockEntityType<CageBlockEntity>> TILE_ENTITY_CAGE = TILE_ENTITIES.register(
      "trap_cage", () -> Builder.of(CageBlockEntity::new, new Block[]{(Block)TRAP_CAGE.get()}).build(null)
   );
   public static final RegistryObject<BlockEntityType<CritterBurrowBlockEntity>> TILE_ENTITY_BURROW = TILE_ENTITIES.register(
      "critter_burrow", () -> Builder.of(CritterBurrowBlockEntity::new, new Block[]{(Block)BURROW.get()}).build(null)
   );
   public static final RegistryObject<BlockEntityType<ReptileNestBlockEntity>> TILE_ENTITY_NEST_REPTILE = TILE_ENTITIES.register(
      "nest_reptile_block_entity", () -> Builder.of(ReptileNestBlockEntity::new, new Block[]{(Block)NEST_REPTILE.get()}).build(null)
   );
   public static final RegistryObject<BlockEntityType<EggBlockEntity>> TILE_ENTITY_EGG = TILE_ENTITIES.register(
      "strange_egg", () -> Builder.of(EggBlockEntity::new, new Block[]{(Block)EGG_SPITTER.get()}).build(null)
   );

   public static <B extends Block> RegistryObject<Block> createBlock(String name, Supplier<? extends B> supplier) {
      return createBlock(name, supplier, 0);
   }

   public static <B extends Block> RegistryObject<Block> createBlock(String name, Supplier<? extends B> supplier, int burnTime) {
      RegistryObject<Block> block = BLOCKS.register(name, supplier);
      ModItems.ITEMS.register(name, () -> new BlockItem((Block)block.get(), new net.minecraft.world.item.Item.Properties()) {
            public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
               return burnTime;
            }
         });
      return block;
   }

   public static <B extends Block> RegistryObject<Block> createItemlessBlock(String name, Supplier<? extends B> supplier) {
      return BLOCKS.register(name, supplier);
   }
}
