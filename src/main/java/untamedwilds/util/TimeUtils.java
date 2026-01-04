package untamedwilds.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public abstract class TimeUtils {
   public static String convertTicksToDays(Level world, int ticks) {
      int d = ticks / 24000;
      if (d > 7) {
         int m = d / 7;
         d -= m * 7;
         return Component.translatable("untamedwilds.timeutils.weeks", new Object[]{m, d}).getString();
      } else {
         return Component.translatable("untamedwilds.timeutils.days", new Object[]{d}).getString();
      }
   }

   public static int getTicksInMonth(Level world) {
      return 168000;
   }
}
