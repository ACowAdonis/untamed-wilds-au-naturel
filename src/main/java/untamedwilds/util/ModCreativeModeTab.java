package untamedwilds.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import untamedwilds.init.ModItems;
import untamedwilds.item.IPopulated;

public class ModCreativeModeTab {
   public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "untamedwilds");
   public static final RegistryObject<CreativeModeTab> UNTAMEDWILDS_ITEMS = CREATIVE_TABS.register(
      "untamedwilds",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.untamedwilds"))
            .withTabsBefore(new ResourceKey[]{CreativeModeTabs.SPAWN_EGGS})
            .icon(() -> new ItemStack((ItemLike)ModItems.LOGO.get()))
            .withTabsBefore(new ResourceKey[]{CreativeModeTabs.SPAWN_EGGS})
            .displayItems((enabledFeatures, output) -> {
               for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                  if (item.get() instanceof IPopulated populated) {
                     populated.fillItemCategory(output);
                  } else {
                     output.accept((ItemLike)item.get());
                  }
               }
            })
            .build()
   );
}
