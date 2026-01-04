package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import untamedwilds.entity.arthropod.EntityButterfly;
import untamedwilds.util.EntityUtils;

public class ButterflyFlutterTowardsGoal extends ButterflyFlutterGoal {
   private final Predicate<BlockState> VALID_POLLINATION_BLOCKS = input -> {
      if (input.is(BlockTags.FLOWERS)) {
         return input.is(Blocks.SUNFLOWER) ? input.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER : true;
      } else {
         return false;
      }
   };
   private int successfulPollinatingTicks;
   private int lastSoundPlayedTick;
   private boolean pollinating;
   @Nullable
   private Vec3 hoverPos;
   private int pollinatingTicks;
   private BlockPos blockPos;

   public ButterflyFlutterTowardsGoal(EntityButterfly entityIn, float speed) {
      super(entityIn, speed);
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   @Override
   public boolean canUse() {
      if (!this.taskOwner.isResting()
         && this.taskOwner.flight_counter == 0
         && this.taskOwner.getRandom().nextInt(400) == 0
         && !this.taskOwner.level().isRaining()) {
         Optional<BlockPos> optional = this.findNearbyFlower();
         if (optional.isPresent()) {
            this.blockPos = optional.get();
            this.taskOwner
               .getNavigation()
               .moveTo((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5, 1.2F);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean canContinueToUse() {
      if (this.taskOwner.getNavigation().isDone() || this.taskOwner.isNotMoving() || this.taskOwner.isResting()) {
         return false;
      } else if (!this.pollinating) {
         return false;
      } else if (this.taskOwner.level().isRaining()) {
         return false;
      } else if (this.hasPollinatedLongEnough()) {
         return this.taskOwner.getRandom().nextFloat() < 0.2F;
      } else if (this.taskOwner.tickCount % 20 == 0 && !this.isFlowerValid(this.blockPos)) {
         this.blockPos = null;
         return false;
      } else {
         return true;
      }
   }

   private boolean hasPollinatedLongEnough() {
      return this.successfulPollinatingTicks > 400;
   }

   @Override
   public void start() {
      this.successfulPollinatingTicks = 0;
      this.pollinatingTicks = 0;
      this.lastSoundPlayedTick = 0;
      this.pollinating = true;
   }

   public void stop() {
      if (this.hasPollinatedLongEnough()) {
         this.taskOwner.setResting(true);
      }

      this.taskOwner.setResting(true);
      this.pollinating = false;
      EntityUtils.spawnParticlesOnEntity(this.taskOwner.level(), this.taskOwner, ParticleTypes.FALLING_NECTAR, 3, 1);
      this.taskOwner.getNavigation().stop();
   }

   public boolean requiresUpdateEveryTick() {
      return true;
   }

   boolean isFlowerValid(BlockPos p_27897_) {
      return this.taskOwner.level().isLoaded(p_27897_) && this.taskOwner.level().getBlockState(p_27897_).is(BlockTags.FLOWERS);
   }

   @Override
   public void tick() {
      this.pollinatingTicks++;
      if (this.pollinatingTicks > 300) {
         this.blockPos = null;
      } else {
         Vec3 vec3 = this.taskOwner.getPosition(0.0F);
         if (this.blockPos != null) {
            vec3 = Vec3.atBottomCenterOf(this.blockPos).add(0.0, 0.6F, 0.0);
         }

         if (vec3.distanceTo(this.taskOwner.position()) > 1.0) {
            this.hoverPos = vec3;
            this.setWantedPos();
         } else {
            if (this.hoverPos == null) {
               this.hoverPos = vec3;
            }

            boolean flag = this.taskOwner.position().distanceTo(this.hoverPos) <= 0.1;
            boolean flag1 = true;
            if (!flag && this.pollinatingTicks > 600) {
               this.blockPos = null;
            } else {
               if (flag) {
                  boolean flag2 = this.taskOwner.getRandom().nextInt(25) == 0;
                  if (flag2) {
                     this.hoverPos = new Vec3(vec3.x() + (double)this.getOffset(), vec3.y(), vec3.z() + (double)this.getOffset());
                     this.taskOwner.getNavigation().stop();
                  } else {
                     flag1 = false;
                  }

                  this.taskOwner.getLookControl().setLookAt(vec3.x(), vec3.y(), vec3.z());
               }

               if (flag1) {
                  this.setWantedPos();
               }

               this.successfulPollinatingTicks++;
               if (this.taskOwner.getRandom().nextFloat() < 0.05F && this.successfulPollinatingTicks > this.lastSoundPlayedTick + 60) {
                  this.lastSoundPlayedTick = this.successfulPollinatingTicks;
                  this.taskOwner.playSound(SoundEvents.BEE_POLLINATE, 1.0F, 1.0F);
               }
            }
         }
      }
   }

   private void setWantedPos() {
      this.taskOwner.getMoveControl().setWantedPosition(this.hoverPos.x(), this.hoverPos.y(), this.hoverPos.z(), 0.35F);
   }

   private float getOffset() {
      return (this.taskOwner.getRandom().nextFloat() * 2.0F - 1.0F) * 0.33333334F;
   }

   private Optional<BlockPos> findNearbyFlower() {
      return this.findNearestBlock(this.VALID_POLLINATION_BLOCKS, 5.0);
   }

   private Optional<BlockPos> findNearestBlock(Predicate<BlockState> p_28076_, double p_28077_) {
      BlockPos blockpos = this.taskOwner.blockPosition();
      MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

      for (int i = 0; (double)i <= p_28077_; i = i > 0 ? -i : 1 - i) {
         for (int j = 0; (double)j < p_28077_; j++) {
            for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
               for (int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                  blockpos$mutableblockpos.setWithOffset(blockpos, k, i - 1, l);
                  if (blockpos.closerThan(blockpos$mutableblockpos, p_28077_) && p_28076_.test(this.taskOwner.level().getBlockState(blockpos$mutableblockpos))) {
                     return Optional.of(blockpos$mutableblockpos);
                  }
               }
            }
         }
      }

      return Optional.empty();
   }
}
