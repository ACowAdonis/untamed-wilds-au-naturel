package untamedwilds.init;

import java.util.List;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(
   modid = "untamedwilds"
)
public class ModVillagerTrades {
   @SubscribeEvent
   public static void onVillagerTradesEvent(VillagerTradesEvent event) {
      if (event.getType() == VillagerProfession.FISHERMAN) {
         ((List)event.getTrades().get(3)).add(new ModVillagerTrades.setupOffer(Items.EMERALD, 1, (Item)ModItems.MATERIAL_PEARL.get(), 1, 6, 8, 0.2F));
         ((List)event.getTrades().get(3)).add(new ModVillagerTrades.setupOffer((Item)ModItems.MATERIAL_PEARL.get(), 2, Items.EMERALD, 1, 6, 8, 0.2F));
         ((List)event.getTrades().get(4)).add(new ModVillagerTrades.setupOffer((Item)ModItems.RARE_GIANT_PEARL.get(), 1, Items.EMERALD, 6, 3, 5, 0.2F));
         ((List)event.getTrades().get(4)).add(new ModVillagerTrades.setupOffer(Items.EMERALD, 6, ((Block)ModBlock.SHARK_MEAT.get()).asItem(), 1, 8, 8, 0.2F));
      }

      if (event.getType() == VillagerProfession.BUTCHER) {
         ((List)event.getTrades().get(3)).add(new ModVillagerTrades.setupOffer((Item)ModItems.MATERIAL_BLUBBER.get(), 6, Items.EMERALD, 1, 12, 5, 0.05F));
         ((List)event.getTrades().get(3)).add(new ModVillagerTrades.setupOffer(Items.EMERALD, 1, (Item)ModItems.MATERIAL_FAT.get(), 4, 12, 5, 0.05F));
      }
   }

   public static class setupOffer implements ItemListing {
      private final Item itemstackIn;
      private final int stackSizeIn;
      private final Item itemstackOut;
      private final int stackSizeOut;
      private final int maxUses;
      private final int givenExp;
      private final float priceMultiplier;

      setupOffer(Item stackOut, int sizeOut, Item stackIn, int sizeIn, int maxUses, int givenExp, float priceMultiplier) {
         this.itemstackIn = stackIn;
         this.stackSizeIn = sizeIn;
         this.itemstackOut = stackOut;
         this.stackSizeOut = sizeOut;
         this.maxUses = maxUses;
         this.givenExp = givenExp;
         this.priceMultiplier = priceMultiplier;
      }

      public MerchantOffer getOffer(Entity entityIn, RandomSource rand) {
         return new MerchantOffer(
            new ItemStack(this.itemstackOut, this.stackSizeOut),
            new ItemStack(this.itemstackIn, this.stackSizeIn),
            this.maxUses,
            this.givenExp,
            this.priceMultiplier
         );
      }
   }
}
