package untamedwilds.entity.reptile;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.INestingMob;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.AmphibiousRandomSwimGoal;
import untamedwilds.entity.ai.AmphibiousTransition;
import untamedwilds.entity.ai.LayEggsOnNestGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.control.look.SmartSwimmerLookControl;
import untamedwilds.entity.ai.control.movement.SmartSwimmingMoveControl;
import untamedwilds.entity.ai.target.HuntMobTarget;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class EntitySoftshellTurtle extends ComplexMobAmphibious implements ISpecies, INewSkins, INestingMob {
   private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(EntitySoftshellTurtle.class, EntityDataSerializers.BOOLEAN);
   public boolean hasExtendedNeck;
   public int extendNeckProgress;
   public Pair<Float, Float> head_movement;
   private float neck_val = 0.0F;
   private float head_val = 0.0F;

   public EntitySoftshellTurtle(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new SmartSwimmingMoveControl(this, 60, 10, 0.6F, 0.25F, true);
      this.lookControl = new SmartSwimmerLookControl(this, 20);
      this.head_movement = new Pair(0.0F, 0.0F);
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
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 6.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 2.0);
   }

   public void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(2, new SmartMeleeAttackGoal(this, 1.0, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 0.7));
      this.goalSelector.addGoal(2, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.0, 1.1, input -> getEcoLevel(input) > getEcoLevel(this)));
      this.goalSelector.addGoal(3, new LayEggsOnNestGoal(this));
      this.goalSelector.addGoal(3, new AmphibiousTransition(this, 1.0));
      this.goalSelector.addGoal(4, new AmphibiousRandomSwimGoal(this, 0.7, 40));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
      this.targetSelector.addGoal(3, new HuntMobTarget<LivingEntity>(this, LivingEntity.class, true, 30, false, input -> getEcoLevel(input) < getEcoLevel(this)));
   }

   @Override
   public boolean wantsToBeOnLand() {
      return this.level().getDayTime() > 4500L && this.level().getDayTime() < 7500L;
   }

   @Override
   public boolean wantsToBeInWater() {
      return this.level().getDayTime() <= 4500L || this.level().getDayTime() >= 7500L;
   }

   public boolean isPushedByFluid() {
      return false;
   }

   @Override
   public void die(DamageSource cause) {
      if (cause.is(DamageTypeTags.NO_IMPACT) && !this.isBaby()) {
         ItemEntity entityitem = this.spawnAtLocation(new ItemStack((ItemLike)ModItems.FOOD_TURTLE_SOUP.get()), 0.2F);
         if (entityitem != null) {
            entityitem.getItem().setCount(1);
         }
      }

      super.die(cause);
   }

   public void tick() {
      super.tick();
      if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03) {
         Vec3 vec3 = this.getViewVector(0.0F);
         float f = Mth.cos(this.getYRot() * (float) (Math.PI / 180.0)) * 0.3F;
         float f1 = Mth.sin(this.getYRot() * (float) (Math.PI / 180.0)) * 0.3F;
         float f2 = 1.2F - this.random.nextFloat() * 0.7F;

         for (int i = 0; i < 2; i++) {
            this.level()
               .addParticle(
                  ParticleTypes.DOLPHIN,
                  this.getX() - vec3.x * (double)f2 + (double)f,
                  this.getY() - vec3.y,
                  this.getZ() - vec3.z * (double)f2 + (double)f1,
                  0.0,
                  0.0,
                  0.0
               );
            this.level()
               .addParticle(
                  ParticleTypes.DOLPHIN,
                  this.getX() - vec3.x * (double)f2 - (double)f,
                  this.getY() - vec3.y,
                  this.getZ() - vec3.z * (double)f2 - (double)f1,
                  0.0,
                  0.0,
                  0.0
               );
         }
      }

      if (this.tickCount % 1000 == 0) {
         this.hasExtendedNeck = this.random.nextBoolean();
      }

      if (this.tickCount % 120 < 11) {
         if (this.tickCount % 120 == 1) {
            this.neck_val = (float)(0.8F - this.random.nextDouble() * 1.6F);
            this.head_val = (float)(0.4F - this.random.nextDouble() * 0.8F);
         }

         this.head_movement = new Pair(
            Mth.lerp(0.1F, (Float)this.head_movement.getA(), this.neck_val), Mth.lerp(0.1F, (Float)this.head_movement.getB(), this.head_val)
         );
      }
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         if (this.isInWater() && this.getNavigation().isDone()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.003, 0.0));
         }
      } else if ((!this.isInWater() || this.hasExtendedNeck) && this.extendNeckProgress < 100) {
         this.extendNeckProgress++;
      } else if ((this.isInWater() || !this.hasExtendedNeck) && this.extendNeckProgress > 0) {
         this.extendNeckProgress--;
      }
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntitySoftshellTurtle> list = this.level().getEntitiesOfClass(EntitySoftshellTurtle.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         return list.size() >= 1;
      } else {
         return false;
      }
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      EntityUtils.dropEggs(this, "egg_softshell_turtle", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (itemstack.isEmpty() && this.isAlive()) {
         EntityUtils.turnEntityIntoItem(this, "spawn_softshell_turtle");
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
      SoundEvent soundevent = this.isBaby() ? SoundEvents.TURTLE_SHAMBLE_BABY : SoundEvents.TURTLE_SHAMBLE;
      this.playSound(soundevent, 0.15F, 1.0F);
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
      compound.putBoolean("has_egg", this.wantsToLayEggs());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setEggStatus(compound.getBoolean("has_egg"));
   }
}
