package untamedwilds.item;

import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import untamedwilds.UntamedWilds;
import untamedwilds.entity.ComplexMob;
import untamedwilds.util.EntityUtils;

public class MobBucketedItem extends BucketItem implements IPopulated {
   private final Supplier<? extends EntityType<?>> entity;

   public MobBucketedItem(Supplier<? extends EntityType<?>> typeIn, Fluid fluid, Properties builder) {
      super(() -> fluid, builder);
      this.entity = typeIn;
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
      if (ComplexMob.ENTITY_DATA_HASH.containsKey(this.entity.get())) {
         EntityUtils.buildTooltipData(
            stack, tooltip, (EntityType<?>)this.entity.get(), EntityUtils.getVariantName((EntityType<?>)this.entity.get(), this.getSpecies(stack))
         );
      }
   }

   public Component getName(ItemStack stack) {
      return Component.translatable(
         "item.untamedwilds.bucket_"
            + EntityUtils.getRegistryName((EntityType<?>)this.entity.get())
            + "_"
            + EntityUtils.getVariantName((EntityType<?>)this.entity.get(), this.getSpecies(stack))
      );
   }

   public void checkExtraContent(@Nullable Player playerIn, Level worldIn, ItemStack itemStackIn, BlockPos posIn) {
      if (worldIn instanceof ServerLevel) {
         this.spawn(worldIn, itemStackIn, posIn);
         worldIn.gameEvent(playerIn, GameEvent.ENTITY_PLACE, posIn);
      }
   }

   public void spawn(Level worldIn, ItemStack itemStack, BlockPos pos) {
      if (worldIn instanceof ServerLevel) {
         EntityType<?> entity = EntityUtils.getEntityTypeFromTag(itemStack.getTag(), (EntityType<?>)this.entity.get());
         EntityUtils.createMobFromItem((ServerLevel)worldIn, itemStack, entity, this.getSpecies(itemStack), pos, null, false);
      }
   }

   protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos) {
      worldIn.playSound(player, pos, SoundEvents.BUCKET_EMPTY_FISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
   }

   private int getSpecies(ItemStack itemIn) {
      if (itemIn.getTag() != null && itemIn.getTag().contains("CustomModelData")) {
         return itemIn.getTag().getInt("CustomModelData");
      } else {
         UntamedWilds.LOGGER.error("No variant found in this itemstack NBT data");
         return 0;
      }
   }

   @Override
   public void fillItemCategory(Output group) {
      for (int i = 0; i < EntityUtils.getNumberOfSpecies((EntityType<?>)this.entity.get()); i++) {
         CompoundTag baseTag = new CompoundTag();
         ItemStack item = new ItemStack(this);
         baseTag.putInt("variant", i);
         baseTag.putInt("CustomModelData", i);
         item.setTag(baseTag);
         group.accept(item);
      }
   }
}
