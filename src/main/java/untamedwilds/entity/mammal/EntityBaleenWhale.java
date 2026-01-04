package untamedwilds.entity.mammal;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INeedsPostUpdate;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.entity.ai.unique.BaleenWhaleFeedGoal;
import untamedwilds.entity.ai.unique.WhaleBreachGoal;
import untamedwilds.entity.ai.unique.WhaleSwimmingGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.util.EntityUtils;

public class EntityBaleenWhale extends ComplexMobAquatic implements ISpecies, INewSkins, INeedsPostUpdate {
   private static final EntityDataAccessor<Boolean> IS_EATING = SynchedEntityData.defineId(EntityBaleenWhale.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Boolean> LONG_FINS = SynchedEntityData.defineId(EntityBaleenWhale.class, EntityDataSerializers.BOOLEAN);
   public int length;
   public EntityBaleenWhale.EntityWhalePart[] whale_parts;
   public int ringBufferIndex = -1;
   public final double[][] ringBuffer = new double[64][3];
   public int gulpProgress;

   public EntityBaleenWhale(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.entityData.define(LONG_FINS, false);
      this.length = this.getMultiparts();
      this.whale_parts = new EntityBaleenWhale.EntityWhalePart[this.length];

      for (int i = 0; i < this.length; i++) {
         this.whale_parts[i] = new EntityBaleenWhale.EntityWhalePart(this, this.getBbWidth(), this.getBbHeight());
      }

      this.turn_speed = 0.03F;
   }

   @Override
   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(IS_EATING, false);
   }

   private void setPartPosition(EntityBaleenWhale.EntityWhalePart part, double offsetX, double offsetY, double offsetZ) {
      part.setPos(
         this.getX() + offsetX * (double)part.scale, this.getY() + offsetY * (double)part.scale, this.getZ() + offsetZ * (double)part.scale
      );
   }

   public boolean isMultipartEntity() {
      return true;
   }

   public PartEntity<?>[] getParts() {
      return this.whale_parts;
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 3.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.6)
         .add(Attributes.MOVEMENT_SPEED, 0.8)
         .add(Attributes.FOLLOW_RANGE, 12.0)
         .add(Attributes.MAX_HEALTH, 100.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.ARMOR, 6.0);
   }

   @Override
   public void registerGoals() {
      this.goalSelector.addGoal(0, new BreathAirGoal(this));
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(4, new WhaleSwimmingGoal(this));
      this.goalSelector.addGoal(5, new WhaleBreachGoal(this, 10));
      this.goalSelector.addGoal(5, new BaleenWhaleFeedGoal(this, 400));
      this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));
   }

   @Override
   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide && this.level().getGameTime() % 4000L == 0L) {
         this.heal(1.0F);
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
         Vec3[] avector3d = new Vec3[this.whale_parts.length];

         for (int j = 0; j < this.whale_parts.length; j++) {
            this.whale_parts[j].collideWithNearbyEntities();
            avector3d[j] = new Vec3(this.whale_parts[j].getX(), this.whale_parts[j].getY(), this.whale_parts[j].getZ());
         }

         float f15 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F * (float) (Math.PI / 180.0);
         float f16 = Mth.cos(f15);
         float yaw = this.getYRot() * (float) (Math.PI / 180.0);
         float pitch = this.getXRot() * (float) (Math.PI / 180.0);
         float f3 = Mth.sin(yaw) * (1.0F - Math.abs(this.getXRot() / 90.0F));
         float f18 = Mth.cos(yaw) * (1.0F - Math.abs(this.getXRot() / 90.0F));
         double[] adouble = this.getMovementOffsets(5, 1.0F);
         float var = 1.0F;

         for (int k = 0; k < this.getMultiparts(); k++) {
            EntityBaleenWhale.EntityWhalePart whale_part = this.whale_parts[k];
            double[] adouble1 = this.getMovementOffsets(5 + k * 2, 1.0F);
            float f7 = yaw + (float)Mth.wrapDegrees(adouble1[0] - adouble[0]) * (float) (Math.PI / 180.0);
            float f20 = Mth.sin(f7) * (1.0F - Math.abs(this.getXRot() / 90.0F));
            float f21 = Mth.cos(f7) * (1.0F - Math.abs(this.getXRot() / 90.0F));
            float offset = k % 2 == 0 ? -1.0F : 1.0F;
            if (k % 2 == 0) {
               var++;
            }

            float f23 = var * 1.5F * offset;
            float value = Mth.clamp(pitch * (float)k, (float)Math.toRadians(-40.0), (float)Math.toRadians(40.0));
            this.setPartPosition(
               whale_part,
               -((double)f3 * 0.5 + (double)(f20 * f23)) * (double)f16,
               (double)(value * -offset),
               ((double)f18 * 0.5 + (double)(f21 * f23)) * (double)f16
            );
            this.whale_parts[k].xo = avector3d[k].x;
            this.whale_parts[k].yo = avector3d[k].y;
            this.whale_parts[k].zo = avector3d[k].z;
            this.whale_parts[k].xOld = avector3d[k].x;
            this.whale_parts[k].yOld = avector3d[k].y;
            this.whale_parts[k].zOld = avector3d[k].z;
         }
      }

      if (this.level().isClientSide && this.isFeeding() && this.gulpProgress < 50) {
         this.gulpProgress++;
      } else if (this.level().isClientSide && !this.isFeeding() && this.gulpProgress > 0) {
         this.gulpProgress--;
      }
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.GUARDIAN_FLOP;
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
      adouble[2] = Mth.lerp(0.5, this.ringBuffer[i][2], this.ringBuffer[j][2]);
      return adouble;
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityBaleenWhale> list = this.level().getEntitiesOfClass(EntityBaleenWhale.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
         list.removeIf(input -> EntityUtils.isInvalidPartner(this, input, false));
         if (list.size() >= 1) {
            this.setAge(this.getPregnancyTime());
            list.get(0).setAge(this.getPregnancyTime());
            return true;
         }
      }

      return false;
   }

   public boolean hasLongFins() {
      return (Boolean)this.entityData.get(LONG_FINS);
   }

   private void setLongFins(boolean long_fins) {
      this.entityData.set(LONG_FINS, long_fins);
   }

   @Override
   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putBoolean("hasLongFins", this.hasLongFins());
   }

   @Override
   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      this.setLongFins(compound.getBoolean("hasLongFins"));
   }

   @Override
   public void updateAttributes() {
      this.setHealth(this.getMaxHealth());
      this.length = this.getMultiparts();
      this.whale_parts = new EntityBaleenWhale.EntityWhalePart[this.length];

      for (int i = 0; i < this.length; i++) {
         this.whale_parts[i] = new EntityBaleenWhale.EntityWhalePart(this, this.getBbWidth(), this.getBbHeight());
      }

      this.setLongFins(getEntityData(this.getType()).getFlags(this.getVariant(), "hasLongFins") == 1);
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
      return this.create_offspring(new EntityBaleenWhale((EntityType<? extends ComplexMob>)ModEntity.BALEEN_WHALE.get(), this.level()));
   }

   public boolean attackEntityPartFrom(EntityBaleenWhale.EntityWhalePart whale_part, DamageSource source, float amount) {
      return this.hurt(source, amount);
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide() && itemstack.getItem() == Items.BLAZE_ROD) {
         this.setFeeding(!this.isFeeding());
      }

      return super.mobInteract(player, hand);
   }

   public int getMultiparts() {
      return 3 + (int)((this.getModelScale() - 1.0F) * 4.0F);
   }

   @Override
   public boolean canBeTargeted() {
      return false;
   }

   public boolean isFeeding() {
      return (Boolean)this.entityData.get(IS_EATING);
   }

   public void setFeeding(boolean bool) {
      this.entityData.set(IS_EATING, bool);
   }

   public static class EntityWhalePart extends PartEntity<EntityBaleenWhale> {
      private final EntityDimensions size;
      public float scale = 1.0F;

      public EntityWhalePart(EntityBaleenWhale parent, float sizeX, float sizeY) {
         super(parent);
         this.size = EntityDimensions.scalable(sizeX, sizeY);
         this.refreshDimensions();
      }

      protected void collideWithNearbyEntities() {
         List<Entity> entities = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, 0.0, 0.2F));
         Entity parent = this.getParent();
         if (parent != null) {
            entities.stream()
               .filter(
                  entity -> entity != parent
                        && (!(entity instanceof EntityBaleenWhale.EntityWhalePart) || ((EntityBaleenWhale.EntityWhalePart)entity).getParent() != parent)
                        && entity.isPushable()
               )
               .forEach(entity -> entity.push(parent));
         }
      }

      public InteractionResult mobInteract(Player player, InteractionHand hand) {
         return this.getParent() == null ? InteractionResult.PASS : ((EntityBaleenWhale)this.getParent()).mobInteract(player, hand);
      }

      public void push(Entity entityIn) {
         entityIn.push(this);
      }

      public boolean canBeCollidedWith() {
         return true;
      }

      public boolean hurt(DamageSource source, float amount) {
         return !this.isInvulnerableTo(source) && ((EntityBaleenWhale)this.getParent()).attackEntityPartFrom(this, source, amount);
      }

      protected void defineSynchedData() {
      }

      protected void readAdditionalSaveData(CompoundTag compound) {
      }

      protected void addAdditionalSaveData(CompoundTag compound) {
      }

      public boolean is(Entity entityIn) {
         return this == entityIn || this.getParent() == entityIn;
      }

      public Packet<ClientGamePacketListener> getAddEntityPacket() {
         throw new UnsupportedOperationException();
      }

      public EntityDimensions getSize(Pose poseIn) {
         return this.size.scale(this.scale);
      }
   }
}
