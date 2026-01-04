package untamedwilds.item.debug;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;

public class EraserItem extends Item {
   public EraserItem(Properties properties) {
      super(properties);
   }

   public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
      if (target.level().isClientSide) {
         return InteractionResult.PASS;
      } else {
         target.remove(RemovalReason.DISCARDED);
         return InteractionResult.SUCCESS;
      }
   }
}
