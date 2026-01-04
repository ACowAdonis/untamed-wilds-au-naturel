package untamedwilds.item;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OwnershipDeedItem extends Item {
   public OwnershipDeedItem(Properties properties) {
      super(properties);
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
      if (stack.hasTag()) {
         CompoundTag nbt = stack.getTag();
         if (nbt != null) {
            tooltip.add(
               Component.translatable("item.untamedwilds.ownership_deed_desc_4", new Object[]{nbt.getString("entityname")}).withStyle(ChatFormatting.GRAY)
            );
            tooltip.add(Component.translatable("item.untamedwilds.ownership_deed_desc_5").withStyle(ChatFormatting.GRAY));
            tooltip.add(
               Component.translatable("item.untamedwilds.ownership_deed_desc_6", new Object[]{nbt.getString("ownername")})
                  .withStyle(new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY})
            );
         }
      } else {
         tooltip.add(Component.translatable("item.untamedwilds.ownership_deed_desc_1").withStyle(ChatFormatting.GRAY));
      }
   }

   public boolean isFoil(ItemStack stack) {
      return stack.hasTag();
   }

   @Nonnull
   public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
      ItemStack itemstack = playerIn.getItemInHand(handIn);
      if (itemstack.hasTag()) {
         CompoundTag nbt = itemstack.getTag();
         if (nbt != null && !nbt.getString("entityid").isEmpty()) {
            for (LivingEntity entity : worldIn.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(8.0))) {
               if (entity.getUUID().equals(UUID.fromString(nbt.getString("entityid")))) {
                  entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 80, 0, false, false));
               }
            }
         }
      }

      return new InteractionResultHolder(InteractionResult.SUCCESS, itemstack);
   }
}
