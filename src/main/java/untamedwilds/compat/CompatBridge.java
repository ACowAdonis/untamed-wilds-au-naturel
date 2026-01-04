package untamedwilds.compat;

import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.Level;
import untamedwilds.UntamedWilds;
import untamedwilds.config.ConfigModCompat;

public class CompatBridge {
   private static final String SERENSEASONS_MODID = "sereneseasons";
   private static final String PATCHOULI_MODID = "patchouli";
   public static boolean Patchouli = false;
   public static boolean SereneSeasons = false;

   public static void RegisterCompat() {
      if (ModList.get().isLoaded("sereneseasons") && (Boolean)ConfigModCompat.sereneSeasonsCompat.get()) {
         SereneSeasons = true;
         UntamedWilds.LOGGER.log(Level.INFO, "Loading compatibility module with SereneSeasons");
      }

      if (ModList.get().isLoaded("patchouli") && (Boolean)ConfigModCompat.sereneSeasonsCompat.get()) {
         Patchouli = true;
         UntamedWilds.LOGGER.log(Level.INFO, "Loading compatibility module with Patchouli");
      }
   }
}
