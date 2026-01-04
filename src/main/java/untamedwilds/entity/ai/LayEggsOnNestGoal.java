package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import untamedwilds.block.blockentity.ReptileNestBlockEntity;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.INestingMob;

public class LayEggsOnNestGoal extends MoveToBlockGoal {
   private final ComplexMob taskOwner;
   private final Level world;
   private boolean hasReachedDestination;
   private boolean needsToBuildNest = false;
   private int nestBuildingTicks;

   public LayEggsOnNestGoal(ComplexMob entityIn) {
      super(entityIn, 1.0, 16, 4);
      this.taskOwner = entityIn;
      this.world = entityIn.level();
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
   }

   public boolean canUse() {
      if (!(this.taskOwner instanceof INestingMob) || !((INestingMob)this.taskOwner).wantsToLayEggs()) {
         return false;
      } else if (this.nextStartTick > 0) {
         this.nextStartTick--;
         return false;
      } else {
         this.needsToBuildNest = false;
         this.nextStartTick = this.nextStartTick(this.mob);
         if (this.findNearestBlock()) {
            return true;
         } else {
            this.needsToBuildNest = true;
            return this.checkForNewNest();
         }
      }
   }

   public boolean canContinueToUse() {
      return ((INestingMob)this.taskOwner).wantsToLayEggs() && super.canContinueToUse();
   }

   public double acceptedDistance() {
      return 1.0;
   }

   public void tick() {
      super.tick();
      BlockPos blockpos = this.getMoveToTarget();
      if (!this.isWithinXZDist(blockpos, this.mob.position(), this.acceptedDistance())) {
         this.hasReachedDestination = false;
         this.tryTicks++;
         if (this.shouldRecalculatePath()) {
            this.mob
               .getNavigation()
               .moveTo((double)((float)blockpos.getX()) + 0.5, (double)blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5, this.speedModifier);
         }
      } else {
         this.hasReachedDestination = true;
         this.tryTicks--;
      }

      if (this.isReachedTarget()) {
         if (this.needsToBuildNest) {
            this.nestBuildingTicks--;
            if (this.nestBuildingTicks % 30 == 0) {
               ((ServerLevel)this.taskOwner.level())
                  .sendParticles(
                     new BlockParticleOption(ParticleTypes.BLOCK, this.taskOwner.level().getBlockState(this.blockPos.below())),
                     this.taskOwner.getX(),
                     this.taskOwner.getY(),
                     this.taskOwner.getZ(),
                     20,
                     0.0,
                     0.0,
                     0.0,
                     0.15F
                  );
               this.taskOwner.playSound(SoundEvents.SHOVEL_FLATTEN, 0.8F, 0.6F);
            }

            if (this.nestBuildingTicks <= 0) {
               this.world.setBlock(this.blockPos, ((INestingMob)this.taskOwner).getNestType().defaultBlockState(), 2);
               ReptileNestBlockEntity te = (ReptileNestBlockEntity)this.world.getBlockEntity(this.blockPos);
               if (te != null) {
                  te.setEntityType(this.taskOwner.getType());
                  te.setVariant(this.taskOwner.getVariant());
                  te.setEggCount(0);
               }

               this.needsToBuildNest = false;
            }
         } else {
            this.addEggsToNest();
            this.stop();
         }
      }
   }

   public boolean checkForNewNest() {
      RandomSource random = this.taskOwner.getRandom();
      BlockPos blockpos = this.mob.blockPosition();
      MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

      for (int i = 0; i < 10; i++) {
         blockpos$mutableblockpos.setWithOffset(blockpos, random.nextInt(8) - 4, random.nextInt(4) - 2, random.nextInt(8) - 4);
         if (((INestingMob)this.taskOwner).isValidNestBlock(blockpos$mutableblockpos) && this.isValidTarget(this.mob.level(), blockpos$mutableblockpos)) {
            this.nestBuildingTicks = 400 + this.taskOwner.getRandom().nextInt(300);
            this.blockPos = blockpos$mutableblockpos;
            return true;
         }
      }

      return false;
   }

   private void addEggsToNest() {
      BlockState blockstate = this.taskOwner.level().getBlockState(this.blockPos);
      if (blockstate.is(((INestingMob)this.taskOwner).getNestType())
         && this.taskOwner.level().getBlockEntity(this.blockPos) instanceof ReptileNestBlockEntity nest) {
         if (((INestingMob)this.taskOwner).wantsToLayEggs()) {
            nest.setEggCount(nest.getEggCount() + this.taskOwner.getOffspring());
         }

         this.taskOwner.level().updateNeighbourForOutputSignal(this.blockPos, blockstate.getBlock());
         ((INestingMob)this.taskOwner).setEggStatus(false);
      }
   }

   private boolean isWithinXZDist(BlockPos blockpos, Vec3 positionVec, double distance) {
      return blockpos.distSqr(BlockPos.containing(positionVec.x(), (double)blockpos.getY(), positionVec.z())) < distance * distance;
   }

   protected boolean isReachedTarget() {
      return this.hasReachedDestination;
   }

   protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
      return this.needsToBuildNest
         ? ((INestingMob)this.taskOwner).isValidNestBlock(pos)
         : worldIn.getBlockState(pos).is(((INestingMob)this.taskOwner).getNestType())
            && worldIn.getBlockEntity(pos) instanceof ReptileNestBlockEntity
            && ((ReptileNestBlockEntity)worldIn.getBlockEntity(pos)).getVariant() == this.taskOwner.getVariant();
   }
}
