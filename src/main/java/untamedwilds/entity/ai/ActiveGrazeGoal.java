package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraftforge.event.ForgeEventFactory;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.init.ModTags;

public class ActiveGrazeGoal extends MoveToBlockGoal {
   private final ComplexMobTerrestrial taskOwner;
   public final Level entityWorld;
   public BlockPos eatPos;
   private int eatingGrassTimer = -1;
   private final int executionChance;

   public ActiveGrazeGoal(ComplexMobTerrestrial entityIn, int chance) {
      super(entityIn, 1.0, chance);
      this.taskOwner = entityIn;
      this.entityWorld = entityIn.level();
      this.executionChance = chance;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
   }

   public boolean canUse() {
      if (this.taskOwner.canMove()
         && !this.taskOwner.isBaby()
         && this.taskOwner.getHunger() <= 100
         && this.taskOwner.getTarget() == null
         && this.taskOwner.getRandom().nextInt(this.executionChance) == 0) {
         this.eatPos = this.getEatPos();
         if (this.taskOwner.getHunger() < 40) {
            this.eatingGrassTimer = -1;
            return this.locateGrazeables();
         } else {
            return this.tryStartEating();
         }
      } else {
         return false;
      }
   }

   public void start() {
      super.start();
   }

   public void stop() {
      super.stop();
      this.eatingGrassTimer = 0;
   }

   public boolean canContinueToUse() {
      return !super.isReachedTarget() && this.eatingGrassTimer != 0;
   }

   public void tick() {
      super.tick();
      this.tryStartEating();
      if (this.eatingGrassTimer > 0) {
         this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
         if (this.eatingGrassTimer == 4) {
            if (this.entityWorld.getBlockState(this.eatPos).is(ModTags.ModBlockTags.GRAZEABLE_BLOCKS)) {
               if (ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.taskOwner) && (Boolean)ConfigGamerules.grazerGriefing.get()) {
                  this.entityWorld.destroyBlock(this.eatPos, false);
               }

               this.taskOwner.addHunger(16);
               this.taskOwner.ate();
            } else {
               BlockPos blockpos1 = this.eatPos.below();
               if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS_BLOCK && ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.taskOwner)
                  )
                {
                  this.entityWorld.globalLevelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                  if ((Boolean)ConfigGamerules.grazerGriefing.get()) {
                     this.entityWorld.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
                  }

                  this.taskOwner.addHunger(16);
                  this.taskOwner.ate();
               }
            }
         }
      }
   }

   protected boolean isValidTarget(LevelReader worldIn, BlockPos posIn) {
      if (this.entityWorld.getBlockState(posIn).is(ModTags.ModBlockTags.GRAZEABLE_BLOCKS)
         || this.entityWorld.getBlockState(posIn.below()).getBlock() == Blocks.GRASS_BLOCK && this.taskOwner.getRandom().nextInt(2) == 0) {
         Path path = this.mob.getNavigation().createPath(posIn, 1);
         return path != null && path.canReach();
      } else {
         return false;
      }
   }

   private boolean locateGrazeables() {
      int i = 8;
      int j = 3;
      BlockPos blockpos = this.mob.blockPosition();
      MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

      for (int k = this.verticalSearchStart; k <= j; k = k > 0 ? -k : 1 - k) {
         for (int l = 0; l < i; l++) {
            for (int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
               for (int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                  blockpos$mutableblockpos.setWithOffset(blockpos, i1, k - 1, j1);
                  if (this.mob.isWithinRestriction(blockpos$mutableblockpos) && this.isValidTarget(this.mob.level(), blockpos$mutableblockpos)) {
                     this.blockPos = blockpos$mutableblockpos;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean tryStartEating() {
      if (this.isGrazeable() && this.eatingGrassTimer == -1) {
         this.eatPos = this.getEatPos();
         this.eatingGrassTimer = 40;
         this.entityWorld.broadcastEntityEvent(this.taskOwner, (byte)10);
         this.taskOwner.getNavigation().stop();
         this.taskOwner.setAnimation(this.taskOwner.getAnimationEat());
         return true;
      } else {
         return false;
      }
   }

   public boolean isGrazeable() {
      return this.entityWorld.getBlockState(this.getEatPos()).is(ModTags.ModBlockTags.GRAZEABLE_BLOCKS)
         || this.entityWorld.getBlockState(this.getEatPos().below()).getBlock() == Blocks.GRASS_BLOCK;
   }

   public BlockPos getEatPos() {
      return this.taskOwner
         .blockPosition()
         .offset(
            BlockPos.containing(
               Math.cos(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2,
               0.0,
               Math.sin(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2
            )
         );
   }

   public double acceptedDistance() {
      return 1.8;
   }
}
