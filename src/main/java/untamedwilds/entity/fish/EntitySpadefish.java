package untamedwilds.entity.fish;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.IPackEntity;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.FishReturnToSchoolGoal;
import untamedwilds.entity.ai.FishWanderAsSchoolGoal;
import untamedwilds.util.EntityUtils;

public class EntitySpadefish extends ComplexMobAquatic implements ISpecies, IPackEntity, INewSkins {
   public EntitySpadefish(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 1.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.75)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 8.0);
   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
      this.goalSelector.addGoal(4, new FishWanderAsSchoolGoal(this));
      this.goalSelector.addGoal(4, new FishReturnToSchoolGoal(this));
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         if (this.herd == null) {
            IPackEntity.initPack(this);
         } else if (this.herd.getLeader().equals(this)) {
            this.herd.tick();
         }

         if (this.tickCount % 1000 == 0 && this.wantsToBreed() && !this.isMale()) {
            this.breed();
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }
      }

      super.aiStep();
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_spadefish", itemstack);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntitySpadefish> list = this.level().getEntitiesOfClass(EntitySpadefish.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
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
      EntityUtils.dropEggs(this, "egg_spadefish", this.getOffspring());
      return null;
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.COD_FLOP;
   }

   @Override
   public boolean shouldLeavePack() {
      return this.random.nextInt(1000) == 0;
   }
}
