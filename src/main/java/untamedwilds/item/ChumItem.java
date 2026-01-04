package untamedwilds.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.init.ModAdvancementTriggers;
import untamedwilds.init.ModParticles;

public class ChumItem extends Item {
   public ChumItem(Properties builder) {
      super(builder);
   }

   public int getUseDuration(ItemStack stack) {
      return 40;
   }

   public UseAnim getUseAnimation(ItemStack stack) {
      return UseAnim.EAT;
   }

   public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
      BlockHitResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, Fluid.SOURCE_ONLY);
      if (!worldIn.getFluidState(blockraytraceresult.getBlockPos()).is(FluidTags.WATER)) {
         return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
      } else {
         ItemStack itemstack = playerIn.getItemInHand(handIn);
         playerIn.getCooldowns().addCooldown(this, 30);
         worldIn.playSound(playerIn, playerIn.blockPosition(), SoundEvents.FISHING_BOBBER_SPLASH, SoundSource.NEUTRAL, 1.0F, 1.0F);
         if (!worldIn.isClientSide) {
            Vec3 pos = blockraytraceresult.getLocation();

            for (int i = 0; i < 6; i++) {
               double d2 = worldIn.random.nextGaussian() * 0.03;
               double d3 = worldIn.random.nextGaussian() * 0.03;
               double d4 = worldIn.random.nextGaussian() * 0.03;
               ((ServerLevel)worldIn)
                  .sendParticles((SimpleParticleType)ModParticles.CHUM_DISPERSE.get(), pos.x, pos.y - 0.1F, pos.z, 1, d2, d3, d4, 0.02);
            }

            ModAdvancementTriggers.BAIT_BASIC.trigger((ServerPlayer)playerIn);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
            if (!playerIn.isCreative()) {
               itemstack.shrink(1);
            }

            float dist = 80.0F;
            List<Entity> list = worldIn.getEntities(
               playerIn,
               new AABB(
                     playerIn.getX(),
                     playerIn.getY(),
                     playerIn.getZ(),
                     playerIn.getX() + 1.0,
                     playerIn.getY() + 1.0,
                     playerIn.getZ() + 1.0
                  )
                  .inflate((double)dist)
            );
            List<Mob> waterMobs = new ArrayList<>();

            for (Entity entity : list) {
               if ((entity instanceof WaterAnimal || entity instanceof ComplexMobAquatic || entity instanceof ComplexMobAmphibious) && entity.isInWater()) {
                  waterMobs.add((Mob)entity);
               }
            }

            if (waterMobs.size() >= 100) {
               ModAdvancementTriggers.MASTER_BAIT.trigger((ServerPlayer)playerIn);
            }

            for (Mob waterMob : waterMobs) {
               waterMob.getNavigation().stop();
               waterMob.getNavigation().moveTo(pos.x, pos.y, pos.z, 1.0);
            }
         }

         return InteractionResultHolder.consume(itemstack);
      }
   }
}
