package untamedwilds.util;

import java.util.Objects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import untamedwilds.init.ModItems;

@EventBusSubscriber(
   modid = "untamedwilds"
)
public class ModEntityRightClickEvent {
   @SubscribeEvent
   public static void modEntityRightClickEvent(EntityInteract event) {
      Player playerIn = event.getEntity();
      Entity target = event.getTarget();
      InteractionHand hand = event.getHand();
      if (playerIn.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.OWNERSHIP_DEED.get()) {
         ItemStack itemstack = playerIn.getItemInHand(hand);
         if (target instanceof TamableAnimal entity_target && entity_target.isTame()) {
            if (Objects.equals(entity_target.getOwnerUUID(), playerIn.getUUID()) && !itemstack.hasTag()) {
               CompoundTag nbt = new CompoundTag();
               nbt.putString("ownername", playerIn.getName().getString());
               nbt.putString("entityname", entity_target.getName().getString());
               nbt.putString("ownerid", playerIn.getUUID().toString());
               nbt.putString("entityid", entity_target.getUUID().toString());
               itemstack.setTag(nbt);
               event.setCanceled(true);
               event.setCancellationResult(InteractionResult.SUCCESS);
            } else if (itemstack.getTag() != null) {
               if (entity_target.getOwnerUUID().toString().equals(itemstack.getTag().getString("ownerid"))
                  && entity_target.getUUID().toString().equals(itemstack.getTag().getString("entityid"))) {
                  entity_target.setOwnerUUID(playerIn.getUUID());
                  if (!playerIn.isCreative()) {
                     itemstack.shrink(1);
                  }
               }

               event.setCanceled(true);
               event.setCancellationResult(InteractionResult.SUCCESS);
            }
         }

         event.setCancellationResult(InteractionResult.PASS);
      }
   }
}
