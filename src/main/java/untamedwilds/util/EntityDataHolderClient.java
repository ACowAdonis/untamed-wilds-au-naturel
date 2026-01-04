package untamedwilds.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class EntityDataHolderClient {
   public final Map<Integer, Map<String, SoundEvent>> sounds;
   public final HashMap<Integer, String> species_data;

   public EntityDataHolderClient(Map<Integer, Map<String, SoundEvent>> p_i232114_3_, HashMap<Integer, String> attack) {
      this.sounds = p_i232114_3_;
      this.species_data = attack;
   }

   public String getSpeciesName(int i) {
      return this.species_data.get(i);
   }

   public int getNumberOfSpecies() {
      return this.species_data.size();
   }

   public void addSpeciesName(int id, String name) {
      if (!this.species_data.containsKey(id)) {
         this.species_data.put(id, name);
      }
   }

   public void addSoundData(int id, String sound_type, ResourceLocation sound) {
      if (!this.sounds.containsKey(id)) {
         this.sounds.put(id, new HashMap<>());
      }

      this.sounds.get(id).put(sound_type, SoundEvent.createVariableRangeEvent(sound));
   }
}
