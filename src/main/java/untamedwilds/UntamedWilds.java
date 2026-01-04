package untamedwilds;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import untamedwilds.block.CageBlock;
import untamedwilds.compat.CompatBridge;
import untamedwilds.config.ConfigBase;
import untamedwilds.init.ModAdvancementTriggers;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModEntity;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModParticles;
import untamedwilds.init.ModSounds;
import untamedwilds.network.UntamedInstance;
import untamedwilds.util.EntityDataListenerEvent;
import untamedwilds.util.ModCreativeModeTab;
import untamedwilds.world.UntamedWildsBiomeModifier;
import untamedwilds.world.UntamedWildsGenerator;

@Mod("untamedwilds")
@EventBusSubscriber(
   modid = "untamedwilds"
)
public class UntamedWilds {
   public static final Logger LOGGER = LogManager.getLogger();
   public static final String MOD_ID = "untamedwilds";
   public static final boolean DEBUG = false;

   public UntamedWilds() {
      IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
      eventBus.addListener(this::setupCommon);
      eventBus.addListener(this::setupClient);
      ModBlock.BLOCKS.register(eventBus);
      ModBlock.TILE_ENTITIES.register(eventBus);
      ModItems.ITEMS.register(eventBus);
      ModEntity.ENTITIES.register(eventBus);
      ModItems.registerSpawnItems();
      UntamedWildsBiomeModifier.BIOME_MODIFIER_SERIALIZERS.register(eventBus);
      ModSounds.SOUNDS.register(eventBus);
      ModParticles.PARTICLES.register(eventBus);
      ModAdvancementTriggers.register();
      UntamedWildsGenerator.FEATURES.register(eventBus);
      UntamedWildsGenerator.CONFIGURED_FEATURES.register(eventBus);
      UntamedWildsGenerator.PLACED_FEATURES.register(eventBus);
      ModCreativeModeTab.CREATIVE_TABS.register(eventBus);
      ModLoadingContext.get().registerConfig(Type.COMMON, ConfigBase.common_config);
      ConfigBase.loadConfig(ConfigBase.common_config, FMLPaths.CONFIGDIR.get().resolve("untamedwilds-common.toml").toString());
      CompatBridge.RegisterCompat();
   }

   private void setupCommon(FMLCommonSetupEvent event) {
      UntamedInstance.registerMessages();
      DispenserBlock.registerBehavior(((Block)ModBlock.TRAP_CAGE.get()).asItem(), new CageBlock.DispenserBehaviorTrapCage());
   }

   private void setupClient(FMLClientSetupEvent event) {
      EntityDataListenerEvent.registerData();
   }
}
