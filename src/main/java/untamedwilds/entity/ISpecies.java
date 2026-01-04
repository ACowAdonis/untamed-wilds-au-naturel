package untamedwilds.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.biome.Biome;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.util.SpeciesDataHolder;

public interface ISpecies {
   default int setSpeciesByBiome(Holder<Biome> biome, MobSpawnType reason) {
      if (!(Boolean)ConfigGamerules.randomSpecies.get() && !isArtificialMobSpawnType(reason)) {
         List<Integer> validTypes = new ArrayList<>();

         for (SpeciesDataHolder speciesDatum : ComplexMob.getEntityData(((Mob)this).getType()).getSpeciesData()) {
            for (List<SpeciesDataHolder.BiomeTestHolder> testList : speciesDatum.getBiomeCategories()) {
               List<Boolean> results = new ArrayList<>();

               for (SpeciesDataHolder.BiomeTestHolder test : testList) {
                  if (!test.isValidBiome(biome, (Biome)biome.value())) {
                     break;
                  }

                  results.add(true);
               }

               if (results.size() == testList.size()) {
                  for (int i = 0; i < speciesDatum.getRarity(); i++) {
                     validTypes.add(speciesDatum.getVariant());
                  }
               }
            }
         }

         return validTypes.isEmpty() ? 99 : validTypes.get(new Random().nextInt(validTypes.size()));
      } else {
         return ((Mob)this).getRandom().nextInt(ComplexMob.getEntityData(((Mob)this).getType()).getSpeciesData().size());
      }
   }

   default String getRawSpeciesName(int i) {
      return ComplexMob.getEntityData(((ComplexMob)this).getType()).getSpeciesData().get(i).getName().toLowerCase();
   }

   default String getSpeciesName(int i) {
      return Component.translatable(
            "entity.untamedwilds." + ((ComplexMob)this).getType().builtInRegistryHolder().key().location().getPath() + "_" + this.getRawSpeciesName(i)
         )
         .getString();
   }

   default String getSpeciesName() {
      return this instanceof ComplexMob ? this.getSpeciesName(((ComplexMob)this).getVariant()) : "";
   }

   static boolean isArtificialMobSpawnType(MobSpawnType reason) {
      return reason == MobSpawnType.SPAWN_EGG
         || reason == MobSpawnType.BUCKET
         || reason == MobSpawnType.MOB_SUMMONED
         || reason == MobSpawnType.COMMAND
         || reason == MobSpawnType.SPAWNER
         || reason == MobSpawnType.DISPENSER
         || reason == MobSpawnType.BREEDING;
   }
}
