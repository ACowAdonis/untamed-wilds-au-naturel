package untamedwilds.entity.amphibian;

import com.github.alexthe666.citadel.animation.Animation;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity.RemovalReason;
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
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INestingMob;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.AmphibiousRandomSwimGoal;
import untamedwilds.entity.ai.LayEggsOnNestGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.control.look.SmartSwimmerLookControl;
import untamedwilds.entity.ai.control.movement.SmartSwimmingMoveControl;
import untamedwilds.entity.ai.target.HuntMobTarget;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class EntityGiantSalamander extends ComplexMobAmphibious implements ISpecies, INeedsPostUpdate, INewSkins, INestingMob {
   private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(EntityGiantSalamander.class, EntityDataSerializers.BOOLEAN);
   public static Animation ATTACK_SWALLOW;
   public int swimProgress;
   public float offset;

   public EntityGiantSalamander(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new SmartSwimmingMoveControl(this, 40, 5, 0.25F, 0.3F, true);
      this.lookControl = new SmartSwimmerLookControl(this, 20);
      ATTACK_SWALLOW = Animation.create(15);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(HAS_EGG, false);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 2.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.1)
         .add(Attributes.MOVEMENT_SPEED, 0.8)
         .add(Attributes.FOLLOW_RANGE, 8.0)
         .add(Attributes.MAX_HEALTH, 10.0)
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
               this, LivingEntity.class, (float)this.getAttributeValue(Attributes.FOLLOW_RANGE), 1.0, 1.1, input -> getEcoLevel(input) > getEcoLevel(this)
            )
         );
      this.goalSelector.addGoal(3, new LayEggsOnNestGoal(this));
      this.goalSelector.addGoal(4, new AmphibiousRandomSwimGoal(this, 0.7, 400));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
      this.targetSelector
         .addGoal(
            3, new HuntMobTarget<LivingEntity>(this, LivingEntity.class, true, false, input -> getEcoLevel(input) < getEcoLevel(this) && input.isInWater())
         );
   }

   public float getWalkTargetValue(BlockPos p_149140_, LevelReader p_149141_) {
      return 0.0F;
   }

   @Override
   public boolean wantsToBeOnLand() {
      return this.level().isRainingAt(this.blockPosition());
   }

   @Override
   public boolean wantsToBeInWater() {
      return true;
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
            if (!this.isNotMoving() && this.random.nextInt(5) == 0 && this.getDeltaMovement().horizontalDistance() > 0.08) {
               Vec3 testpos = this.position()
                  .add(
                     Math.cos(Math.toRadians((double)(this.getYRot() + 90.0F))) * -0.8,
                     0.0,
                     Math.sin(Math.toRadians((double)(this.getYRot() + 90.0F))) * -0.8
                  );
               BlockPos testblockpos = new BlockPos((int)testpos.x, (int)testpos.y, (int)testpos.z);
               if (this.level().getBlockState(new BlockPos(testblockpos.below())).is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                  ((ServerLevel)this.level())
                     .sendParticles(
                        new BlockParticleOption(ParticleTypes.FALLING_DUST, this.level().getBlockState(testblockpos.below())),
                        testpos.x,
                        testpos.y + 0.2,
                        testpos.z,
                        2,
                        (double)this.random.nextFloat() * 0.2,
                        (double)this.random.nextFloat() * 0.2,
                        (double)this.random.nextFloat() * 0.2,
                        0.0
                     );
               } else {
                  ((ServerLevel)this.level())
                     .sendParticles(
                        ParticleTypes.UNDERWATER,
                        testpos.x,
                        testpos.y + 0.2,
                        testpos.z,
                        2,
                        (double)this.random.nextFloat() * 0.2,
                        (double)this.random.nextFloat() * 0.2,
                        (double)this.random.nextFloat() * 0.2,
                        0.0
                     );
               }
            }
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }
      } else {
         if ((double)Math.abs(this.getYRot() - this.yRotO) > 0.005) {
            this.offset = Mth.rotLerp(0.05F, this.offset, this.getYRot() - this.yRotO);
         }

         if (this.isInWater() && !this.isNotMoving()) {
            if (this.swimProgress < 20) {
               this.swimProgress++;
            }
         } else if ((!this.isInWater() || this.isNotMoving()) && this.swimProgress > 0) {
            this.swimProgress--;
         }
      }
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityGiantSalamander> list = this.level().getEntitiesOfClass(EntityGiantSalamander.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         return list.size() >= 1;
      } else {
         return false;
      }
   }

   public boolean doHurtTarget(Entity entityIn) {
      float f = (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
      boolean flag = entityIn.hurt(this.damageSources().mobAttack(this), f);
      if (!flag) {
         return false;
      } else {
         if (entityIn instanceof LivingEntity
            && entityIn.getBbWidth() * entityIn.getBbHeight() < 0.4F
            && (!(entityIn instanceof TamableAnimal) || !((TamableAnimal)entityIn).isTame())) {
            this.setAnimation(ATTACK_SWALLOW);
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BEEHIVE_ENTER, SoundSource.BLOCKS, 1.0F, 1.0F);
            EntityUtils.spawnParticlesOnEntity(this.level(), (LivingEntity)entityIn, ParticleTypes.POOF, 6, 2);
            this.setDeltaMovement(
               new Vec3(entityIn.getX() - this.getX(), entityIn.getY() - this.getY(), entityIn.getZ() - this.getZ()).scale(0.15F)
            );
            this.huntingCooldown = 12000;
            entityIn.remove(RemovalReason.KILLED);
         }

         return true;
      }
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      EntityUtils.dropEggs(this, "egg_giant_salamander", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_giant_salamander", itemstack);
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
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, ATTACK_SWALLOW};
   }

   @Override
   public void updateAttributes() {
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getAttack().floatValue());
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getHealth().floatValue());
      this.setHealth(this.getMaxHealth());
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
