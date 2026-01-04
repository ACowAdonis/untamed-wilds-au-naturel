package untamedwilds.entity.amphibian;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.INestingMob;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.AmphibiousTransition;
import untamedwilds.entity.ai.LayEggsOnNestGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.control.look.SmartSwimmerLookControl;
import untamedwilds.entity.ai.control.movement.SmartSwimmingMoveControl;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class EntityNewt extends ComplexMobAmphibious implements ISpecies, INewSkins, INestingMob {
   private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(EntityNewt.class, EntityDataSerializers.BOOLEAN);
   public int swimProgress;
   public float offset;

   public EntityNewt(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new SmartSwimmingMoveControl(this, 40, 5, 0.25F, 0.3F, true);
      this.lookControl = new SmartSwimmerLookControl(this, 20);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(HAS_EGG, false);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 1.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.7)
         .add(Attributes.FOLLOW_RANGE, 8.0)
         .add(Attributes.MAX_HEALTH, 2.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
   }

   public void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(2, new SmartMeleeAttackGoal(this, 1.4, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 0.7));
      this.goalSelector
         .addGoal(
            2,
            new SmartAvoidGoal<LivingEntity>(
               this, LivingEntity.class, (float)this.getAttributeValue(Attributes.FOLLOW_RANGE), 1.0, 1.3, input -> getEcoLevel(input) > getEcoLevel(this)
            )
         );
      this.goalSelector.addGoal(3, new AmphibiousTransition(this, 1.0));
      this.goalSelector.addGoal(3, new LayEggsOnNestGoal(this));
      this.goalSelector.addGoal(6, new SmartLookAtGoal(this, LivingEntity.class, 10.0F));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
   }

   public float getWalkTargetValue(BlockPos p_149140_, LevelReader p_149141_) {
      return 0.0F;
   }

   private boolean isAquatic() {
      return getEntityData(this.getType()).getFlags(this.getVariant(), "isAquatic") == 1;
   }

   @Override
   public boolean wantsToBeOnLand() {
      return !this.isAquatic();
   }

   @Override
   public boolean wantsToBeInWater() {
      return this.isAquatic();
   }

   public boolean isPushedByFluid() {
      return false;
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.003, 0.0));
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }
      } else {
         if ((double)Math.abs(this.getYRot() - this.yRotO) > 0.005) {
            this.offset = Mth.rotLerp(0.05F, this.offset, this.getYRot() - this.yRotO);
         }

         if (this.isInWater() && !this.onGround() && this.swimProgress < 20) {
            this.swimProgress++;
         } else if ((!this.isInWater() || this.onGround()) && this.swimProgress > 0) {
            this.swimProgress--;
         }
      }
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityNewt> list = this.level().getEntitiesOfClass(EntityNewt.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         return list.size() >= 1;
      } else {
         return false;
      }
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      EntityUtils.dropEggs(this, "egg_newt", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_newt", itemstack);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
      SoundEvent soundevent = SoundEvents.TURTLE_SHAMBLE_BABY;
      this.playSound(soundevent, 0.15F, 1.0F);
   }

   @Override
   public float getModelScale() {
      return 0.8F;
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
      return (Block)ModBlock.NEST_AMPHIBIAN.get();
   }

   @Override
   public boolean isValidNestBlock(BlockPos pos) {
      return this.level().isWaterAt(pos)
         && this.level().getBlockState(pos.below()).is(ModTags.ModBlockTags.VALID_REPTILE_NEST)
         && this.getNestType().defaultBlockState().canSurvive(this.level(), pos);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("has_egg", this.wantsToLayEggs());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setEggStatus(compound.getBoolean("has_egg"));
   }
}
