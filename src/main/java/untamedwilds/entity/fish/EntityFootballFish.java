package untamedwilds.entity.fish;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.target.HuntMobTarget;
import untamedwilds.util.EntityUtils;

public class EntityFootballFish extends ComplexMobAquatic implements ISpecies, INewSkins, INeedsPostUpdate {
   private static final EntityDataAccessor<Boolean> HAS_MALE = SynchedEntityData.defineId(EntityFootballFish.class, EntityDataSerializers.BOOLEAN);

   public EntityFootballFish(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(HAS_MALE, false);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 3.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.42)
         .add(Attributes.FOLLOW_RANGE, 8.0)
         .add(Attributes.MAX_HEALTH, 8.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 2.0);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new SmartMeleeAttackGoal(this, 1.8, false, 2.0F));
      this.goalSelector.addGoal(2, new PanicGoal(this, 1.25));
      this.goalSelector.addGoal(4, new ComplexMobAquatic.SwimGoal(this, 4));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
      this.targetSelector.addGoal(2, new HuntMobTarget<LivingEntity>(this, LivingEntity.class, true, false, input -> getEcoLevel(input) < 5));
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.tickCount % 1000 == 0) {
            if (this.wantsToBreed() && !this.isMale()) {
               this.breed();
            }

            if (!this.hasAttachedMale() && this.random.nextInt(40) == 0 && this.getY() < 42.0) {
               this.setAttachedMale(true);
            }
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         if (this.random.nextInt(18) == 0) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.GLOW, this.getX(), this.getY() + 0.4, this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
         }
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.hasAttachedMale() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         MutableBlockPos blockPos = new MutableBlockPos();

         for (int i = 0; i <= 16; i++) {
            BlockState state = this.level().getBlockState(blockPos.set(this.getX(), this.getY() + (double)i, this.getZ()));
            if (!state.getFluidState().is(FluidTags.WATER)) {
               return false;
            }
         }

         this.setAge(this.getPregnancyTime());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide() && this.hasAttachedMale() && itemstack.getItem() == Items.SHEARS) {
         this.playSound(SoundEvents.SHEEP_SHEAR, 1.5F, 0.8F);
         this.setAttachedMale(false);
         this.hurt(this.damageSources().mobAttack(player), 1.0F);
      }

      return super.mobInteract(player, hand);
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      if (!this.hasAttachedMale()) {
         this.setAttachedMale(true);
      }

      EntityUtils.dropEggs(this, "egg_football_fish", this.getOffspring());
      return null;
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.GUARDIAN_FLOP;
   }

   @Override
   public void updateAttributes() {
      this.setGender(1);
      if (this.random.nextInt(3) == 0) {
         this.setAttachedMale(true);
      }
   }

   public boolean hasAttachedMale() {
      return (Boolean)this.entityData.get(HAS_MALE);
   }

   private void setAttachedMale(boolean attachedMale) {
      this.entityData.set(HAS_MALE, attachedMale);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("hasMale", this.hasAttachedMale());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setAttachedMale(compound.getBoolean("hasMale"));
   }
}
