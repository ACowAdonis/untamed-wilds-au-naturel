package untamedwilds.entity;

import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import untamedwilds.block.blockentity.CritterBurrowBlockEntity;
import untamedwilds.compat.CompatBridge;
import untamedwilds.compat.CompatSereneSeasons;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.init.ModAdvancementTriggers;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityDataHolder;
import untamedwilds.util.EntityDataHolderClient;
import untamedwilds.util.EntityDataListenerEvent;
import untamedwilds.util.EntityUtils;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class ComplexMob extends TamableAnimal {
   public static HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> TEXTURES_COMMON = new HashMap<>();
   public static HashMap<String, HashMap<Integer, ArrayList<ResourceLocation>>> TEXTURES_RARE = new HashMap<>();
   private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.BLOCK_POS);
   private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Integer> SKIN = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Boolean> IS_ANGRY = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(ComplexMob.class, EntityDataSerializers.BOOLEAN);
   public HerdEntity herd = null;
   public float turn_speed = 1.0F;
   public int huntingCooldown;
   public int retaliationCooldown;
   public static HashMap<EntityType<?>, EntityDataHolder> ENTITY_DATA_HASH = new HashMap<>();
   public static HashMap<EntityType<?>, EntityDataHolderClient> CLIENT_DATA_HASH = new HashMap<>();

   public ComplexMob(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new MoveControl(this);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(HOME_POS, BlockPos.ZERO);
      this.entityData.define(VARIANT, 0);
      this.entityData.define(SKIN, 0);
      this.entityData.define(SIZE, 1.0F);
      this.entityData.define(GENDER, 0);
      this.entityData.define(IS_ANGRY, false);
      this.entityData.define(COMMAND, 0);
      this.entityData.define(SLEEPING, false);
      this.entityData.define(SITTING, false);
   }

   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.huntingCooldown > 0) {
            this.huntingCooldown--;
         }

         if (this.retaliationCooldown > 0) {
            this.retaliationCooldown--;
         }

         if (this.tickCount % 600 == 0 && this.wantsToBreed()) {
            this.setInLove(null);
         }
      }
   }

   public static EntityDataHolder getEntityData(EntityType<?> typeIn) {
      if (!ENTITY_DATA_HASH.containsKey(typeIn)) {
         EntityDataListenerEvent.registerEntityData(typeIn);
      }

      return ENTITY_DATA_HASH.get(typeIn);
   }

   protected SoundEvent getAmbientSound() {
      return EntityUtils.getSound(this.getType(), this.getVariant(), "ambient");
   }

   protected SoundEvent getHurtSound(@NotNull DamageSource source) {
      return EntityUtils.getSound(this.getType(), this.getVariant(), "hurt", SoundEvents.GENERIC_HURT);
   }

   protected SoundEvent getDeathSound() {
      return EntityUtils.getSound(this.getType(), this.getVariant(), "death", SoundEvents.GENERIC_DEATH);
   }

   protected SoundEvent getThreatSound() {
      return EntityUtils.getSound(this.getType(), this.getVariant(), "threat");
   }

   public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
      return true;
   }

   public boolean checkSpawnObstruction(LevelReader worldIn) {
      return worldIn.isUnobstructed(this);
   }

   public boolean canBeLeashed(Player player) {
      return player.isCreative() ? !this.isLeashed() : !this.isLeashed() && this.isTame();
   }

   public boolean removeWhenFarAway(double distanceToClosestPlayer) {
      // Match vanilla despawn behavior: despawn if not tamed and no custom name
      return !this.isTame() && !this.hasCustomName();
   }

   public void setSleeping(boolean sleeping) {
      this.entityData.set(SLEEPING, sleeping);
   }

   public boolean isSleeping() {
      return (Boolean)this.entityData.get(SLEEPING);
   }

   public void setSitting(boolean sitting) {
      this.entityData.set(SITTING, sitting);
   }

   public boolean isSitting() {
      return (Boolean)this.entityData.get(SITTING);
   }

   public boolean isNotMoving() {
      return this.getDeltaMovement().x == 0.0 && this.getDeltaMovement().z == 0.0;
   }

   public boolean canBeTargeted() {
      return true;
   }

   public double getCurrentSpeed() {
      return Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
   }

   public int getAmbientSoundInterval() {
      return 300;
   }

   public int getExperienceReward() {
      int xp = Math.max(getEcoLevel(this) / 2, 1);
      return xp + this.level().random.nextInt(xp);
   }

   public int getVariant() {
      return (Integer)this.entityData.get(VARIANT);
   }

   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }

   public int getSkin() {
      return (Integer)this.entityData.get(SKIN);
   }

   public void setSkin(int skin) {
      this.entityData.set(SKIN, skin);
   }

   public <T extends ComplexMob> void chooseSkinForSpecies(T entityIn, boolean allowRares) {
      if (this instanceof INewSkins && !this.level().isClientSide) {
         String name = entityIn.getType().builtInRegistryHolder().key().location().getPath();
         if (!TEXTURES_COMMON.get(name).isEmpty()) {
            boolean isRare = allowRares
               && TEXTURES_RARE.get(name).containsKey(this.getVariant())
               && (double)this.random.nextFloat() < (Double)ConfigGamerules.rareSkinChance.get();
            int skin = this.random
                  .nextInt(isRare ? TEXTURES_RARE.get(name).get(this.getVariant()).size() : TEXTURES_COMMON.get(name).get(this.getVariant()).size())
               + (isRare ? 100 : 0);
            this.setSkin(skin);
         }
      }
   }

   public float getModelScale() {
      return getEntityData(this.getType()).getScale(this.getVariant());
   }

   public float getMobSize() {
      return (Float)this.entityData.get(SIZE);
   }

   public void setMobSize(float size) {
      this.entityData.set(SIZE, size);
   }

   public void setRandomMobSize() {
      this.entityData.set(SIZE, this.getModelScale() + (float)this.random.nextGaussian() * 0.1F);
   }

   public void setGender(int gender) {
      this.entityData.set(GENDER, gender);
   }

   public int getGender() {
      return (Integer)this.entityData.get(GENDER);
   }

   public boolean isMale() {
      return this.getGender() == 0;
   }

   public String getGenderString() {
      return this.isMale() ? "male" : "female";
   }

   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get()) {
         return CompatBridge.SereneSeasons ? CompatSereneSeasons.isCurrentSeason(this.level(), this.getBreedingSeason()) : true;
      } else {
         return false;
      }
   }

   public <T extends ComplexMob> void breed() {
      int bound = 1 + (this.getOffspring() > 0 ? this.random.nextInt(this.getOffspring() + 1) : 0);

      for (int i = 0; i < bound; i++) {
         T child = (T)this.getBreedOffspring((ServerLevel)this.level(), this);
         if (child != null) {
            child.setVariant(this.getVariant());
            child.setAge(this.getAdulthoodTime() * -1);
            child.setGender(this.random.nextInt(2));
            child.setRandomMobSize();
            child.setBaby(true);
            child.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            if (this.getOwner() != null) {
               child.tame((Player)this.getOwner());
            }

            if (this instanceof INeedsPostUpdate) {
               ((INeedsPostUpdate)child).updateAttributes();
            }

            if (TEXTURES_COMMON.containsKey(child.getType().builtInRegistryHolder().key().location().getPath())) {
               this.chooseSkinForSpecies(child, true);
            }

            this.level().addFreshEntity(child);
            this.level().broadcastEntityEvent(this, (byte)18);
         }
      }
   }

   protected <T extends ComplexMob> T create_offspring(T entity) {
      entity.setGender(this.random.nextInt(2));
      entity.setRandomMobSize();
      entity.setVariant(this.getVariant());
      entity.chooseSkinForSpecies(this, true);
      if (entity instanceof INeedsPostUpdate) {
         ((INeedsPostUpdate)entity).updateAttributes();
      }

      return entity;
   }

   public String getBreedingSeason() {
      return getEntityData(this.getType()).getBreedingSeason(this.getVariant());
   }

   public int getAdulthoodTime() {
      return getEntityData(this.getType()).getGrowingTime(this.getVariant()) * (Integer)ConfigGamerules.cycleLength.get() * 2;
   }

   public int getPregnancyTime() {
      return getEntityData(this.getType()).getGrowingTime(this.getVariant()) * (Integer)ConfigGamerules.cycleLength.get();
   }

   public int getOffspring() {
      return getEntityData(this.getType()).getOffspring(this.getVariant());
   }

   public boolean isFood(ItemStack stack) {
      return getEntityData(this.getType()).getFavouriteFood(this.getVariant()).getItem().equals(Blocks.AIR.asItem())
         ? false
         : stack.getItem().equals(getEntityData(this.getType()).getFavouriteFood(this.getVariant()).getItem());
   }

   public boolean canTakeItem(ItemStack stack) {
      return false;
   }

   private boolean isBlinking() {
      return this.tickCount % 60 > 53;
   }

   public boolean shouldRenderEyes() {
      return !this.isSleeping() && !this.dead && !this.isBlinking() && this.hurtTime == 0;
   }

   public boolean canMove() {
      return !this.isSitting() && !this.isSleeping() && !this.isVehicle();
   }

   public void setHome(BlockPos position) {
      this.entityData.set(HOME_POS, position);
   }

   public BlockPos getHome() {
      return (BlockPos)this.entityData.get(HOME_POS);
   }

   public Vec3 getHomeAsVec() {
      BlockPos home = this.getHome();
      return new Vec3((double)home.getX(), (double)home.getY(), (double)home.getZ());
   }

   public void setAge(int age) {
      int i = this.age;
      super.setAge(age);
      this.age = age;
      if (!this.isMale()
         && (!(this instanceof INestingMob nestingMob) || !nestingMob.isEggLayer())
         && !(Boolean)ConfigGamerules.easyBreeding.get()
         && i > 0
         && age <= 0) {
         this.breed();
      }
   }

   public ResourceLocation getTexture() {
      return EntityUtils.getSkinFromEntity(this);
   }

   public static int getEcoLevel(LivingEntity entity) {
      if (entity instanceof Player) {
         return (int)(4.0F + entity.getHealth() / 6.0F);
      } else {
         int attack = (int)Math.max(entity.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? entity.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 1.0, 4.0);
         return entity instanceof ComplexMob && ((ComplexMob)entity).herd != null
            ? (int)(Math.sqrt((double)(entity.getHealth() * (float)attack)) / 2.5) + ((ComplexMob)entity).herd.creatureList.size()
            : (int)(Math.sqrt((double)(entity.getHealth() * (float)attack)) / 2.5);
      }
   }

   protected void performRetaliation(DamageSource damageSource, float health, float damage, boolean needsActiveTarget) {
      if (!needsActiveTarget || this.getTarget() == damageSource.getDirectEntity()) {
         if (this.retaliationCooldown == 0
            && !this.isNoAi()
            && this.getTarget() != null
            && damage < health
            && !damageSource.is(DamageTypeTags.IS_PROJECTILE)
            && damageSource.getDirectEntity() instanceof LivingEntity
            && !(damageSource.getDirectEntity() instanceof Player)
            && (!(damageSource.getDirectEntity() instanceof TamableAnimal tamable) || tamable.getOwner() == null)
            && damageSource.is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)
            && this.hasLineOfSight(damageSource.getDirectEntity())) {
            damageSource.getDirectEntity().hurt(this.damageSources().thorns(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            this.retaliationCooldown = 10;
         }
      }
   }

   protected void setAngry(boolean isAngry) {
      this.entityData.set(IS_ANGRY, isAngry);
   }

   public boolean isAngry() {
      return (Boolean)this.entityData.get(IS_ANGRY);
   }

   public void setCommandInt(int command) {
      this.entityData.set(COMMAND, command % (this.getType().is(ModTags.EntityTags.HAS_GUARD_AI) ? 4 : 3));
   }

   public int getCommandInt() {
      return (Integer)this.entityData.get(COMMAND);
   }

   public boolean shouldDespawn() {
      return this instanceof ISpecies && this.getHome() != BlockPos.ZERO;
   }

   public void checkDespawn() {
      super.checkDespawn();
      if (this.shouldDespawn()
         && !this.level().hasNearbyAlivePlayer(this.getX(), this.getY(), this.getZ(), (double)((Integer)ConfigMobControl.critterSpawnRange.get()).intValue())
         && this instanceof ISpecies
         && this.getHome() != BlockPos.ZERO) {
         BlockEntity burrow = this.level().getBlockEntity(this.getHome());
         if (burrow instanceof CritterBurrowBlockEntity) {
            ((CritterBurrowBlockEntity)burrow).tryEnterBurrow(this);
            burrow.setChanged();
         }
      }
   }

   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      if (this.getHome() != BlockPos.ZERO) {
         compound.putInt("HomePosX", this.getHome().getX());
         compound.putInt("HomePosY", this.getHome().getY());
         compound.putInt("HomePosZ", this.getHome().getZ());
      }

      if (this.isTame()) {
         compound.putInt("Command", this.getCommandInt());
      }

      compound.putInt("Variant", this.getVariant());
      compound.putInt("Skin", this.getSkin());
      compound.putFloat("Size", this.getMobSize());
      compound.putInt("Gender", this.getGender());
      compound.putBoolean("isAngry", this.isAngry());
      compound.putInt("PeacefulTicks", this.huntingCooldown);
   }

   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      if (compound.contains("HomePosX")) {
         int i = compound.getInt("HomePosX");
         int j = compound.getInt("HomePosY");
         int k = compound.getInt("HomePosZ");
         this.setHome(new BlockPos(i, j, k));
      }

      if (compound.contains("OwnerUUID")) {
         this.setCommandInt(compound.getInt("Command"));
      }

      this.setVariant(EntityUtils.getClampedNumberOfSpecies(compound.getInt("Variant"), this.getType()));
      this.setSkin(compound.getInt("Skin"));
      this.setMobSize(compound.getFloat("Size"));
      this.setGender(compound.getInt("Gender"));
      this.setAngry(compound.getBoolean("isAngry"));
      this.huntingCooldown = compound.getInt("PeacefulTicks");
   }

   @Nullable
   public SpawnGroupData finalizeSpawn(
      ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag
   ) {
      if (!ISpecies.isArtificialMobSpawnType(reason) && this instanceof ISpecies) {
         Holder<Biome> optional = worldIn.getBiome(this.blockPosition());
         int i = ((ISpecies)this).setSpeciesByBiome(optional, reason);
         this.setVariant(i);
         if (i == 99) {
            this.remove(RemovalReason.DISCARDED);
            return null;
         }
      }

      this.setGender(this.random.nextInt(2));
      this.setRandomMobSize();
      if (TEXTURES_COMMON.containsKey(this.getType().builtInRegistryHolder().key().location().getPath())) {
         this.chooseSkinForSpecies(this, (Boolean)ConfigGamerules.wildRareSkins.get());
      }

      if (this instanceof INeedsPostUpdate) {
         ((INeedsPostUpdate)this).updateAttributes();
      }

      this.setAge(0);
      if (this instanceof IPackEntity) {
         IPackEntity.initPack(this);
      }

      return spawnDataIn;
   }

   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide()) {
         if (!CompatBridge.Patchouli) {
            ModAdvancementTriggers.NO_PATCHOULI_LOADED.trigger((ServerPlayer)player);
         }

         ItemStack itemstack = player.getItemInHand(hand);
         if (player.isCreative() && itemstack.isEmpty() && this instanceof IPackEntity && this.herd != null) {
            for (int i = 0; i < this.herd.creatureList.size(); i++) {
               ComplexMob creature = this.herd.creatureList.get(i);
               creature.addEffect(new MobEffectInstance(MobEffects.GLOWING, 80, 0));
            }
         }

         if (this.isTame() && this.getOwner() == player) {
            if (itemstack.isEmpty()) {
               this.setCommandInt(this.getCommandInt() + 1);
               player.sendSystemMessage(Component.translatable("entity.untamedwilds.command." + this.getCommandInt()));
               if (this.getCommandInt() > 1) {
                  this.getNavigation().stop();
                  this.setSitting(true);
               } else if (this.getCommandInt() <= 1 && this.isSitting()) {
                  this.setSitting(false);
               }
            } else {
               EntityUtils.consumeItemStack(this, itemstack);
            }
         }

         return super.mobInteract(player, hand);
      } else {
         return InteractionResult.PASS;
      }
   }
}
