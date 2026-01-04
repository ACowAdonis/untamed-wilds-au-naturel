package untamedwilds.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import untamedwilds.UntamedWilds;

public class JSONLoader<T> extends SimpleJsonResourceReloadListener {
   private static final Gson STANDARD_GSON = new Gson();
   private final Codec<T> codec;
   private final String folderName;
   protected Map<ResourceLocation, T> data = new HashMap<>();

   public JSONLoader(String folderName, Codec<T> codec) {
      this(folderName, codec, STANDARD_GSON);
   }

   public JSONLoader(String folderName, Codec<T> codec, Gson gson) {
      super(gson, folderName);
      this.folderName = folderName;
      this.codec = codec;
   }

   @Nullable
   public T getData(ResourceLocation id) {
      return this.data.get(id);
   }

   protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profiler) {
      UntamedWilds.LOGGER.info("Beginning loading of data for data loader: {}", this.folderName);
      this.data = this.mapValues(jsons);
      UntamedWilds.LOGGER.info("Data loader for {} loaded {} jsons", this.folderName, this.data.size());
   }

   private Map<ResourceLocation, T> mapValues(Map<ResourceLocation, JsonElement> inputs) {
      Map<ResourceLocation, T> newMap = new HashMap<>();

      for (Entry<ResourceLocation, JsonElement> entry : inputs.entrySet()) {
         ResourceLocation key = entry.getKey();
         JsonElement element = entry.getValue();
         this.codec
            .decode(JsonOps.INSTANCE, element)
            .get()
            .ifLeft(result -> newMap.put(key, (T)result.getFirst()))
            .ifRight(partial -> UntamedWilds.LOGGER.error("Failed to parse data json for {} due to: {}", key.toString(), partial.message()));
      }

      return newMap;
   }
}
