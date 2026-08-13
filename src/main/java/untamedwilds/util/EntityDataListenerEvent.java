package untamedwilds.util;

import java.util.HashMap;
import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.UntamedWilds;
import untamedwilds.entity.ComplexMob;
import untamedwilds.init.ModEntity;
import untamedwilds.network.SyncTextureData;
import untamedwilds.network.UntamedInstance;

@EventBusSubscriber(
   modid = "untamedwilds"
)
public class EntityDataListenerEvent {
   public static final JSONLoader<EntityDataHolder> ENTITY_DATA_HOLDERS = new JSONLoader<>("entities", EntityDataHolder.CODEC);
   private static boolean isLoaded = false;

   @SubscribeEvent
   public static void onAddReloadListeners(AddReloadListenerEvent event) {
      event.addListener(ENTITY_DATA_HOLDERS);
      registerData();
   }

   public static void registerData() {
      for (RegistryObject<EntityType<?>> entity : ModEntity.ENTITIES.getEntries()) {
         registerEntityData((EntityType<?>)entity.get());
      }

      isLoaded = true;
   }

   @SubscribeEvent
   public static void onPlayerLogIn(PlayerLoggedInEvent event) {
      UntamedWilds.LOGGER.info("Firing player login event");
      registerData();

      for (EntityType<?> types : ComplexMob.ENTITY_DATA_HASH.keySet()) {
         ResourceLocation entityName = types.builtInRegistryHolder().key().location();
         int size = 0;

         for (SpeciesDataHolder speciesData : ComplexMob.ENTITY_DATA_HASH.get(types).getSpeciesData()) {
            UntamedInstance.sendToClient(
               new SyncTextureData(entityName, speciesData.getName(), speciesData.getSkins(), size++), (ServerPlayer)event.getEntity()
            );
         }
      }
   }

   public static boolean isEntityDataLoaded() {
      return isLoaded;
   }

   public static EntityDataHolder registerEntityData(EntityType<?> typeIn) {
      String nameIn = Objects.requireNonNull(typeIn.builtInRegistryHolder().key().location()).getPath();
      if (ENTITY_DATA_HOLDERS.getData(new ResourceLocation("untamedwilds", nameIn)) != null) {
         EntityDataHolder data = ENTITY_DATA_HOLDERS.getData(new ResourceLocation("untamedwilds", nameIn));
         processData(data, typeIn);
         return data;
      } else {
         return null;
      }
   }

   private static void processData(EntityDataHolder dataIn, EntityType<?> typeIn) {
      ComplexMob.ENTITY_DATA_HASH.put(typeIn, dataIn);
      processSkins(dataIn, typeIn.builtInRegistryHolder().key().location().getPath());

      for (SpeciesDataHolder speciesData : ComplexMob.ENTITY_DATA_HASH.get(typeIn).getSpeciesData()) {
         ComplexMob.CLIENT_DATA_HASH
            .computeIfAbsent(typeIn, k -> new EntityDataHolderClient(new HashMap<>(), new HashMap<>()))
            .species_data.put(speciesData.getVariant(), speciesData.getName());
      }
   }

   private static void processSkins(EntityDataHolder dataIn, String nameIn) {
      for (SpeciesDataHolder speciesDatum : dataIn.getSpeciesData()) {
         EntityUtils.buildSkinArrays(
            nameIn, speciesDatum.getName().toLowerCase(), dataIn, speciesDatum.getVariant(), ComplexMob.TEXTURES_COMMON, ComplexMob.TEXTURES_RARE
         );
      }
   }
}
