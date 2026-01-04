package untamedwilds.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.registries.ForgeRegistries;
import untamedwilds.UntamedWilds;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INestingMob;
import untamedwilds.entity.ISpecies;
import untamedwilds.init.ModSounds;

public abstract class EntityUtils {
   public static void destroyBoat(Level worldIn, LivingEntity entityIn) {
      if (entityIn.getVehicle() != null && entityIn.getVehicle() instanceof Boat boat) {
         boat.kill();
         if (worldIn.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            for (int i = 0; i < 3; i++) {
               boat.spawnAtLocation(boat.getVariant().getPlanks());
            }

            for (int j = 0; j < 2; j++) {
               boat.spawnAtLocation(Items.STICK);
            }
         }
      }
   }

   public static <T extends ParticleOptions> void spawnParticlesOnEntity(Level worldIn, LivingEntity entityIn, T particle, int count, int iter) {
      if (!worldIn.isClientSide) {
         if (entityIn.isMultipartEntity()) {
            for (PartEntity<?> part : entityIn.getParts()) {
               for (int i = 0; i < iter; i++) {
                  ((ServerLevel)worldIn)
                     .sendParticles(
                        particle,
                        part.getX(),
                        part.getY() + (double)part.getBbHeight() / 1.5,
                        part.getZ(),
                        count,
                        (double)(part.getBbWidth() / 4.0F),
                        (double)(part.getBbHeight() / 4.0F),
                        (double)(part.getBbWidth() / 4.0F),
                        0.05
                     );
               }
            }
         } else {
            for (int i = 0; i < iter; i++) {
               ((ServerLevel)worldIn)
                  .sendParticles(
                     particle,
                     entityIn.getX(),
                     entityIn.getY() + (double)entityIn.getBbHeight() / 1.5,
                     entityIn.getZ(),
                     count,
                     (double)(entityIn.getBbWidth() / 4.0F),
                     (double)(entityIn.getBbHeight() / 4.0F),
                     (double)(entityIn.getBbWidth() / 4.0F),
                     0.05
                  );
            }
         }
      }
   }

   public static int getPackSize(EntityType<?> type, int variant) {
      return ComplexMob.getEntityData(type).getGroupCount(variant);
   }

   public static BlockPos getRelativeBlockPos(Entity entityIn, float xzOffset, float yOffset) {
      return entityIn.blockPosition()
         .offset(
            BlockPos.containing(
               Math.cos(Math.toRadians((double)(entityIn.getYRot() + 90.0F))) * (double)xzOffset,
               (double)yOffset,
               Math.sin(Math.toRadians((double)(entityIn.getYRot() + 90.0F))) * (double)xzOffset
            )
         );
   }

   public static EntityType<?> getEntityTypeFromTag(CompoundTag nbt, @Nullable EntityType<?> alt) {
      if (nbt != null && nbt.contains("EntityTag", 10)) {
         CompoundTag entityNBT = nbt.getCompound("EntityTag");
         if (entityNBT.contains("id", 8)) {
            return EntityType.byString(entityNBT.getString("id")).orElse(alt);
         }
      }

      return alt;
   }

   public static void buildTooltipData(ItemStack stack, List<Component> tooltip, EntityType<?> entity, String path) {
      if (path != null) {
         if (stack.getTag() != null && stack.getTag().contains("EntityTag")) {
            CompoundTag compound = stack.getTagElement("EntityTag");
            String gender = compound.contains("Gender")
               ? Component.translatable("mobspawn.tooltip." + (compound.getInt("Gender") == 0 ? "male" : "female")).getString() + " "
               : "";
            String type;
            if (path.isEmpty()) {
               type = Component.translatable(entity.getDescriptionId()).getString();
            } else {
               type = Component.translatable(entity.getDescriptionId() + "_" + path).getString();
            }

            if (stack.getTag().getCompound("EntityTag").contains("CustomName")) {
               String customName = stack.getTag().getCompound("EntityTag").getString("CustomName");
               tooltip.add(
                  MutableComponent.create(new LiteralContents(customName.substring(9, customName.length() - 2) + " (" + gender + type + ")"))
                     .withStyle(ChatFormatting.GRAY)
               );
            } else {
               tooltip.add(MutableComponent.create(new LiteralContents(gender + type)).withStyle(ChatFormatting.GRAY));
            }
         }

         if ((Boolean)ConfigGamerules.scientificNames.get()) {
            String scipath = path.isEmpty() ? "" : "_" + path;
            MutableComponent tooltipText = Component.translatable(entity.getDescriptionId() + scipath + ".sciname");
            if (!tooltipText.getString().contains(".")) {
               tooltip.add(tooltipText.withStyle(new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY}));
            }
         }
      }
   }

   public static void createMobFromItem(
      ServerLevel worldIn, ItemStack itemstack, EntityType<?> entity, @Nullable Integer species, BlockPos spawnPos, @Nullable Player player, boolean offset
   ) {
      createMobFromItem(worldIn, itemstack, entity, species, spawnPos, player, offset, false);
   }

   public static void createMobFromItem(
      ServerLevel worldIn,
      ItemStack itemstack,
      EntityType<?> entity,
      @Nullable Integer species,
      BlockPos spawnPos,
      @Nullable Player player,
      boolean offset,
      boolean skipNBTCheck
   ) {
      if (itemstack.getTag() != null) {
         if (itemstack.getTag().contains("EntityTag") && !skipNBTCheck) {
            if (itemstack.getTagElement("EntityTag").contains("UUID") && worldIn.getEntity(itemstack.getTagElement("EntityTag").getUUID("UUID")) != null) {
               itemstack.getTagElement("EntityTag").putUUID("UUID", Mth.createInsecureUUID(worldIn.random));
            }

            Entity spawn = entity.spawn(worldIn, itemstack, player, spawnPos, MobSpawnType.BUCKET, true, offset);
            if (spawn != null && itemstack.hasCustomHoverName()) {
               spawn.setCustomName(itemstack.getHoverName());
            }
         } else {
            Entity spawn = entity.create(worldIn, null, null, spawnPos, MobSpawnType.SPAWN_EGG, true, offset);
            if (spawn instanceof ComplexMob entitySpawn) {
               int true_species = species != null
                  ? species
                  : entitySpawn.getRandom().nextInt(ComplexMob.getEntityData(entitySpawn.getType()).getSpeciesData().size());
               entitySpawn.setVariant(true_species);
               entitySpawn.chooseSkinForSpecies(entitySpawn, true);
               entitySpawn.setRandomMobSize();
               entitySpawn.setGender(entitySpawn.getRandom().nextInt(2));
               if (spawn instanceof INeedsPostUpdate) {
                  ((INeedsPostUpdate)spawn).updateAttributes();
               }
            }

            if (spawn != null) {
               if (itemstack.hasCustomHoverName()) {
                  spawn.setCustomName(itemstack.getHoverName());
               }

               worldIn.addFreshEntityWithPassengers(spawn);
            }
         }
      }
   }

   public static void dropEggs(ComplexMob entity, String item_name, int number) {
      if ((Boolean)ConfigGamerules.mobsLayEggs.get()) {
         CompoundTag baseTag = new CompoundTag();
         ItemStack item = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("untamedwilds:" + item_name.toLowerCase())));
         baseTag.putInt("variant", entity.getVariant());
         baseTag.putInt("custom_model_data", entity.getVariant());
         item.setTag(baseTag);
         ItemEntity entityitem = entity.spawnAtLocation(item, 0.2F);
         if (entityitem != null) {
            entityitem.getItem().setCount(1 + entity.getRandom().nextInt(number - 1));
         }
      }
   }

   public static void turnEntityIntoItem(LivingEntity entity, String item_name) {
      if ((Boolean)ConfigGamerules.easyMobCapturing.get() || ((Mob)entity).getTarget() == null) {
         ItemEntity entityitem = entity.spawnAtLocation(
            new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("untamedwilds:" + item_name.toLowerCase()))), 0.2F
         );
         RandomSource rand = entity.getRandom();
         if (entityitem != null) {
            entityitem.setDeltaMovement(
               (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F),
               (double)(rand.nextFloat() * 0.05F),
               (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F)
            );
            entityitem.getItem().setTag(writeEntityToNBT(entity, false, true));
            if (entity.hasCustomName()) {
               entityitem.getItem().setHoverName(entity.getCustomName());
            }

            entity.discard();
         }
      }
   }

   public static void mutateEntityIntoItem(LivingEntity entity, Player player, InteractionHand hand, String item_name, ItemStack itemstack) {
      if ((Boolean)ConfigGamerules.easyMobCapturing.get() || ((Mob)entity).getTarget() == null) {
         entity.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
         itemstack.shrink(1);
         ItemStack newitem = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("untamedwilds:" + item_name.toLowerCase())));
         newitem.setTag(writeEntityToNBT(entity, false, true));
         if (entity.hasCustomName()) {
            newitem.setHoverName(entity.getCustomName());
         }

         if (!entity.level().isClientSide) {
            CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, newitem);
         }

         if (itemstack.isEmpty()) {
            player.setItemInHand(hand, newitem);
         } else if (!player.getInventory().add(newitem)) {
            player.drop(newitem, false);
         }

         entity.discard();
      }
   }

   public static List<ItemStack> getItemFromLootTable(ResourceLocation lootTableIn, Level worldIn) {
      Builder lootcontext$builder = new Builder((ServerLevel)worldIn);
      return (List<ItemStack>)(worldIn.getServer() != null
         ? worldIn.getServer()
            .getLootData()
            .getLootTable(lootTableIn)
            .getRandomItems(lootcontext$builder.create(new net.minecraft.world.level.storage.loot.parameters.LootContextParamSet.Builder().build()))
         : Lists.newArrayList());
   }

   public static CompoundTag writeEntityToNBT(LivingEntity entity) {
      return writeEntityToNBT(entity, false);
   }

   public static CompoundTag writeEntityToNBT(LivingEntity entity, boolean keepHomeData) {
      return writeEntityToNBT(entity, keepHomeData, false);
   }

   public static CompoundTag writeEntityToNBT(LivingEntity entity, boolean keepHomeData, boolean attachModelData) {
      CompoundTag baseTag = new CompoundTag();
      CompoundTag entityTag = new CompoundTag();
      entity.saveAsPassenger(entityTag);
      entityTag.remove("Pos");
      entityTag.remove("Motion");
      if (entityTag.contains("BoundingBox")) {
         entityTag.remove("BoundingBox");
      }

      if (entityTag.contains("Leash")) {
         entityTag.remove("Leash");
      }

      if (entity instanceof ISpecies && !keepHomeData) {
         entityTag.remove("HomePosX");
         entityTag.remove("HomePosY");
         entityTag.remove("HomePosZ");
      }

      if (attachModelData && entity instanceof ComplexMob) {
         baseTag.putInt("CustomModelData", ((ComplexMob)entity).getVariant());
      }

      baseTag.put("EntityTag", entityTag);
      return baseTag;
   }

   public static boolean hasFullHealth(LivingEntity entityIn) {
      return entityIn.getHealth() >= entityIn.getMaxHealth();
   }

   public static Pair<Integer, Integer> buildSkinArrays(
      String name,
      String species,
      EntityDataHolder dataIn,
      int variant,
      HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> common_list,
      HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> rare_list
   ) {
      return buildSkinArrays(name, species, dataIn.getSkins(variant), variant, common_list, rare_list);
   }

   public static Pair<Integer, Integer> buildSkinArrays(
      String name,
      String species,
      int skins,
      int variant,
      HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> common_list,
      HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> rare_list
   ) {
      String path = "textures/entity/" + name + "/" + species;
      if (!common_list.containsKey(name)) {
         common_list.put(name, new HashMap<>());
      }

      if (!rare_list.containsKey(name)) {
         rare_list.put(name, new HashMap<>());
      }

      Pair<Integer, Integer> values = new Pair(skins / 10 - 1, skins % 10 - 1);
      common_list.get(name).put(variant, new ArrayList<>());
      if ((Integer)values.getFirst() >= 1) {
         for (int i = 0; i <= values.getFirst(); i++) {
            String full_path = String.format(path + "_%d.png", i + 1);
            common_list.get(name).get(variant).add(new ResourceLocation("untamedwilds", full_path));
         }
      } else {
         common_list.get(name).get(variant).add(new ResourceLocation("untamedwilds", path + ".png"));
      }

      if ((Integer)values.getSecond() >= 0) {
         rare_list.get(name).put(variant, new ArrayList<>());

         for (int i = 0; i <= values.getSecond(); i++) {
            String full_path = String.format(path + "_%dr.png", i + 1);
            rare_list.get(name).get(variant).add(new ResourceLocation("untamedwilds", full_path));
         }
      }

      return values;
   }

   @Deprecated
   public static int populateSkinArray(String path, String suffix, int variant, HashMap<Integer, ArrayList<ResourceLocation>> list, boolean addDefault) {
      list.put(variant, new ArrayList<>());

      for (int i = 0; i < 99; i++) {
         int k = i;

         try {
            if (suffix.matches("[^a-z0-9/._:-]")) {
               UntamedWilds.LOGGER.error("Invalid character in " + suffix + ", terminating Skin registry");
               break;
            }

            String full_path = String.format(path + suffix, i + 1);
            Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation("untamedwilds", full_path));
            list.get(variant).add(new ResourceLocation("untamedwilds", full_path));
         } catch (Exception var8) {
            if (i == 0 && addDefault) {
               list.get(variant).add(new ResourceLocation("untamedwilds", path + ".png"));
               k = i + 1;
            }

            if (list.get(variant).isEmpty()) {
               list.remove(variant);
            }

            return k;
         }
      }

      return 0;
   }

   public static String getVariantName(EntityType<?> typeIn, int variantIn) {
      if (ComplexMob.ENTITY_DATA_HASH.containsKey(typeIn)) {
         return ComplexMob.ENTITY_DATA_HASH.get(typeIn).getName(variantIn);
      } else {
         return ComplexMob.CLIENT_DATA_HASH.containsKey(typeIn) ? ComplexMob.CLIENT_DATA_HASH.get(typeIn).getSpeciesName(variantIn) : "";
      }
   }

   public static int getNumberOfSpecies(EntityType<?> typeIn) {
      if (EntityDataListenerEvent.isEntityDataLoaded()) {
         if (ComplexMob.ENTITY_DATA_HASH.containsKey(typeIn)) {
            return ComplexMob.ENTITY_DATA_HASH.get(typeIn).getSpeciesData().size();
         }

         if (ComplexMob.CLIENT_DATA_HASH.containsKey(typeIn)) {
            return ComplexMob.CLIENT_DATA_HASH.get(typeIn).getNumberOfSpecies();
         }

         UntamedWilds.LOGGER.warn("There's no species provided for the EntityType");
      }

      return 99;
   }

   public static SoundEvent getSound(EntityType<?> typeIn, int variantIn, String sound_type) {
      return getSound(typeIn, variantIn, sound_type, ModSounds.NOTHING);
   }

   public static SoundEvent getSound(EntityType<?> typeIn, int variantIn, String sound_type, @Nullable SoundEvent fallback) {
      if (ComplexMob.ENTITY_DATA_HASH.containsKey(typeIn)) {
         SoundEvent location = ComplexMob.ENTITY_DATA_HASH.get(typeIn).getSounds(variantIn, sound_type);
         if (location != null) {
            return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(location.getLocation());
         }
      }

      return fallback;
   }

   public static int getClampedNumberOfSpecies(int i, EntityType<?> typeIn) {
      if (EntityDataListenerEvent.isEntityDataLoaded()) {
         int size = Math.max(0, getNumberOfSpecies(typeIn) - 1);
         if (i > size) {
            UntamedWilds.LOGGER.warn("Correcting wrong Variant value of " + i + " to " + size);
         }

         return Mth.clamp(i, 0, size);
      } else {
         return i;
      }
   }

   public static ResourceLocation getSkinFromEntity(ComplexMob entityIn) {
      Optional<ResourceKey<EntityType<?>>> entry = ForgeRegistries.ENTITY_TYPES.getResourceKey(entityIn.getType());
      if (entry.isPresent()) {
         String name = entry.get().location().getPath();
         if (entityIn.getSkin() > 99 && ComplexMob.TEXTURES_RARE.get(name).containsKey(entityIn.getVariant())) {
            return ComplexMob.TEXTURES_RARE
               .get(name)
               .get(entityIn.getVariant())
               .get(Math.min(entityIn.getSkin() - 100, ComplexMob.TEXTURES_RARE.get(name).get(entityIn.getVariant()).size() - 1));
         }

         if (entityIn.getVariant() >= 0) {
            return ComplexMob.TEXTURES_COMMON
               .get(name)
               .get(entityIn.getVariant())
               .get(Math.min(entityIn.getSkin(), ComplexMob.TEXTURES_COMMON.get(name).get(entityIn.getVariant()).size() - 1));
         }
      }

      return new ResourceLocation("missing");
   }

   public static void consumeItemStack(TamableAnimal entityIn, ItemStack itemstack) {
      if (itemstack.isEdible()) {
         FoodProperties itemFood = itemstack.getItem().getFoodProperties(itemstack, entityIn);
         if (itemFood != null) {
            entityIn.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
            if (entityIn instanceof ComplexMobTerrestrial) {
               ((ComplexMobTerrestrial)entityIn).addHunger(itemFood.getNutrition() * 10);
            } else {
               entityIn.heal((float)itemFood.getNutrition());
            }

            for (Pair<MobEffectInstance, Float> pair : itemFood.getEffects()) {
               if (pair.getFirst() != null && entityIn.level().random.nextFloat() < (Float)pair.getSecond()) {
                  entityIn.addEffect(new MobEffectInstance((MobEffectInstance)pair.getFirst()));
               }
            }
         }
      } else if (!PotionUtils.getMobEffects(itemstack).isEmpty()) {
         entityIn.playSound(SoundEvents.GENERIC_DRINK, 1.0F, 1.0F);
         if (entityIn instanceof ComplexMobTerrestrial) {
            ((ComplexMobTerrestrial)entityIn).addHunger(10);
         }

         for (MobEffectInstance effectinstance : PotionUtils.getMobEffects(itemstack)) {
            if (effectinstance.getEffect().isInstantenous()) {
               effectinstance.getEffect().applyInstantenousEffect(entityIn.getOwner(), entityIn.getOwner(), entityIn, effectinstance.getAmplifier(), 1.0);
            } else {
               entityIn.addEffect(new MobEffectInstance(effectinstance));
            }
         }
      }
   }

   public static Vec3 getOvershootPath(Entity entityIn, Entity targetIn, double overshoot) {
      double x = targetIn.getX() - entityIn.getX();
      double z = targetIn.getZ() - entityIn.getZ();
      float angle = (float)Math.atan2(z, x);
      double dist = (double)Mth.sqrt((float)(Math.pow(x, 2.0) + Math.pow(z, 2.0)));
      double add_x = (double)Mth.cos(angle) * (dist + overshoot);
      double add_z = (double)Mth.sin(angle) * (dist + overshoot);
      return new Vec3(entityIn.getX() + add_x, targetIn.getY(), entityIn.getZ() + add_z);
   }

   public static boolean isInvalidPartner(ComplexMob entityIn, ComplexMob partnerIn, boolean isHermaphrodite) {
      if (entityIn instanceof INestingMob nesting && (nesting.wantsToLayEggs() || ((INestingMob)partnerIn).wantsToLayEggs())) {
         return true;
      }

      return (Boolean)ConfigGamerules.genderedBreeding.get() && (partnerIn.getGender() == entityIn.getGender() || isHermaphrodite)
         || partnerIn.getVariant() != entityIn.getVariant()
         || partnerIn.getAge() != 0;
   }

   public static String getRegistryName(EntityType<?> id) {
      return id.getDescription().getString().replace(" ", "_").toLowerCase();
   }
}
