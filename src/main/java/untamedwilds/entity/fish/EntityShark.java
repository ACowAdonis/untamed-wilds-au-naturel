package untamedwilds.entity.fish;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import untamedwilds.entity.ai.MeleeAttackCircle;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.target.HuntWoundedTarget;
import untamedwilds.entity.ai.target.SmartHurtByTargetGoal;
import untamedwilds.entity.ai.unique.SharkSwimmingGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.init.ModItems;
import untamedwilds.util.EntityUtils;

public class EntityShark extends ComplexMobAquatic implements ISpecies, IAnimatedEntity, INeedsPostUpdate, INewSkins {
   private static final EntityDataAccessor<Boolean> SHORT_FINS = SynchedEntityData.defineId(EntityShark.class, EntityDataSerializers.BOOLEAN);
   public static Animation ATTACK_THRASH;
   private int animationTick;
   private Animation currentAnimation;
   public int posPointer = -1;
   public final double[][] positions = new double[64][3];

   public EntityShark(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      ATTACK_THRASH = Animation.create(15);
      this.turn_speed = 0.3F;
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(SHORT_FINS, false);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 12.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.4)
         .add(Attributes.MOVEMENT_SPEED, 0.8)
         .add(Attributes.FOLLOW_RANGE, 32.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.MAX_HEALTH, 50.0);
   }

   @Override
   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(2, new MeleeAttackCircle(this, 2.3, false, 2.0F));
      this.goalSelector.addGoal(3, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(4, new SharkSwimmingGoal(this));
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
      }

      if (!this.isNoAi() && !this.isBaby()) {
         if (this.posPointer < 0) {
            for (int i = 0; i < this.positions.length; i++) {
               this.positions[i][0] = (double)this.getYRot();
               this.positions[i][1] = this.getY();
            }
         }

         this.posPointer++;
         if (this.posPointer == this.positions.length) {
            this.posPointer = 0;
         }

         this.positions[this.posPointer][0] = (double)this.getYRot();
         this.positions[this.posPointer][1] = (double)this.getXRot();
      }
   }

   public double getMovementOffsets(int offset, float partialTicks, int value) {
      if (this.isDeadOrDying()) {
         partialTicks = 0.0F;
      }

      partialTicks = 1.0F - partialTicks;
      int i = this.posPointer - offset & 63;
      int j = this.posPointer - offset - 1 & 63;
      double d0 = this.positions[i][value];
      double d1 = this.positions[j][value] - d0;
      return d0 + d1 * (double)partialTicks;
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
         List<EntityShark> list = this.level().getEntitiesOfClass(EntityShark.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
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
      return this.create_offspring(new EntityShark((EntityType<? extends ComplexMob>)ModEntity.SHARK.get(), this.level()));
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

   public boolean isBottomDweller() {
      return getEntityData(this.getType()).getFlags(this.getVariant(), "bottomDweller") == 1;
   }

   @Override
   public void updateAttributes() {
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getAttack().floatValue());
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getHealth().floatValue());
      this.setHealth(this.getMaxHealth());
      this.setShortFins(getEntityData(this.getType()).getFlags(this.getVariant(), "shortFins") == 1);
   }

   public boolean hasShortFins() {
      return (Boolean)this.entityData.get(SHORT_FINS);
   }

   private void setShortFins(boolean short_fins) {
      this.entityData.set(SHORT_FINS, short_fins);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("hasShortFins", this.hasShortFins());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setShortFins(compound.getBoolean("hasShortFins"));
   }
}
