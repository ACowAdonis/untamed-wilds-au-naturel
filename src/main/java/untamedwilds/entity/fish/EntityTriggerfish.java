package untamedwilds.entity.fish;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartMeleeAttackGoal;
import untamedwilds.util.EntityUtils;

public class EntityTriggerfish extends ComplexMobAquatic implements ISpecies, INewSkins {
   public EntityTriggerfish(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 2.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.6)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 6.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 2.0);
   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(0, new SmartMeleeAttackGoal(this, 1.8, false, 2.0F));
      this.goalSelector.addGoal(2, new PanicGoal(this, 1.25));
      this.goalSelector.addGoal(3, new EntityTriggerfish.TriggerFishBlowGoal(this, 600));
      this.goalSelector.addGoal(4, new ComplexMobAquatic.SwimGoal(this, 5));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         if (this.tickCount % 1000 == 0 && this.wantsToBreed() && !this.isMale()) {
            this.breed();
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         this.setAngry(this.getTarget() != null);
      }
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_triggerfish", itemstack);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityTriggerfish> list = this.level().getEntitiesOfClass(EntityTriggerfish.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
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
      EntityUtils.dropEggs(this, "egg_triggerfish", this.getOffspring());
      return null;
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.GUARDIAN_FLOP;
   }

   public class TriggerFishBlowGoal extends MoveToBlockGoal {
      private final ComplexMobAquatic taskOwner;
      private final int chance;
      private boolean taskComplete;
      private int counter = 0;

      public TriggerFishBlowGoal(ComplexMobAquatic entityIn, int chance) {
         super(entityIn, 1.0, 3, 4);
         this.taskOwner = entityIn;
         this.chance = chance;
      }

      public boolean canUse() {
         super.canUse();
         if (this.taskOwner.getRandom().nextInt(this.chance) != 0 && !this.blockPos.equals(BlockPos.ZERO)) {
            return false;
         } else {
            this.taskComplete = false;
            return true;
         }
      }

      protected void moveMobToBlock() {
         this.mob
            .getNavigation()
            .moveTo(
               (double)((float)this.blockPos.getX()) + 0.5,
               (double)(this.blockPos.getY() + 2),
               (double)((float)this.blockPos.getZ()) + 0.5,
               this.speedModifier
            );
      }

      protected BlockPos getMoveToTarget() {
         return this.blockPos.above(1);
      }

      public boolean canContinueToUse() {
         return !this.taskComplete;
      }

      public double acceptedDistance() {
         return 1.8;
      }

      public boolean isInterruptable() {
         return true;
      }

      public void tick() {
         super.tick();
         if (this.counter == 0 && this.isReachedTarget()) {
            this.taskOwner
               .getLookControl()
               .setLookAt((double)this.blockPos.getX() + 0.5, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5);
            this.taskOwner.getNavigation().stop();
            this.counter = this.taskOwner.getRandom().nextInt(40) + 40;
         }

         if (this.counter > 0) {
            this.counter--;
            this.taskOwner
               .getLookControl()
               .setLookAt((double)this.blockPos.getX() + 0.5, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5);
            if (this.counter == 0) {
               Direction direction = this.taskOwner.getMotionDirection();
               this.taskOwner.setDeltaMovement(this.taskOwner.getDeltaMovement().add((double)direction.getStepX() * -0.1, 0.1, (double)direction.getStepZ() * -0.1));
               this.taskOwner.getNavigation().stop();
               Level worldIn = this.taskOwner.level();
               ((ServerLevel)worldIn)
                  .sendParticles(
                     new BlockParticleOption(ParticleTypes.BLOCK, worldIn.getBlockState(this.blockPos)),
                     (double)this.blockPos.getX() + 0.5,
                     (double)this.blockPos.above().getY(),
                     (double)this.blockPos.getZ() + 0.5,
                     50,
                     0.0,
                     0.0,
                     0.0,
                     0.15F
                  );
               this.taskComplete = true;
            }
         }
      }

      protected boolean isValidTarget(LevelReader p_25619_, BlockPos blockpos) {
         if (this.taskOwner.level().getBlockState(blockpos).is(BlockTags.SAND)
            && this.taskOwner.level().getFluidState(blockpos.above()).is(Fluids.WATER)
            && EntityTriggerfish.this.random.nextInt(2) == 0) {
            Path path = this.mob.getNavigation().createPath(blockpos, 1);
            return path != null && path.canReach();
         } else {
            return false;
         }
      }
   }
}
