package untamedwilds.item;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import untamedwilds.UntamedWilds;
import untamedwilds.util.EntityUtils;

public class MobBottledItem extends Item implements IPopulated {
   private final Supplier<? extends EntityType<?>> entity;

   public MobBottledItem(Supplier<? extends EntityType<?>> typeIn, Properties properties) {
      super(properties);
      this.entity = typeIn;
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
      EntityUtils.buildTooltipData(
         stack, tooltip, (EntityType<?>)this.entity.get(), EntityUtils.getVariantName((EntityType<?>)this.entity.get(), this.getSpecies(stack))
      );
   }

   public Component getName(ItemStack stack) {
      return Component.translatable(
         "item.untamedwilds.bottle_"
            + EntityUtils.getRegistryName((EntityType<?>)this.entity.get())
            + "_"
            + EntityUtils.getVariantName((EntityType<?>)this.entity.get(), this.getSpecies(stack))
      );
   }

   public InteractionResult useOn(UseOnContext useContext) {
      Level worldIn = useContext.getLevel();
      if (!(worldIn instanceof ServerLevel)) {
         return InteractionResult.SUCCESS;
      } else {
         ItemStack itemStack = useContext.getItemInHand();
         BlockPos pos = useContext.getClickedPos();
         Direction facing = useContext.getClickedFace();
         BlockState blockState = worldIn.getBlockState(pos);
         BlockPos spawnPos = blockState.getCollisionShape(worldIn, pos).isEmpty() ? pos : pos.relative(facing);
         EntityType<?> entity = EntityUtils.getEntityTypeFromTag(itemStack.getTag(), (EntityType<?>)this.entity.get());
         boolean doVerticalOffset = !Objects.equals(pos, spawnPos) && facing == Direction.UP;
         EntityUtils.createMobFromItem((ServerLevel)worldIn, itemStack, entity, this.getSpecies(itemStack), spawnPos, useContext.getPlayer(), doVerticalOffset);
         if (useContext.getPlayer() != null && !useContext.getPlayer().isCreative()) {
            itemStack.shrink(1);
            useContext.getPlayer().getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
         }

         return InteractionResult.CONSUME;
      }
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
