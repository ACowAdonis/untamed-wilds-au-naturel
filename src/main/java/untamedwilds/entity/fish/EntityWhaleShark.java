package untamedwilds.entity.fish;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.init.ModEntity;
import untamedwilds.util.EntityUtils;

public class EntityWhaleShark extends ComplexMobAquatic implements ISpecies, INewSkins {
   public final int length;
   public final EntityWhaleShark.EntityWhaleSharkPart[] whale_shark_parts;
   public int ringBufferIndex = -1;
   public final double[][] ringBuffer = new double[64][3];

   public EntityWhaleShark(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.length = this.getMultiparts();
      this.whale_shark_parts = new EntityWhaleShark.EntityWhaleSharkPart[this.length];

      for (int i = 0; i < this.length; i++) {
         this.whale_shark_parts[i] = new EntityWhaleShark.EntityWhaleSharkPart(this, this.getBbWidth(), this.getBbHeight());
      }

      this.turn_speed = 0.1F;
   }

   private void setPartPosition(EntityWhaleShark.EntityWhaleSharkPart part, double offsetX, double offsetY, double offsetZ) {
      part.setPos(
         this.getX() + offsetX * (double)part.scale, this.getY() + offsetY * (double)part.scale, this.getZ() + offsetZ * (double)part.scale
      );
   }

   public boolean isMultipartEntity() {
      return true;
   }

   public PartEntity<?>[] getParts() {
      return this.whale_shark_parts;
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 3.0)
         .add(Attributes.ATTACK_KNOCKBACK, 1.6)
         .add(Attributes.MOVEMENT_SPEED, 0.8)
         .add(Attributes.FOLLOW_RANGE, 12.0)
         .add(Attributes.MAX_HEALTH, 80.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
         .add(Attributes.ARMOR, 6.0);
   }

   @Override
   public void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(3, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(4, new ComplexMobAquatic.SwimGoal(this));
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
         Vec3[] avector3d = new Vec3[this.whale_shark_parts.length];

         for (int j = 0; j < this.whale_shark_parts.length; j++) {
            this.whale_shark_parts[j].collideWithNearbyEntities();
            avector3d[j] = new Vec3(this.whale_shark_parts[j].getX(), this.whale_shark_parts[j].getY(), this.whale_shark_parts[j].getZ());
         }

         float f15 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F * (float) (Math.PI / 180.0);
         float f16 = Mth.cos(f15);
         float yaw = this.getYRot() * (float) (Math.PI / 180.0);
         float pitch = this.getXRot() * (float) (Math.PI / 180.0);
         float f3 = Mth.sin(yaw) * (1.0F - Math.abs(this.getXRot() / 90.0F));
         float f18 = Mth.cos(yaw) * (1.0F - Math.abs(this.getXRot() / 90.0F));
         double[] adouble = this.getMovementOffsets(5, 1.0F);

         for (int k = 0; k < this.getMultiparts(); k++) {
            EntityWhaleShark.EntityWhaleSharkPart whale_shark_part = this.whale_shark_parts[k];
            double[] adouble1 = this.getMovementOffsets(5 + k * 2, 1.0F);
            float f7 = yaw + (float)Mth.wrapDegrees(adouble1[0] - adouble[0]) * (float) (Math.PI / 180.0);
            float f20 = Mth.sin(f7) * (1.0F - Math.abs(this.getXRot() / 90.0F));
            float f21 = Mth.cos(f7) * (1.0F - Math.abs(this.getXRot() / 90.0F));
            float f23 = k == 0 ? (float)(k + 3) : (float)((k + 3) * -1);
            float value = Mth.clamp(pitch * (float)k, -20.0F, 20.0F);
            this.setPartPosition(whale_shark_part, (double)(-(f3 * 0.5F + f20 * f23) * f16), (double)value, (double)((f18 * 0.5F + f21 * f23) * f16));
            this.whale_shark_parts[k].xo = avector3d[k].x;
            this.whale_shark_parts[k].yo = avector3d[k].y;
            this.whale_shark_parts[k].zo = avector3d[k].z;
            this.whale_shark_parts[k].xOld = avector3d[k].x;
            this.whale_shark_parts[k].yOld = avector3d[k].y;
            this.whale_shark_parts[k].zOld = avector3d[k].z;
         }
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
      adouble[2] = Mth.lerp((double)partialTicks, this.ringBuffer[i][2], this.ringBuffer[j][2]);
      return adouble;
   }

   @Override
   public boolean wantsToBreed() {
      if (super.wantsToBreed() && !this.isSleeping() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityWhaleShark> list = this.level().getEntitiesOfClass(EntityWhaleShark.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
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
      return this.create_offspring(new EntityWhaleShark((EntityType<? extends ComplexMob>)ModEntity.WHALE_SHARK.get(), this.level()));
   }

   public boolean attackEntityPartFrom(EntityWhaleShark.EntityWhaleSharkPart whale_shark_part, DamageSource source, float amount) {
      return this.hurt(source, amount);
   }

   public int getMultiparts() {
      return 2;
   }

   @Override
   public boolean canBeTargeted() {
      return false;
   }

   public static class EntityWhaleSharkPart extends PartEntity<EntityWhaleShark> {
      private final EntityDimensions size;
      public float scale = 1.0F;

      public EntityWhaleSharkPart(EntityWhaleShark parent, float sizeX, float sizeY) {
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
                        && (!(entity instanceof EntityWhaleShark.EntityWhaleSharkPart) || ((EntityWhaleShark.EntityWhaleSharkPart)entity).getParent() != parent)
                        && entity.isPushable()
               )
               .forEach(entity -> entity.push(parent));
         }
      }

      public InteractionResult mobInteract(Player player, InteractionHand hand) {
         return this.getParent() == null ? InteractionResult.PASS : ((EntityWhaleShark)this.getParent()).mobInteract(player, hand);
      }

      public void push(Entity entityIn) {
         entityIn.push(this);
      }

      public boolean canBeCollidedWith() {
         return true;
      }

      public boolean hurt(DamageSource source, float amount) {
         return !this.isInvulnerableTo(source) && ((EntityWhaleShark)this.getParent()).attackEntityPartFrom(this, source, amount);
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
