package untamedwilds.item.debug;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import untamedwilds.entity.ComplexMob;

public class LovePotionItem extends Item {
   public LovePotionItem(Properties properties) {
      super(properties);
   }

   public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
      if (target.level().isClientSide) {
         return InteractionResult.PASS;
      } else if (target instanceof Player) {
         return InteractionResult.FAIL;
      } else {
         if (target instanceof ComplexMob) {
            if (((ComplexMob)target).getAge() <= 0) {
               ComplexMob entity = (ComplexMob)target;
               entity.setInLove(playerIn);
               return InteractionResult.SUCCESS;
            }

            ((ComplexMob)target).setAge(1);
         }

         if (target instanceof Animal) {
            ((Animal)target).setInLove(playerIn);
            return InteractionResult.SUCCESS;
         } else {
            return InteractionResult.FAIL;
         }
      }
   }
}
