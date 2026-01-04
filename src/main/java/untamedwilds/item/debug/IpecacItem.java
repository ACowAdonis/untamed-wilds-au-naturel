package untamedwilds.item.debug;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import untamedwilds.entity.ComplexMobTerrestrial;

public class IpecacItem extends Item {
   public IpecacItem(Properties properties) {
      super(properties);
   }

   public InteractionResult useOn(UseOnContext context) {
      context.getPlayer().sendSystemMessage(MutableComponent.create(new LiteralContents("Pos: " + context.getClickedPos())));
      return InteractionResult.PASS;
   }

   public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
      if (target.level().isClientSide) {
         return InteractionResult.PASS;
      } else if (target instanceof Player) {
         return InteractionResult.FAIL;
      } else if (target instanceof ComplexMobTerrestrial entity) {
         entity.addHunger(-100);
         entity.huntingCooldown = 0;
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }
}
