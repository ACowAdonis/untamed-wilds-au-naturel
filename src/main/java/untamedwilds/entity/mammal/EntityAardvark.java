package untamedwilds.entity.mammal;

import com.github.alexthe666.citadel.animation.Animation;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraftforge.common.ForgeMod;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.FindItemsGoal;
import untamedwilds.entity.ai.GotoSleepGoal;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartLookAtGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.SmartSwimGoal_Land;
import untamedwilds.entity.ai.SmartWanderGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.init.ModItems;
import untamedwilds.init.ModLootTables;
import untamedwilds.util.EntityUtils;

public class EntityAardvark extends ComplexMobTerrestrial implements ISpecies, INewSkins {
   private BlockPos lastDugPos = null;
   public static Animation WORK_DIG;
   public static Animation ATTACK;

   public EntityAardvark(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.turn_speed = 0.8F;
      WORK_DIG = Animation.create(76);
      ATTACK = Animation.create(18);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new SmartSwimGoal_Land(this));
      this.goalSelector.addGoal(2, new FindItemsGoal(this, 12, 100, false, true));
      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.6, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(2, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.2, 1.6, input -> getEcoLevel(input) > getEcoLevel(this)));
      this.goalSelector.addGoal(3, new GotoSleepGoal(this, 1.0));
      this.goalSelector.addGoal(5, new SmartWanderGoal(this, 1.0, 120, 0, false));
      this.goalSelector.addGoal(6, new SmartLookAtGoal(this, LivingEntity.class, 10.0F));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 1.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.1)
         .add(Attributes.MOVEMENT_SPEED, 0.18)
         .add(Attributes.FOLLOW_RANGE, 24.0)
         .add(Attributes.MAX_HEALTH, 8.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 0.0)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 0.5);
   }

   @Override
   public boolean wantsToBreed() {
      return !super.wantsToBreed() ? false : !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this) && this.getHunger() >= 80;
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         this.setAngry(this.getTarget() != null);
         if (this.level().getGameTime() % 1000L == 0L) {
            this.addHunger(-10);
            if (!this.isStarving()) {
               this.heal(1.0F);
            }
         }

         if (this.getAnimation() == NO_ANIMATION && this.getTarget() == null && !this.isSleeping() && this.getCommandInt() == 0) {
            int i = this.random.nextInt(3000);
            if (i == 13 && !this.isInWater() && this.isNotMoving() && this.canMove()) {
               this.setSitting(true);
            }

            if (i == 14 && this.isSitting()) {
               this.setSitting(false);
            }

            if (i > 2980
               && !this.isInWater()
               && this.getHunger() < 60
               && this.canMove()
               && this.getAnimation() == NO_ANIMATION
               && (this.lastDugPos == null || this.distanceToSqr((double)this.lastDugPos.getX(), this.getY(), (double)this.lastDugPos.getZ()) > 50.0)
               && this.level().getBlockState(this.blockPosition().below()).is(BlockTags.MINEABLE_WITH_SHOVEL)) {
               this.setAnimation(WORK_DIG);
               this.lastDugPos = this.blockPosition();
            }
         }

         if (this.getAnimation() == WORK_DIG && this.getAnimationTick() % 8 == 0) {
            ((ServerLevel)this.level())
               .sendParticles(
                  new BlockParticleOption(ParticleTypes.BLOCK, this.level().getBlockState(this.blockPosition().below())),
                  this.getX(),
                  this.getY(),
                  this.getZ(),
                  20,
                  0.0,
                  0.0,
                  0.0,
                  0.15F
               );
            this.playSound(SoundEvents.SHOVEL_FLATTEN, 0.8F, 0.6F);
            if (this.getAnimationTick() == 64) {
               int rand = this.random.nextInt(6);
               if (rand == 0) {
                  for (ItemStack itemstack : EntityUtils.getItemFromLootTable(ModLootTables.LOOT_DIGGING, this.level())) {
                     this.spawnAtLocation(itemstack);
                  }
               } else if (rand == 1) {
                  this.spawnAtLocation(new ItemStack((ItemLike)ModItems.VEGETABLE_AARDVARK_CUCUMBER.get()));
               }
            }
         }
      }

      if (this.getAnimation() != NO_ANIMATION && this.getAnimation() == ATTACK && (this.getAnimationTick() == 8 || this.getAnimationTick() == 12)) {
         this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 0.7F);
      }

      super.aiStep();
   }

   protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
      return sizeIn.height * 0.85F;
   }

   public boolean doHurtTarget(Entity entityIn) {
      boolean flag = super.doHurtTarget(entityIn);
      if (flag && this.getAnimation() == NO_ANIMATION && !this.isBaby()) {
         this.setAnimation(ATTACK);
         this.setAnimationTick(0);
      }

      return flag;
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, WORK_DIG, ATTACK};
   }

   @Nullable
   public EntityAardvark getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      return this.create_offspring(new EntityAardvark((EntityType<? extends ComplexMob>)ModEntity.AARDVARK.get(), this.level()));
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      if (this.lastDugPos != null) {
         compound.putInt("DugPosX", this.lastDugPos.getX());
         compound.putInt("DugPosZ", this.lastDugPos.getZ());
      }
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      if (compound.contains("LastDugPos")) {
         this.lastDugPos = new BlockPos(compound.getInt("DugPosX"), 0, compound.getInt("DugPosZ"));
      }
   }
}
