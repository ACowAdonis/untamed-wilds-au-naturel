package untamedwilds.item;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.util.EntityUtils;

public class MobEggItem extends Item implements IPopulated {
   private final Supplier<? extends EntityType<?>> entity;

   public MobEggItem(Supplier<? extends EntityType<?>> typeIn, Properties properties) {
      super(properties);
      this.entity = typeIn;
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
      tooltip.add(Component.translatable("mobspawn.tooltip.unknown").withStyle(ChatFormatting.GRAY));
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
         Entity spawn = entity.create(
            (ServerLevel)worldIn, itemStack.getTag(), null, spawnPos, MobSpawnType.BUCKET, true, !Objects.equals(pos, spawnPos) && facing == Direction.UP
         );
         if (spawn instanceof ComplexMob entitySpawn) {
            entitySpawn.setVariant(this.getSpecies(itemStack, entitySpawn));
            entitySpawn.chooseSkinForSpecies(entitySpawn, true);
            entitySpawn.setRandomMobSize();
            entitySpawn.setGender(entitySpawn.getRandom().nextInt(2));
            entitySpawn.setAge(entitySpawn.getAdulthoodTime() * -1);
            if (spawn instanceof INeedsPostUpdate) {
               ((INeedsPostUpdate)spawn).updateAttributes();
            }
         }

         if (spawn != null) {
            ((ServerLevel)worldIn).addFreshEntityWithPassengers(spawn);
         }

         itemStack.shrink(1);
         return InteractionResult.CONSUME;
      }
   }

   public String getDescriptionId() {
      return Component.translatable("item.untamedwilds.egg_" + this.entity.get().builtInRegistryHolder().key().location().getPath()).getString();
   }

   private int getSpecies(ItemStack itemIn, ComplexMob entityIn) {
      return itemIn.getTag() != null && itemIn.getTag().contains("variant") ? itemIn.getTag().getInt("variant") : entityIn.getVariant();
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
