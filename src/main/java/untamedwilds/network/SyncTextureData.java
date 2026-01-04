package untamedwilds.network;

import java.util.HashMap;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.registries.ForgeRegistries;
import untamedwilds.entity.ComplexMob;
import untamedwilds.util.EntityDataHolderClient;
import untamedwilds.util.EntityUtils;

public class SyncTextureData {
   private final ResourceLocation entityName;
   private final String speciesName;
   private final Integer skinsData;
   private final Integer id;

   public SyncTextureData(FriendlyByteBuf buf) {
      this.entityName = buf.readResourceLocation();
      this.speciesName = buf.readUtf();
      this.skinsData = buf.readInt();
      this.id = buf.readInt();
   }

   public SyncTextureData(ResourceLocation str, String species_name, Integer skins, Integer id) {
      this.entityName = str;
      this.speciesName = species_name;
      this.skinsData = skins;
      this.id = id;
   }

   public void toBytes(FriendlyByteBuf buf) {
      buf.writeResourceLocation(this.entityName);
      buf.writeUtf(this.speciesName);
      buf.writeInt(this.skinsData);
      buf.writeInt(this.id);
   }

   public boolean handle(Supplier<Context> ctx) {
      ctx.get()
         .enqueueWork(
            () -> {
               EntityType<?> type = (EntityType<?>)ForgeRegistries.ENTITY_TYPES.getValue(this.entityName);
               if (!ComplexMob.CLIENT_DATA_HASH.containsKey(type)) {
                  ComplexMob.CLIENT_DATA_HASH.put(type, new EntityDataHolderClient(new HashMap<>(), new HashMap<>()));
               }

               EntityUtils.buildSkinArrays(
                  this.entityName.getPath(), this.speciesName, this.skinsData, this.id, ComplexMob.TEXTURES_COMMON, ComplexMob.TEXTURES_RARE
               );
               ComplexMob.CLIENT_DATA_HASH.get(type).addSpeciesName(this.id, this.speciesName);
            }
         );
      return true;
   }
}
