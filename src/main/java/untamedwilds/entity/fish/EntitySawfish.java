package untamedwilds.entity.fish;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.target.HuntWoundedTarget;
import untamedwilds.entity.ai.target.SmartHurtByTargetGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class EntitySawfish extends ComplexMobAquatic implements ISpecies, IAnimatedEntity, INeedsPostUpdate, INewSkins {
   private static final EntityDataAccessor<Boolean> IS_BURROWING = SynchedEntityData.defineId(EntitySawfish.class, EntityDataSerializers.BOOLEAN);
   public static Animation ATTACK_THRASH;
   private int animationTick;
   private Animation currentAnimation;
   public int ringBufferIndex = -1;
   public final double[][] ringBuffer = new double[64][3];
   private int burrowCooldown;

   public EntitySawfish(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      ATTACK_THRASH = Animation.create(66);
      this.entityData.define(IS_BURROWING, false);
      this.turn_speed = 0.3F;
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 12.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.4)
         .add(Attributes.MOVEMENT_SPEED, 0.7)
         .add(Attributes.FOLLOW_RANGE, 24.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.MAX_HEALTH, 50.0);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new SmartMeleeAttackGoal(this, 1.8, false, 2.0F));
      this.goalSelector.addGoal(3, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(4, new ComplexMobAquatic.SwimGoal(this, 3, 200) {
         @Override
         public boolean canUse() {
            if (this.mob instanceof EntitySawfish sawfish && sawfish.isBurrowing()) {
               return false;
            }

            return super.canUse();
         }
      });
      this.targetSelector.addGoal(1, new SmartHurtByTargetGoal(this));
      this.targetSelector.addGoal(3, new HuntWoundedTarget<LivingEntity>(this, LivingEntity.class, true));
   }

   @Override
   public void aiStep() {
      super.aiStep();
      AnimationHandler.INSTANCE.updateAnimations(this);
      if (!this.level().isClientSide) {
         this.setAngry(this.getTarget() != null);
         if (this.tickCount % 1000 == 0 && this.wantsToBreed() && !this.isMale()) {
            this.setAge(this.getPregnancyTime());
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         if (this.burrowCooldown > 0) {
            this.burrowCooldown--;
         } else if (this.getAnimation() == NO_ANIMATION && this.getTarget() == null) {
            int i = this.random.nextInt(1500);
            if (i <= 8
               && !this.isBurrowing()
               && this.level().getBlockState(this.blockPosition().below()).is(ModTags.ModBlockTags.SOFT_SOIL)
               && this.getNavigation().isDone()) {
               this.setBurrowing(true);
               this.burrowCooldown = 3000 + this.getRandom().nextInt(6000);
            } else if (i <= 11 && this.isBurrowing()) {
               this.setBurrowing(false);
               EntityUtils.spawnParticlesOnEntity(
                  this.level(), this, new BlockParticleOption(ParticleTypes.BLOCK, this.level().getBlockState(this.blockPosition().below())), 20, 4
               );
               this.burrowCooldown = 3000 + this.getRandom().nextInt(6000);
            }
         }
      }

      if (this.isInWater() && this.getNavigation().isDone()) {
         this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
      }

      if (!this.isNoAi() && !this.isBaby()) {
         if (this.ringBufferIndex < 0) {
            for (int i = 0; i < this.ringBuffer.length; i++) {
               this.ringBuffer[i][0] = (double)this.getYRot();
               this.ringBuffer[i][1] = this.getY();
            }
         }

         this.ringBufferIndex++;
         if (this.ringBufferIndex == this.ringBuffer.length) {
            this.ringBufferIndex = 0;
         }

         this.ringBuffer[this.ringBufferIndex][0] = (double)(this.yRotO + 0.5F * Mth.wrapDegrees(this.getYRot() - this.yRotO));
         this.ringBuffer[this.ringBufferIndex][1] = this.getY();
      }
   }

   public double[] getMovementOffsets(int offset, float partialTicks) {
      if (this.isDeadOrDying()) {
         partialTicks = 0.0F;
      }

      partialTicks = 1.0F - partialTicks;
      int i = this.ringBufferIndex - offset & 63;
      int j = this.ringBufferIndex - offset - 1 & 63;
      double[] adouble = new double[3];
      double d0 = this.ringBuffer[i][0];
      double d1 = this.ringBuffer[j][0] - d0;
      adouble[0] = d0 + d1 * (double)partialTicks;
      d0 = this.ringBuffer[i][1];
      d1 = this.ringBuffer[j][1] - d0;
      adouble[1] = d0 + d1 * (double)partialTicks;
      adouble[2] = Mth.lerp((double)partialTicks, this.ringBuffer[i][2], this.ringBuffer[j][2]);
      return adouble;
   }

   @Override
   protected void handleOutOfWaterBehavior() {
      if (!this.isInWater() && this.onGround() && this.verticalCollision && this.random.nextInt(60) == 1) {
         for (LivingEntity entityHit : this.level().getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), this, this.getBoundingBox().inflate(1.2F))) {
            if (!entityHit.equals(this) && this.hasLineOfSight(entityHit)) {
               this.doHurtTarget(entityHit);
            }
         }

         this.setAnimation(ATTACK_THRASH);
         this.setDeltaMovement(
            this.getDeltaMovement()
               .add((double)((this.random.nextFloat() * 4.0F - 2.0F) * 0.1F), 0.4F, (double)((this.random.nextFloat() * 4.0F - 2.0F) * 0.1F))
         );
         this.setOnGround(false);
         this.setYRot(this.random.nextFloat() * 360.0F);
         this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntitySawfish> list = this.level().getEntitiesOfClass(EntitySawfish.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         if (list.size() >= 1) {
            this.setAge(this.getPregnancyTime());
            list.get(0).setAge(this.getPregnancyTime());
            return true;
         }
      }

      return false;
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      return this.create_offspring(new EntitySawfish((EntityType<? extends ComplexMob>)ModEntity.SAWFISH.get(), this.level()));
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.COD_FLOP;
   }

   public boolean doHurtTarget(Entity entityIn) {
      boolean flag = super.doHurtTarget(entityIn);
      if (flag && this.getAnimation() == NO_ANIMATION && !this.isBaby()) {
         if (this.getRandom().nextInt(3) == 0) {
            this.spawnAtLocation(new ItemStack((ItemLike)ModItems.MATERIAL_SHARK_TOOTH.get()), 0.2F);
         }

         this.setAnimation(ATTACK_THRASH);
      }

      return flag;
   }

   public boolean hurt(DamageSource source, float amount) {
      if (this.isBurrowing()) {
         this.setBurrowing(false);
      }

      return super.hurt(source, amount);
   }

   public int getAnimationTick() {
      return this.animationTick;
   }

   public void setAnimationTick(int tick) {
      this.animationTick = tick;
   }

   public Animation getAnimation() {
      return this.currentAnimation;
   }

   public void setAnimation(Animation animation) {
      this.currentAnimation = animation;
   }

   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, ATTACK_THRASH};
   }

   @Override
   public void updateAttributes() {
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getAttack().floatValue());
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getHealth().floatValue());
      this.setHealth(this.getMaxHealth());
   }

   public boolean isBurrowing() {
      return (Boolean)this.entityData.get(IS_BURROWING);
   }

   private void setBurrowing(boolean burrowing) {
      this.entityData.set(IS_BURROWING, burrowing);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("isBurrowing", this.isBurrowing());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setBurrowing(compound.getBoolean("isBurrowing"));
   }
}
