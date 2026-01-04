package untamedwilds.entity.ai;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.ForgeEventFactory;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.entity.ComplexMobTerrestrial;
import untamedwilds.init.ModTags;

public class GrazeGoal extends Goal {
   public final ComplexMobTerrestrial taskOwner;
   public final Level entityWorld;
   public BlockPos testpos;
   private int eatingGrassTimer;
   private final int executionChance;

   public GrazeGoal(ComplexMobTerrestrial entityIn, int chance) {
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
         this.testpos = this.taskOwner
            .blockPosition()
            .offset(
               BlockPos.containing(
                  Math.cos(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2,
                  0.0,
                  Math.sin(Math.toRadians((double)(this.taskOwner.getYRot() + 90.0F))) * 1.2
               )
            );
         return this.isGrazeable();
      } else {
         return false;
      }
   }

   public void start() {
      this.eatingGrassTimer = 40;
      this.entityWorld.broadcastEntityEvent(this.taskOwner, (byte)10);
      this.taskOwner.getNavigation().stop();
      this.taskOwner.setAnimation(this.taskOwner.getAnimationEat());
   }

   public void stop() {
      this.eatingGrassTimer = 0;
   }

   public boolean canContinueToUse() {
      return this.eatingGrassTimer > 0;
   }

   public void tick() {
      this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
      if (this.eatingGrassTimer == 4) {
         if (this.entityWorld.getBlockState(this.testpos).is(ModTags.ModBlockTags.GRAZEABLE_BLOCKS)) {
            if (ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.taskOwner) && (Boolean)ConfigGamerules.grazerGriefing.get()) {
               this.entityWorld.destroyBlock(this.testpos, false);
            }

            this.taskOwner.addHunger(16);
            this.taskOwner.ate();
         } else {
            BlockPos blockpos1 = this.testpos.below();
            if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS_BLOCK) {
               if (ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.taskOwner)) {
                  this.entityWorld.globalLevelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                  if ((Boolean)ConfigGamerules.grazerGriefing.get()) {
                     this.entityWorld.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
                  }
               }

               this.taskOwner.addHunger(16);
               this.taskOwner.ate();
            }
         }
      }
   }

   public boolean isGrazeable() {
      return this.entityWorld.getBlockState(this.testpos).is(ModTags.ModBlockTags.GRAZEABLE_BLOCKS)
         || this.entityWorld.getBlockState(this.testpos.below()).getBlock() == Blocks.GRASS_BLOCK;
   }
}
