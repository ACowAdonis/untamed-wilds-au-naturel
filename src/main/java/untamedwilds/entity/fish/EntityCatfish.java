package untamedwilds.entity.fish;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
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
import oshi.util.tuples.Pair;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ComplexMobAquatic;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.unique.CatfishGarbageBinGoal;
import untamedwilds.util.EntityUtils;

public class EntityCatfish extends ComplexMobAquatic implements ISpecies, INewSkins {
   public Pair<Float, Float> whisker_offset;
   private float whisker_y = 0.0F;
   private float whisker_z = 0.0F;

   public EntityCatfish(EntityType<? extends ComplexMob> type, Level worldIn) {
      super(type, worldIn);
      this.whisker_offset = new Pair(0.0F, 0.0F);
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 1.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.3)
         .add(Attributes.MOVEMENT_SPEED, 0.65)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 8.0);
   }

   @Override
   protected void registerGoals() {
      this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
      this.goalSelector.addGoal(2, new CatfishGarbageBinGoal(this, 6, 100));
      this.goalSelector.addGoal(4, new ComplexMobAquatic.SwimGoal(this, 3));
   }

   @Override
   public void aiStep() {
      if (!this.level().isClientSide) {
         if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.003, 0.0));
         }

         if (this.tickCount % 1000 == 0 && this.wantsToBreed() && !this.isMale()) {
            this.breed();
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }
      }

      if (this.tickCount % 120 < 11) {
         if (this.tickCount % 120 == 1) {
            this.whisker_y = 0.8F - this.random.nextFloat() * 1.6F;
            this.whisker_z = 0.4F - this.random.nextFloat() * 0.8F;
         }

         this.whisker_offset = new Pair(
            Mth.lerp(0.1F, (Float)this.whisker_offset.getA(), this.whisker_y), Mth.lerp(0.1F, (Float)this.whisker_offset.getB(), this.whisker_z)
         );
      }

      super.aiStep();
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_catfish", itemstack);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityCatfish> list = this.level().getEntitiesOfClass(EntityCatfish.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
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
      EntityUtils.dropEggs(this, "egg_catfish", this.getOffspring());
      return null;
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundEvents.COD_FLOP;
   }
}
