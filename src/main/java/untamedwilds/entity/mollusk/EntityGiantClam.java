package untamedwilds.entity.mollusk;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.util.EntityUtils;

public class EntityGiantClam extends ComplexMob implements ISpecies, INewSkins {
   private static final EntityDataAccessor<Boolean> CLAM_OPEN = SynchedEntityData.defineId(EntityGiantClam.class, EntityDataSerializers.BOOLEAN);
   public int closeProgress;

   public EntityGiantClam(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.entityData.define(CLAM_OPEN, false);
      this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.0)
         .add(Attributes.FOLLOW_RANGE, 1.0)
         .add(Attributes.MAX_HEALTH, 20.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.ARMOR, 12.0);
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
   }

   public void baseTick() {
      int i = this.getAirSupply();
      super.baseTick();
      if (this.isAlive() && !this.isInWaterOrBubble()) {
         this.setAirSupply(--i);
         if (this.getAirSupply() == -20) {
            this.setAirSupply(0);
            this.hurt(this.damageSources().dryOut(), 2.0F);
         }
      } else {
         this.setAirSupply(300);
      }
   }

   @Override
   public void aiStep() {
      super.aiStep();
      this.setDeltaMovement(0.0, this.getDeltaMovement().get(Axis.Y), 0.0);
      if (!this.level().isClientSide) {
         if (this.isInWater() && this.isOpen() && (double)this.getRandom().nextFloat() > 0.99) {
            ((ServerLevel)this.level())
               .sendParticles(
                  ParticleTypes.BUBBLE_COLUMN_UP,
                  this.getPosition(0.0F).x,
                  this.getPosition(0.0F).y + 0.2,
                  this.getPosition(0.0F).z,
                  1,
                  (double)this.random.nextFloat() * 0.2,
                  (double)this.random.nextFloat() * 0.2,
                  (double)this.random.nextFloat() * 0.2,
                  0.0
               );
         }

         if (this.tickCount % 1000 == 0 && this.wantsToBreed()) {
            this.breed();
         }

         this.setOpen(this.level().isDay());
      }

      if (this.level().isClientSide) {
         if (!this.isOpen() && this.closeProgress < 200) {
            this.closeProgress++;
         } else if (this.isOpen() && this.closeProgress > 0) {
            this.closeProgress--;
         }
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityGiantClam> list = this.level().getEntitiesOfClass(EntityGiantClam.class, this.getBoundingBox().inflate(12.0, 6.0, 12.0));
         list.removeIf(input -> input == this || input.getAge() != 0 || input.getVariant() != this.getVariant());
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
      EntityUtils.dropEggs(this, "egg_giant_clam", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (itemstack.getItem() instanceof ShovelItem && this.isAlive() && hand == InteractionHand.MAIN_HAND) {
         if (this.random.nextInt(4) == 0) {
            this.level().playSound(null, this.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.BLOCKS, 1.0F, 0.8F);
            EntityUtils.turnEntityIntoItem(this, "spawn_giant_clam");
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }

         this.level().playSound(null, this.blockPosition(), SoundEvents.SHULKER_HURT_CLOSED, SoundSource.BLOCKS, 1.0F, 0.8F);
         EntityUtils.spawnParticlesOnEntity(this.level(), this, ParticleTypes.SMOKE, 3, 1);
      }

      return super.mobInteract(player, hand);
   }

   @Override
   public boolean canBeTargeted() {
      return false;
   }

   private boolean isOpen() {
      return (Boolean)this.entityData.get(CLAM_OPEN);
   }

   private void setOpen(boolean open) {
      this.entityData.set(CLAM_OPEN, open);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("isOpen", this.isOpen());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setOpen(compound.getBoolean("isOpen"));
   }
}
