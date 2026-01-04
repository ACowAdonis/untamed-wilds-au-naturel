package untamedwilds.block.blockentity;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import untamedwilds.UntamedWilds;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

public class CageBlockEntity extends BlockEntity {
   private CompoundTag data;
   private boolean locked;

   public CageBlockEntity(BlockPos pos, BlockState state) {
      super((BlockEntityType)ModBlock.TILE_ENTITY_CAGE.get(), pos, state);
   }

   public static boolean isBlacklisted(Entity entity) {
      return entity.getType().is(ModTags.EntityTags.CAGE_BLACKLIST);
   }

   public boolean cageEntity(Mob entity) {
      if (this.isLocked() || isBlacklisted(entity) || !(Boolean)ConfigGamerules.easyMobCapturing.get() && entity.getTarget() != null) {
         return false;
      } else {
         this.setTagCompound(EntityUtils.writeEntityToNBT(entity));
         this.setLocked(true);
         entity.discard();
         this.setChanged();
         return true;
      }
   }

   public boolean spawnCagedCreature(ServerLevel worldIn, BlockPos pos, boolean offsetHitbox) {
      if (!worldIn.isClientSide && this.isLocked()) {
         EntityType<?> entity = EntityUtils.getEntityTypeFromTag(this.getTagCompound(), null);
         if (entity != null
            && worldIn.noCollision(
               entity.getAABB(
                  (double)((float)pos.getX() + 0.5F),
                  (double)((float)pos.getY() - (offsetHitbox ? entity.getHeight() + 1.2F : 0.0F)),
                  (double)((float)pos.getZ() + 0.5F)
               )
            )) {
            if (worldIn.getEntity(this.data.getCompound("EntityTag").getUUID("UUID")) != null) {
               UntamedWilds.LOGGER.info("UUID is already present in the Level; Randomizing UUID for the new mob");
               this.data.getCompound("EntityTag").putUUID("UUID", Mth.createInsecureUUID(worldIn.random));
            }

            Entity caged_entity = EntityType.loadEntityRecursive(this.data.getCompound("EntityTag"), worldIn, input -> input);
            if (caged_entity != null) {
               caged_entity.moveTo(
                  (double)((float)pos.getX() + 0.5F),
                  (double)pos.getY() - (offsetHitbox ? (double)caged_entity.getBbHeight() + 1.2 : 0.8),
                  (double)((float)pos.getZ() + 0.5F),
                  Mth.wrapDegrees(worldIn.random.nextFloat() * 360.0F),
                  0.0F
               );
               worldIn.addFreshEntityWithPassengers(caged_entity);
               this.setTagCompound(null);
               this.setLocked(true);
               return true;
            }
         }
      }

      return false;
   }

   @Nullable
   public CompoundTag getTagCompound() {
      return this.data;
   }

   private void setTagCompound(@Nullable CompoundTag nbt) {
      this.data = nbt;
   }

   public boolean hasTagCompound() {
      return this.data != null;
   }

   public boolean isLocked() {
      return this.locked;
   }

   private void setLocked(boolean locked) {
      this.locked = locked;
   }

   public void load(@NotNull CompoundTag compound) {
      super.load(compound);
      this.setTagCompound(compound.copy());
      this.setLocked(compound.getBoolean("closed"));
   }

   public void saveAdditional(@NotNull CompoundTag compound) {
      super.saveAdditional(compound);
      compound.putBoolean("closed", this.isLocked());
      if (this.getTagCompound() != null) {
         compound.put("EntityTag", this.getTagCompound().getCompound("EntityTag"));
      }
   }
}
