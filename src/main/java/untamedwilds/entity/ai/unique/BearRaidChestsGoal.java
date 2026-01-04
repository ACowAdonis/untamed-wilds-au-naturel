package untamedwilds.entity.ai.unique;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import untamedwilds.entity.mammal.EntityBear;
import untamedwilds.util.EntityUtils;

public class BearRaidChestsGoal extends MoveToBlockGoal {
   private Container targetInventory;
   private final EntityBear taskOwner;
   private final int executionChance;
   private int searchCooldown;

   public BearRaidChestsGoal(EntityBear entityIn, int chance) {
      super(entityIn, 1.0, 10, 3);
      this.taskOwner = entityIn;
      this.executionChance = chance;
      this.searchCooldown = 100;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      return !this.taskOwner.isTame()
            && this.taskOwner.onGround()
            && this.taskOwner.getHunger() <= 60
            && this.taskOwner.getRandom().nextInt(this.executionChance) == 0
            && this.taskOwner.getTarget() == null
         ? super.canUse()
         : false;
   }

   public void start() {
      this.taskOwner
         .getNavigation()
         .moveTo((double)this.blockPos.getX() + 0.5, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5, 1.0);
      super.start();
   }

   public void stop() {
      this.taskOwner.setSitting(false);
      super.stop();
   }

   public void tick() {
      if (this.taskOwner.distanceToSqr((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ()) < 4.0) {
         this.taskOwner
            .getLookControl()
            .setLookAt(
               (double)this.blockPos.getX(),
               (double)((float)this.blockPos.getY() + 1.5F),
               (double)this.blockPos.getZ(),
               10.0F,
               (float)this.taskOwner.getMaxHeadXRot()
            );
         this.taskOwner.getNavigation().stop();
         this.taskOwner.setSitting(true);
         this.searchCooldown--;
         if (this.taskOwner.level().getBlockEntity(this.blockPos) instanceof ChestBlockEntity chest) {
            this.taskOwner.level().blockEvent(this.blockPos, chest.getBlockState().getBlock(), 1, 1);
         }

         if (this.searchCooldown == 0) {
            this.searchCooldown = 100;
            this.stealItem();
         }
      }

      super.tick();
   }

   protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
      if (worldIn.getBlockEntity(pos) != null && getInventoryAtPosition(worldIn, pos) != null && !isInventoryEmpty(getInventoryAtPosition(worldIn, pos), Direction.UP)
         )
       {
         this.targetInventory = getInventoryAtPosition(worldIn, pos);
         this.blockPos = pos;
         return true;
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      if (this.taskOwner.getHunger() < 60 && !this.targetInventory.isEmpty() && this.tryTicks <= 1200) {
         return true;
      } else {
         if (this.taskOwner.level().getBlockEntity(this.blockPos) instanceof ChestBlockEntity chest) {
            this.taskOwner.level().blockEvent(this.blockPos, chest.getBlockState().getBlock(), 1, 0);
         }

         return false;
      }
   }

   private boolean stealItem() {
      if (this.targetInventory != null) {
         Direction enumfacing = Direction.DOWN;
         if (isInventoryEmpty(this.targetInventory, enumfacing)) {
            return false;
         }

         if (this.targetInventory instanceof WorldlyContainer isidedinventory) {
            int[] aint = isidedinventory.getSlotsForFace(enumfacing);

            for (int i : aint) {
               ItemStack itemstack = this.targetInventory.getItem(i);
               if (!itemstack.isEmpty() && canExtractItemFromSlot(this.targetInventory, itemstack, i, enumfacing)) {
                  EntityUtils.consumeItemStack(this.taskOwner, itemstack);
                  if (!itemstack.getItem().isEdible() && PotionUtils.getMobEffects(itemstack).isEmpty()) {
                     this.taskOwner.spawnAtLocation(itemstack, 0.2F);
                  }

                  return true;
               }
            }
         } else {
            int j = this.targetInventory.getContainerSize();

            for (int k = 0; k < j; k++) {
               ItemStack itemstack = this.targetInventory.getItem(k);
               if (!itemstack.isEmpty() && canExtractItemFromSlot(this.targetInventory, itemstack, k, enumfacing)) {
                  this.targetInventory.setItem(k, ItemStack.EMPTY);
                  this.taskOwner.setAnimation(EntityBear.ATTACK_SWIPE);
                  EntityUtils.consumeItemStack(this.taskOwner, itemstack);
                  if (!itemstack.getItem().isEdible() && PotionUtils.getMobEffects(itemstack).isEmpty()) {
                     this.taskOwner.spawnAtLocation(itemstack, 0.2F);
                  }

                  return true;
               }
            }
         }
      }

      return false;
   }

   private static Container getInventoryAtPosition(LevelReader worldIn, BlockPos pos) {
      Container iinventory = null;
      if (worldIn.getBlockEntity(pos) != null) {
         BlockEntity tileentity = worldIn.getBlockEntity(pos);
         if (tileentity instanceof Container) {
            iinventory = (Container)tileentity;
         }
      }

      return iinventory;
   }

   private static boolean isInventoryEmpty(Container inventoryIn, Direction side) {
      if (inventoryIn instanceof WorldlyContainer isidedinventory) {
         int[] aint = isidedinventory.getSlotsForFace(side);

         for (int i : aint) {
            if (!isidedinventory.getItem(i).isEmpty()) {
               return false;
            }
         }
      } else {
         int j = inventoryIn.getContainerSize();

         for (int k = 0; k < j; k++) {
            if (!inventoryIn.getItem(k).isEmpty()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean canExtractItemFromSlot(Container inventoryIn, ItemStack stack, int index, Direction side) {
      return !(inventoryIn instanceof WorldlyContainer) || ((WorldlyContainer)inventoryIn).canTakeItemThroughFace(index, stack, side);
   }

   public double acceptedDistance() {
      return 0.5;
   }
}
