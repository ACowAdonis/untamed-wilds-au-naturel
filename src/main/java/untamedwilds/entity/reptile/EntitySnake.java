package untamedwilds.entity.reptile;

import com.github.alexthe666.citadel.animation.Animation;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraftforge.common.ForgeMod;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INestingMob;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.LayEggsOnNestGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartSwimGoal_Land;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.entity.ai.target.DontThreadOnMeTarget;
import untamedwilds.entity.ai.target.HuntMobTarget;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class EntitySnake extends ComplexMobTerrestrial implements ISpecies, INewSkins, INeedsPostUpdate, INestingMob {
   private static final EntityDataAccessor<Boolean> RATTLER = SynchedEntityData.defineId(EntitySnake.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(EntitySnake.class, EntityDataSerializers.BOOLEAN);
   public static Animation ANIMATION_TONGUE;
   public float offset;

   public EntitySnake(EntityType<? extends ComplexMobTerrestrial> type, Level worldIn) {
      super(type, worldIn);
      ANIMATION_TONGUE = Animation.create(10);
      this.ticksToSit = 20;
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(RATTLER, false);
      this.entityData.define(HAS_EGG, false);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 2.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.33)
         .add(Attributes.FOLLOW_RANGE, 12.0)
         .add(Attributes.MAX_HEALTH, 5.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 0.0)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 0.4);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new SmartSwimGoal_Land(this));
      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(2, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.2, 1.6, input -> getEcoLevel(input) > getEcoLevel(this)));
      this.goalSelector.addGoal(3, new SmartWanderGoal(this, 1.0, true));
      this.goalSelector.addGoal(3, new LayEggsOnNestGoal(this));
      this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));
      this.targetSelector.addGoal(3, new HuntMobTarget<LivingEntity>(this, LivingEntity.class, true, 30, false, input -> getEcoLevel(input) < getEcoLevel(this)));
      this.targetSelector.addGoal(3, new DontThreadOnMeTarget<LivingEntity>(this, LivingEntity.class, true));
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.tickCount % 1000 == 0 && this.random.nextInt(40) == 0) {
            this.spawnAtLocation(new ItemStack((ItemLike)ModItems.MATERIAL_SNAKE_SKIN.get()), 0.2F);
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         if (this.tickCount % 120 == 0) {
            this.setAnimation(ANIMATION_TONGUE);
         }

         if (this.getAnimation() == NO_ANIMATION && this.getTarget() == null && !this.isSleeping()) {
            int i = this.random.nextInt(3000);
            if (i <= 10 && !this.isInWater() && this.isNotMoving() && this.canMove()) {
               this.getNavigation().stop();
               this.setSitting(true);
            }

            if ((i == 11 || this.isInWater() || this.isActive()) && this.isSitting()) {
               this.setSitting(false);
            }
         }

         this.setAngry(this.getTarget() != null);
      } else if ((double)Math.abs(this.getYRot() - this.yRotO) > 0.005) {
         this.offset = Mth.rotLerp(0.05F, this.offset, this.getYRot() - this.yRotO);
      }
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntitySnake> list = this.level().getEntitiesOfClass(EntitySnake.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         return list.size() >= 1;
      } else {
         return false;
      }
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
   }

   @Override
   protected SoundEvent getAmbientSound() {
      if (this.isAngry()) {
         super.getAmbientSound();
      }

      return null;
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      EntityUtils.dropEggs(this, "egg_snake", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (itemstack.isEmpty() && this.isAlive()) {
         EntityUtils.turnEntityIntoItem(this, "spawn_snake");
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, ANIMATION_TONGUE};
   }

   public boolean doHurtTarget(Entity entityIn) {
      float f = (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
      boolean flag = entityIn.hurt(this.damageSources().mobAttack(this), f);
      if (flag && this.getVenomStrength() > 0) {
         if (entityIn instanceof LivingEntity) {
            ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.POISON, 140, this.getVenomStrength() - 1));
         }

         return true;
      } else {
         return flag;
      }
   }

   @Override
   public boolean hurt(DamageSource source, float amount) {
      if (source.getDirectEntity() instanceof Player player && player.getMainHandItem().getItem() instanceof ShovelItem) {
         return super.hurt(source, this.getHealth());
      }

      return super.hurt(source, amount);
   }

   public Integer getVenomStrength() {
      return getEntityData(this.getType()).getFlags(this.getVariant(), "venom");
   }

   @Override
   public void updateAttributes() {
      this.setRattler(getEntityData(this.getType()).getFlags(this.getVariant(), "rattler") == 1);
   }

   public boolean isRattler() {
      return (Boolean)this.entityData.get(RATTLER);
   }

   private void setRattler(boolean dimorphism) {
      this.entityData.set(RATTLER, dimorphism);
   }

   public boolean attackEntityPartFrom(DamageSource source, float amount) {
      return this.hurt(source, amount);
   }

   @Override
   public boolean wantsToLayEggs() {
      return (Boolean)this.entityData.get(HAS_EGG);
   }

   @Override
   public void setEggStatus(boolean status) {
      this.entityData.set(HAS_EGG, status);
   }

   @Override
   public Block getNestType() {
      return (Block)ModBlock.NEST_REPTILE.get();
   }

   @Override
   public boolean isValidNestBlock(BlockPos pos) {
      return this.level().isEmptyBlock(pos)
         && this.level().getBlockState(pos.below()).is(ModTags.ModBlockTags.VALID_REPTILE_NEST)
         && this.getNestType().defaultBlockState().canSurvive(this.level(), pos);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("rattler", this.isRattler());
      compound.putBoolean("has_egg", this.wantsToLayEggs());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setRattler(compound.getBoolean("rattler"));
      this.setEggStatus(compound.getBoolean("has_egg"));
   }
}
