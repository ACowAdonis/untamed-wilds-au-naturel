package untamedwilds.entity;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ai.control.movement.SmartSwimmingMoveControl;
import untamedwilds.util.EntityUtils;

public abstract class ComplexMobTerrestrial extends ComplexMob implements IAnimatedEntity {
   public int sitProgress;
   public int ticksToSit;
   public int sleepProgress;
   public int forceSleep;
   protected int tiredCounter = 0;
   protected int buoyancy = 1;
   private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(ComplexMobTerrestrial.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Boolean> MAN_EATER = SynchedEntityData.defineId(ComplexMobTerrestrial.class, EntityDataSerializers.BOOLEAN);
   private int animationTick;
   private Animation currentAnimation;
   public float turn_speed = 0.2F;
   protected float swimSpeedMult = 1.0F;

   public ComplexMobTerrestrial(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new SmartSwimmingMoveControl(this, 85, 10, 1.0F, 1.0F, true);
      this.lookControl = new SmoothSwimmingLookControl(this, 30);
      this.ticksToSit = 40;
   }

   protected PathNavigation createNavigation(Level worldIn) {
      return new ComplexMobTerrestrial.AmphibiousPathNavigation(this, worldIn);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(HUNGER, 79);
      this.entityData.define(MAN_EATER, false);
   }

   @Override
   public void aiStep() {
      AnimationHandler.INSTANCE.updateAnimations(this);
      if (!this.level().isClientSide) {
         if (this.forceSleep > 0) {
            this.forceSleep--;
         } else if (this.forceSleep < 0) {
            this.forceSleep++;
         }

         if (!this.getNavigation().isDone() && (this.isSitting() || this.isSleeping())) {
            this.setSitting(false);
            this.setSleeping(false);
         }

         if (!this.isSleeping() && this.forceSleep > 0) {
            this.setSleeping(true);
         }

         if (this.tickCount % 200 == 0 && !this.isActive() && this.getNavigation().isDone()) {
            this.tiredCounter++;
            if (this.distanceToSqr(this.getHomeAsVec()) <= 6.0) {
               this.setSleeping(true);
               this.tiredCounter = 0;
            } else if (this.tiredCounter >= 3) {
               this.setHome(BlockPos.ZERO);
               this.tiredCounter = 0;
            }

            this.moveControl.setWantedPosition((double)this.getHome().getX(), (double)this.getHome().getY(), (double)this.getHome().getZ(), 1.0);
         }
      }

      if (this.isSitting() && this.sitProgress < this.ticksToSit) {
         this.sitProgress++;
      } else if (!this.isSitting() && this.sitProgress > 0) {
         this.sitProgress--;
      }

      if (this.isSleeping() && this.sleepProgress < 40) {
         this.sleepProgress++;
      } else if (!this.isSleeping() && this.sleepProgress > 0) {
         this.sleepProgress--;
      }

      super.aiStep();
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide()) {
         ItemStack itemstack = player.getItemInHand(hand);
         if (!this.isBaby() && this.isFood(itemstack) && !this.dead) {
            if (!this.level().isClientSide && !player.isCreative()) {
               itemstack.shrink(1);
            }

            if ((Boolean)ConfigGamerules.playerBreeding.get() && this.age == 0) {
               this.setInLove(player);
               EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.HEART, 7, 1);
            }

            this.setAnimation(this.getAnimationEat());
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
         }
      }

      return super.mobInteract(player, hand);
   }

   protected ComplexMobTerrestrial.ActivityType getActivityType() {
      return getEntityData(this.getType()).getActivityType(this.getVariant());
   }

   public boolean isActive() {
      ComplexMobTerrestrial.ActivityType type = this.getActivityType();
      Pair<Integer, Integer> times = type.getTimes();
      if ((!this.isTame() || this.getCommandInt() == 0) && (Boolean)ConfigGamerules.sleepBehaviour.get()) {
         if (type == ComplexMobTerrestrial.ActivityType.CATHEMERAL) {
            return this.tickCount % 17000 < 3000;
         } else {
            long time = this.level().getDayTime();
            if (((Integer)times.getFirst()).equals(times.getSecond())) {
               return this.forceSleep >= 0;
            } else {
               return times.getFirst() > times.getSecond()
                  ? time > (long)((Integer)times.getFirst()).intValue() || time < (long)((Integer)times.getSecond()).intValue()
                  : time > (long)((Integer)times.getFirst()).intValue() && time < (long)((Integer)times.getSecond()).intValue();
            }
         }
      } else {
         return true;
      }
   }

   private void setHunger(int hunger) {
      this.entityData.set(HUNGER, hunger);
   }

   public int getHunger() {
      return (Integer)this.entityData.get(HUNGER);
   }

   public boolean isStarving() {
      return this.getHunger() <= 0;
   }

   public boolean isManEater() {
      return this.entityData.get(MAN_EATER);
   }

   public void setManEater(boolean manEater) {
      this.entityData.set(MAN_EATER, manEater);
   }

   public void addHunger(int change) {
      int i = this.getHunger() + change;
      this.setHunger(i > 200 ? 200 : Math.max(i, 0));
   }

   public boolean hurt(DamageSource source, float amount) {
      if (this.isSitting()) {
         this.setSitting(false);
      }

      if (this.isSleeping() && this.forceSleep <= 0) {
         this.setSleeping(false);
         this.forceSleep = -4000;
      }

      return super.hurt(source, amount);
   }

   public void die(DamageSource p_70645_1_) {
      if (!this.level().isClientSide
         && !(Boolean)ConfigGamerules.hardcoreDeath.get()
         && this.getHome() != BlockPos.ZERO
         && this.isTame()
         && this.getHunger() != 0) {
         this.addEffect(new MobEffectInstance(MobEffects.GLOWING, 800, 0));
         this.setHealth(0.5F);
         this.setHunger(0);
         if (!this.randomTeleport((double)this.getHome().getX(), (double)this.getHome().getY(), (double)this.getHome().getZ(), true)) {
            super.die(p_70645_1_);
         }
      } else {
         super.die(p_70645_1_);
      }
   }

   public void travel(Vec3 p_149181_) {
      if (this.isControlledByLocalInstance() && this.isInWater() && this.getAirSupply() > 40) {
         this.moveRelative(this.getSpeed(), p_149181_);
         this.move(MoverType.SELF, this.getDeltaMovement());
         this.setDeltaMovement(this.getDeltaMovement().scale(0.3));
      } else {
         super.travel(p_149181_);
      }
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
      return new Animation[0];
   }

   public Animation getAnimationEat() {
      return NO_ANIMATION;
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("Sleeping", this.isSleeping());
      compound.putInt("SleepingTicks", this.forceSleep);
      compound.putBoolean("Sitting", this.isSitting());
      compound.putInt("Hunger", this.getHunger());
      compound.putBoolean("ManEater", this.isManEater());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setSleeping(compound.getBoolean("Sleeping"));
      this.forceSleep = compound.getInt("SleepingTicks");
      this.setSitting(compound.getBoolean("Sitting"));
      this.setHunger(compound.getInt("Hunger"));
      this.setManEater(compound.getBoolean("ManEater"));
   }

   public static enum ActivityType {
      DIURNAL("diurnal", 1000, 16000),
      NOCTURNAL("nocturnal", 13000, 4000),
      CREPUSCULAR("crepuscular", 8000, 23000),
      CATHEMERAL("cathemeral", -1, -1),
      INSOMNIAC("insomniac", -1, -1);

      public int wakeUp;
      public int sleep;
      public String name;
      public static final Codec<ComplexMobTerrestrial.ActivityType> CODEC = Codec.STRING
         .comapFlatMap(ComplexMobTerrestrial.ActivityType::getByName, ComplexMobTerrestrial.ActivityType::toString)
         .stable();

      private ActivityType(String name, int wakeUp, int sleep) {
         this.wakeUp = wakeUp;
         this.sleep = sleep;
         this.name = name;
      }

      private static DataResult<ComplexMobTerrestrial.ActivityType> getByName(String path) {
         return switch (path) {
            case "diurnal" -> DataResult.success(DIURNAL);
            case "nocturnal" -> DataResult.success(NOCTURNAL);
            case "crepuscular" -> DataResult.success(CREPUSCULAR);
            case "cathemeral" -> DataResult.success(CATHEMERAL);
            default -> DataResult.success(INSOMNIAC);
         };
      }

      @Override
      public String toString() {
         return this.name;
      }

      public Pair<Integer, Integer> getTimes() {
         return new Pair(this.wakeUp, this.sleep);
      }
   }

   static class AmphibiousPathNavigation extends WaterBoundPathNavigation {
      private final ComplexMobTerrestrial entityIn;

      AmphibiousPathNavigation(ComplexMobTerrestrial entityIn, Level worldIn) {
         super(entityIn, worldIn);
         this.entityIn = entityIn;
      }

      protected boolean canUpdatePath() {
         return true;
      }

      protected PathFinder createPathFinder(int p_149222_) {
         this.nodeEvaluator = new AmphibiousNodeEvaluator(false);
         return new PathFinder(this.nodeEvaluator, p_149222_);
      }

      public boolean isStableDestination(BlockPos destinationIn) {
         BlockPos blockpos = destinationIn.below();
         return this.level.getBlockState(blockpos).isSolidRender(this.level, blockpos);
      }
   }
}
