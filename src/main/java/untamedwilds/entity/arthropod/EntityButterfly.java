package untamedwilds.entity.arthropod;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.unique.ButterflyFlutterGoal;
import untamedwilds.entity.ai.unique.ButterflyFlutterTowardsGoal;
import untamedwilds.util.EntityUtils;

public class EntityButterfly extends ComplexMob implements FlyingAnimal, ISpecies, INewSkins, INeedsPostUpdate {
   private static final EntityDataAccessor<Boolean> DIMORPHISM = SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Boolean> RESTING = SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.BOOLEAN);
   public int flight_counter = 0;

   public EntityButterfly(EntityType<? extends EntityButterfly> type, Level worldIn) {
      super(type, worldIn);
      this.moveControl = new FlyingMoveControl(this, 20, true);
      this.entityData.define(DIMORPHISM, false);
      this.entityData.define(RESTING, false);
   }

   public MobType getMobType() {
      return MobType.ARTHROPOD;
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 1.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.2)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 1.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 0.0)
         .add(Attributes.FLYING_SPEED, 2.0);
   }

   protected PathNavigation createNavigation(Level p_27815_) {
      FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
         public boolean isStableDestination(BlockPos p_27947_) {
            return !this.level.getBlockState(p_27947_.below()).isAir();
         }
      };
      flyingpathnavigation.setCanOpenDoors(false);
      flyingpathnavigation.setCanFloat(false);
      flyingpathnavigation.setCanPassDoors(true);
      return flyingpathnavigation;
   }

   public void registerGoals() {
      this.goalSelector.addGoal(0, new ButterflyFlutterTowardsGoal(this, 1.0F));
      this.goalSelector.addGoal(1, new ButterflyFlutterGoal(this, 1.0F));
   }

   public void tick() {
      super.tick();
      if (this.isResting() && !this.level().isClientSide()) {
         this.setDeltaMovement(Vec3.ZERO);
         boolean flag = this.isSilent();
         if (this.level().getBlockState(this.blockPosition()).isAir()) {
            this.setResting(false);
            if (!flag) {
               this.level().levelEvent(null, 1025, this.blockPosition(), 0);
            }
         } else if (this.random.nextInt(800) == 0) {
            this.setResting(false);
            this.flight_counter = 200;
         }
      } else if (this.flight_counter > 0) {
         this.flight_counter--;
      }
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
      }
   }

   public boolean isPushable() {
      return false;
   }

   protected void doPush(Entity p_27415_) {
   }

   protected void pushEntities() {
   }

   public boolean onClimbable() {
      return this.isResting();
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityButterfly> list = this.level().getEntitiesOfClass(EntityButterfly.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
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
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (itemstack.getItem() == Items.GLASS_BOTTLE && this.isAlive()) {
         EntityUtils.turnEntityIntoItem(this, "bottle_butterfly");
         itemstack.shrink(1);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   public boolean causeFallDamage(float p_148702_, float p_148703_, DamageSource p_148704_) {
      return false;
   }

   protected void checkFallDamage(double p_27419_, boolean p_27420_, BlockState p_27421_, BlockPos p_27422_) {
   }

   @Override
   public void updateAttributes() {
      this.setDimorphism(getEntityData(this.getType()).getFlags(this.getVariant(), "dimorphism") == 1);
   }

   public boolean hasDimorphism() {
      return (Boolean)this.entityData.get(DIMORPHISM);
   }

   private void setDimorphism(boolean dimorphism) {
      this.entityData.set(DIMORPHISM, dimorphism);
   }

   public boolean isResting() {
      return (Boolean)this.entityData.get(RESTING);
   }

   public void setResting(boolean resting) {
      this.entityData.set(RESTING, resting);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("hasDimorphism", this.hasDimorphism());
      compound.putBoolean("resting", this.isResting());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setDimorphism(compound.getBoolean("hasDimorphism"));
      this.setResting(compound.getBoolean("resting"));
   }

   @Override
   public ResourceLocation getTexture() {
      ResourceLocation texture_path = EntityUtils.getSkinFromEntity(this);
      if (this.hasDimorphism()) {
         String trimmed_path = texture_path.getPath().substring(0, texture_path.getPath().lastIndexOf(46));
         return new ResourceLocation("untamedwilds", trimmed_path + "_" + this.getGenderString() + ".png");
      } else {
         return texture_path;
      }
   }

   public boolean isFlying() {
      return !this.isResting();
   }
}
