package untamedwilds.entity.arthropod;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.common.ForgeMod;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.INewSkins;
import untamedwilds.entity.ISpecies;
import untamedwilds.entity.ai.SmartAvoidGoal;
import untamedwilds.entity.ai.SmartMateGoal;
import untamedwilds.util.EntityUtils;

public class EntityKingCrab extends ComplexMobAmphibious implements ISpecies, INewSkins, IAnimatedEntity {
   private int animationTick;
   private Animation currentAnimation;
   public static Animation EAT_LEFT;
   public static Animation EAT_RIGHT;
   public static Animation EAT_BOTH;

   public EntityKingCrab(EntityType<? extends EntityKingCrab> type, Level worldIn) {
      super(type, worldIn);
      EAT_LEFT = Animation.create(56);
      EAT_RIGHT = Animation.create(56);
      EAT_BOTH = Animation.create(80);
   }

   public MobType getMobType() {
      return MobType.ARTHROPOD;
   }

   public static Builder registerAttributes() {
      return LivingEntity.createLivingAttributes()
         .add(Attributes.ATTACK_DAMAGE, 3.0)
         .add(Attributes.ATTACK_KNOCKBACK, 0.0)
         .add(Attributes.MOVEMENT_SPEED, 0.4)
         .add(Attributes.FOLLOW_RANGE, 16.0)
         .add(Attributes.MAX_HEALTH, 8.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 0.0)
         .add(Attributes.ARMOR, 4.0)
         .add((Attribute)ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3, false));
      this.goalSelector.addGoal(2, new SmartMateGoal(this, 1.0));
      this.goalSelector.addGoal(2, new SmartAvoidGoal<LivingEntity>(this, LivingEntity.class, 16.0F, 1.2, 1.6, input -> getEcoLevel(input) > getEcoLevel(this)));
      this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0, 240));
      this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));
   }

   public float getWalkTargetValue(BlockPos p_149140_, LevelReader p_149141_) {
      return 0.0F;
   }

   @Override
   public boolean wantsToBeOnLand() {
      return this.level().isRainingAt(this.blockPosition());
   }

   @Override
   public boolean wantsToBeInWater() {
      return true;
   }

   public boolean isPushedByFluid() {
      return false;
   }

   @Override
   public void aiStep() {
      super.aiStep();
      AnimationHandler.INSTANCE.updateAnimations(this);
      if (!this.level().isClientSide) {
         if (this.tickCount % 1000 == 0 && this.wantsToBreed() && !this.isMale()) {
            this.breed();
         }

         if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
         }

         if (this.level().getGameTime() % 4000L == 0L) {
            this.heal(1.0F);
         }

         if (this.isInWater()
            && this.getAnimation() == NO_ANIMATION
            && this.getTarget() == null
            && this.level().getBlockState(this.blockPosition().below()).is(BlockTags.MINEABLE_WITH_SHOVEL)
            && this.getCommandInt() == 0) {
            int i = this.random.nextInt(3000);
            if (i > 2940 && i < 2960) {
               this.setAnimation(EAT_RIGHT);
            }

            if (i > 2960 && i < 2980) {
               this.setAnimation(EAT_LEFT);
            }

            if (i > 2980) {
               this.setAnimation(EAT_BOTH);
            }
         }

         if (this.getAnimation() != NO_ANIMATION
            && (
               (this.getAnimation() == EAT_LEFT || this.getAnimation() == EAT_RIGHT) && this.getAnimationTick() == 20
                  || this.getAnimation() == EAT_BOTH && (this.getAnimationTick() == 20 || this.getAnimationTick() == 44)
            )) {
            ((ServerLevel)this.level())
               .sendParticles(
                  new BlockParticleOption(ParticleTypes.FALLING_DUST, this.level().getBlockState(this.blockPosition().below())),
                  this.getX(),
                  this.getY(),
                  this.getZ(),
                  3,
                  0.0,
                  0.0,
                  0.0,
                  0.15F
               );
            this.playSound(SoundEvents.SHOVEL_FLATTEN, 0.2F, 0.7F);
         }
      }

      if (this.level().isClientSide()) {
      }
   }

   @Override
   public boolean wantsToBreed() {
      if ((Boolean)ConfigGamerules.naturalBreeding.get() && this.isInWater() && this.getAge() == 0 && EntityUtils.hasFullHealth(this)) {
         List<EntityKingCrab> list = this.level().getEntitiesOfClass(EntityKingCrab.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
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
      EntityUtils.dropEggs(this, "egg_king_crab", this.getOffspring());
      return null;
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
      if (hand == InteractionHand.MAIN_HAND && itemstack.getItem().equals(Items.WATER_BUCKET) && this.isAlive()) {
         EntityUtils.mutateEntityIntoItem(this, player, hand, "bucket_king_crab", itemstack);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public int getAnimationTick() {
      return this.animationTick;
   }

   @Override
   public void setAnimationTick(int tick) {
      this.animationTick = tick;
   }

   @Override
   public Animation getAnimation() {
      return this.currentAnimation;
   }

   @Override
   public void setAnimation(Animation animation) {
      this.currentAnimation = animation;
   }

   @Override
   public Animation[] getAnimations() {
      return new Animation[]{NO_ANIMATION, EAT_RIGHT, EAT_LEFT, EAT_BOTH};
   }
}
