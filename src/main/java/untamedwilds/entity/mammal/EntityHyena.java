package untamedwilds.entity.mammal;

import com.github.alexthe666.citadel.animation.Animation;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.IPackEntity;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.FindItemsGoal;
import untamedwilds.entity.ai.FollowParentGoal;
import untamedwilds.entity.ai.GotoSleepGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartFollowOwnerGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.entity.ai.SmartSwimGoal_Land;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.entity.ai.target.AngrySleeperTarget;
import untamedwilds.entity.ai.target.HuntPackMobTarget;
import untamedwilds.entity.ai.target.HuntPlayerTarget;
import untamedwilds.entity.ai.target.HurtPackByTargetGoal;
import untamedwilds.entity.ai.target.SmartOwnerHurtTargetGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.init.ModSounds;
import untamedwilds.util.EntityUtils;

public class EntityHyena extends ComplexMobTerrestrial implements INewSkins, ISpecies, IPackEntity, INeedsPostUpdate {
   public static Animation ATTACK_POUNCE;
   public static Animation IDLE_TALK;
   public static Animation ATTACK_BITE;

   public EntityHyena(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      IDLE_TALK = Animation.create(20);
      ATTACK_POUNCE = Animation.create(42);
      ATTACK_BITE = Animation.create(15);
      this.turn_speed = 0.1F;
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new SmartSwimGoal_Land(this));
      this.goalSelector.addGoal(2, new FindItemsGoal(this, 12, true));
      this.goalSelector.addGoal(2, new SmartMeleeAttackGoal(this, 1.8, false, 1.0F, false, false));
      this.goalSelector.addGoal(3, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.2, 1.6, input -> getEcoLevel(input) > getEcoLevel(this)));
      this.goalSelector.addGoal(4, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(4, new GotoSleepGoal(this, 1.0));
      this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
      this.goalSelector.addGoal(5, new SmartWanderGoal(this, 1.0, true));
      this.goalSelector.addGoal(6, new SmartLookAtGoal(this, LivingEntity.class, 10.0F));
      this.targetSelector.addGoal(1, new HurtPackByTargetGoal(this).setAlertOthers());
      this.targetSelector
         .addGoal(2, new HuntPackMobTarget<LivingEntity>(this, LivingEntity.class, true, 30, false, input -> getEcoLevel(input) < getEcoLevel(this)));
      this.targetSelector.addGoal(2, new HuntPlayerTarget(this, 30));
      this.targetSelector.addGoal(3, new AngrySleeperTarget<LivingEntity>(this, LivingEntity.class, true));
   }

   protected void reassessTameGoals() {
      if (this.isTame()) {
         this.goalSelector.addGoal(3, new SmartFollowOwnerGoal(this, 1.3, 12.0F, 3.0F));
         this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
         this.targetSelector.addGoal(2, new SmartOwnerHurtTargetGoal(this));
      }
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 6.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.4)
         .add(Attributes.MOVEMENT_SPEED, 0.2)
         .add(Attributes.FOLLOW_RANGE, 24.0)
         .add(Attributes.MAX_HEALTH, 20.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.2)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0);
   }

   @Override
   public boolean wantsToBreed() {
      return ConfigGamerules.naturalBreeding.get() && this.age == 0 ? this.getHunger() >= 80 : false;
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         if (this.herd == null) {
            IPackEntity.initPack(this);
         } else {
            this.herd.tick();
         }

         if (this.level().getGameTime() % 1000L == 0L) {
            this.addHunger(-10);
            if (!this.isStarving()) {
               this.heal(1.0F);
            }
         }

         if (this.getAnimation() == NO_ANIMATION && this.getTarget() == null && !this.isSleeping() && this.getCommandInt() == 0) {
            int i = this.random.nextInt(3000);
            if (i == 0 && !this.isInWater() && this.isNotMoving() && this.canMove() && this.isActive()) {
               this.getNavigation().stop();
               this.setSitting(true);
            }

            if ((i == 1 || this.isInWater()) && this.isSitting() && this.getCommandInt() < 2) {
               this.setSitting(false);
            }

            if (i > 2980 && !this.isInWater() && !this.isBaby()) {
               this.setAnimation(IDLE_TALK);
            }
         }

         this.setAngry(this.getTarget() != null);
         if (this.getAnimation() == ATTACK_POUNCE && this.getAnimationTick() == 10) {
            this.getMoveControl().strafe(2.0F, 0.0F);
            this.getJumpControl().jump();
         }

         if (this.getAnimation() == IDLE_TALK && this.getAnimationTick() == 1 && this.getAmbientSound() != null) {
            this.playSound(this.getAmbientSound(), this.getSoundVolume(), this.getVoicePitch());
         }

         if (this.getTarget() != null && this.tickCount % 120 == 0) {
            this.playSound(this.getThreatSound(), this.getSoundVolume(), this.getVoicePitch());
         }
      }

      if (this.getAnimation() != NO_ANIMATION && this.getAnimation() == ATTACK_BITE && this.getAnimationTick() == 6) {
         this.playSound(ModSounds.ENTITY_ATTACK_BITE, 1.5F, 0.8F);
      }

      super.aiStep();
   }

   public boolean doHurtTarget(Entity entityIn) {
      boolean flag = super.doHurtTarget(entityIn);
      if (flag && this.getAnimation() == NO_ANIMATION && !this.isBaby()) {
         Animation anim = this.chooseAttackAnimation();
         this.setAnimation(anim);
      }

      return flag;
   }

   protected void playStepSound(BlockPos pos, BlockState blockIn) {
      this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
   }

   private Animation chooseAttackAnimation() {
      return switch (this.random.nextInt(4)) {
         case 0 -> ATTACK_POUNCE;
         case 1 -> ATTACK_POUNCE;
         default -> ATTACK_BITE;
      };
   }

   @Nullable
   public EntityHyena getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      return this.create_offspring(new EntityHyena((EntityType<? extends ComplexMob>)ModEntity.HYENA.get(), this.level()));
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND
         && !this.level().isClientSide()
         && !this.isTame()
         && this.isBaby()
         && EntityUtils.hasFullHealth(this)
         && this.isFood(itemstack)) {
         this.playSound(SoundEvents.HORSE_EAT, 1.5F, 0.8F);
         if (this.getRandom().nextInt(3) == 0) {
            this.tame(player);
            EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.HEART, 3, 6);
         } else {
            EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.SMOKE, 3, 3);
         }
      }

      return super.mobInteract(player, hand);
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, ATTACK_POUNCE, ATTACK_BITE, IDLE_TALK};
   }

   @Override
   public Animation getAnimationEat() {
      return NO_ANIMATION;
   }

   @Override
   public void updateAttributes() {
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getAttack().floatValue());
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)getEntityData(this.getType()).getSpeciesData().get(this.getVariant()).getHealth().floatValue());
      this.setHealth(this.getMaxHealth());
   }

   @Override
   public SpawnGroupData finalizeSpawn(
      ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag
   ) {
      SpawnGroupData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

      // Roll for man-eater status
      if (ConfigGamerules.predatorPlayerHunting.get()) {
         double manEaterChance = ConfigGamerules.predatorManEaterChance.get();
         if (this.random.nextDouble() < manEaterChance) {
            this.setManEater(true);
         }
      }

      return data;
   }
}
