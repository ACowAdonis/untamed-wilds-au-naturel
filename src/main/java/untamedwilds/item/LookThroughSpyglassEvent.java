package untamedwilds.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ISpecies;
import untamedwilds.init.ModAdvancementTriggers;

@EventBusSubscriber(
   modid = "untamedwilds"
)
public class LookThroughSpyglassEvent {
   @SubscribeEvent
   public static void lookAtEntityThroughSpyglassEvent(LivingEntityUseItemEvent event) {
      ItemStack usedItem = event.getItem();
      Entity entity = event.getEntity();
      if ((Boolean)ConfigGamerules.spyglassBehaviorChange.get()
         && !entity.level().isClientSide
         && entity instanceof Player playerIn
         && playerIn.tickCount % 40 == 0
         && usedItem.getItem().equals(Items.SPYGLASS)) {
         HitResult hitresult = raycast(playerIn, (double)((Integer)ConfigGamerules.spyglassCheckRange.get()).intValue(), true);
         if (hitresult.getType() == Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult)hitresult;
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntityHitResult) {
               displayEntityData(livingEntityHitResult, playerIn, playerIn.level());
               if (entityHitResult.getEntity() instanceof Animal animalResult) {
                  ModAdvancementTriggers.DISCOVERED.trigger((ServerPlayer)playerIn, animalResult);
               }
            }
         }
      }
   }

   public static HitResult raycast(Entity origin, double maxDistance, boolean hitsEntities) {
      Vec3 startPos = origin.getEyePosition(1.0F);
      Vec3 rotation = origin.getViewVector(1.0F);
      Vec3 endPos = startPos.add(rotation.x * maxDistance, rotation.y * maxDistance, rotation.z * maxDistance);
      HitResult hitResult = origin.level().clip(new ClipContext(startPos, endPos, Block.COLLIDER, Fluid.NONE, origin));
      if (hitResult.getType() != Type.MISS) {
         endPos = hitResult.getLocation();
      }

      maxDistance *= 5.0;
      HitResult entityHitResult = ProjectileUtil.getEntityHitResult(
         origin, startPos, endPos, origin.getBoundingBox().expandTowards(rotation.scale(maxDistance)).inflate(1.0, 1.0, 1.0), entity -> !entity.isSpectator(), maxDistance
      );
      if (hitsEntities && entityHitResult != null) {
         hitResult = entityHitResult;
      }

      return hitResult;
   }

   private static void displayEntityData(LivingEntity target, Player playerIn, Level world) {
      MutableComponent name = MutableComponent.create(new LiteralContents(""));
      if (target instanceof ComplexMob entity) {
         String entityName = entity instanceof ISpecies ? ((ISpecies)entity).getSpeciesName() : entity.getName().getString();
         name.append((entity.isBaby() ? "Young " : "") + (ConfigGamerules.genderedBreeding.get() ? entity.getGenderString() + " " : "") + entityName + " ");
         if ((Boolean)ConfigGamerules.scientificNames.get()) {
            String useVarName = entity instanceof ISpecies ? "_" + ((ISpecies)entity).getRawSpeciesName(entity.getVariant()) : "";
            name.append("(");
            name.append(Component.translatable(entity.getType().getDescriptionId() + useVarName + ".sciname").withStyle(ChatFormatting.ITALIC));
            name.append(") ");
         }

         if (!entity.isMale() && entity.getAge() > 0 && !(Boolean)ConfigGamerules.easyBreeding.get()) {
            name.append("This female is pregnant ");
         }
      } else {
         name.append(target.isBaby() ? "Young " : target.getName().getString() + " ");
      }

      int health = (int)(10.0F * target.getHealth() / target.getMaxHealth());
      MutableComponent state = getHealthState(health);
      name.append("(");
      name.append(state);
      name.append(") ");
      name.append("(");
      name.append(getThreatLevel(target, playerIn));
      name.append(")");
      playerIn.displayClientMessage(name, true);
   }

   private static MutableComponent getHealthState(int health) {
      switch (health) {
         case 0:
         case 1:
            return MutableComponent.create(new LiteralContents("Almost Dead")).withStyle(ChatFormatting.DARK_RED);
         case 2:
         case 3:
         case 4:
            return MutableComponent.create(new LiteralContents("Wounded")).withStyle(ChatFormatting.RED);
         case 5:
         case 6:
         case 7:
            return MutableComponent.create(new LiteralContents("Injured")).withStyle(ChatFormatting.YELLOW);
         case 8:
         case 9:
         case 10:
            return MutableComponent.create(new LiteralContents("Healthy")).withStyle(ChatFormatting.GREEN);
         default:
            return MutableComponent.create(LiteralContents.EMPTY);
      }
   }

   private static MutableComponent getThreatLevel(LivingEntity target, Player player) {
      int val = ComplexMob.getEcoLevel(player) - ComplexMob.getEcoLevel(target);
      if (val > 4) {
         return MutableComponent.create(new LiteralContents("Harmless")).withStyle(ChatFormatting.GREEN);
      } else if (val > 2) {
         return MutableComponent.create(new LiteralContents("Mild threat")).withStyle(ChatFormatting.YELLOW);
      } else if (val > 0) {
         return MutableComponent.create(new LiteralContents("Caution")).withStyle(ChatFormatting.YELLOW);
      } else {
         return val > -4
            ? MutableComponent.create(new LiteralContents("Dangerous")).withStyle(ChatFormatting.RED)
            : MutableComponent.create(new LiteralContents("Deadly")).withStyle(ChatFormatting.DARK_RED);
      }
   }
}
