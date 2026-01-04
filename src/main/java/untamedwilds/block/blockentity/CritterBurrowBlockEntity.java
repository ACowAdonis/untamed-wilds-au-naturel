package untamedwilds.block.blockentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.entity.ComplexMob;
import untamedwilds.init.ModBlock;
import untamedwilds.util.EntityUtils;

public class CritterBurrowBlockEntity extends BlockEntity {
   private final List<CritterBurrowBlockEntity.Inhabitants> inhabitants = Lists.newArrayList();
   private EntityType<?> entityType;
   private int variant;
   private int count;

   public CritterBurrowBlockEntity(BlockPos pos, BlockState state) {
      super((BlockEntityType)ModBlock.TILE_ENTITY_BURROW.get(), pos, state);
   }

   public int getSumMobs() {
      return this.inhabitants.size() + this.count;
   }

   public boolean hasNoMobs() {
      return this.getSumMobs() == 0;
   }

   public void tryEnterBurrow(LivingEntity entityIn) {
      entityIn.stopRiding();
      entityIn.ejectPassengers();
      CompoundTag CompoundTag = EntityUtils.writeEntityToNBT(entityIn, true);
      this.inhabitants.add(new CritterBurrowBlockEntity.Inhabitants(CompoundTag));
      if (this.getLevel() != null) {
         BlockPos blockpos = this.getBlockPos();
         this.level
            .playSound(
               null,
               (double)blockpos.getX(),
               (double)blockpos.getY(),
               (double)blockpos.getZ(),
               SoundEvents.BEEHIVE_ENTER,
               SoundSource.BLOCKS,
               1.0F,
               1.0F
            );
         EntityUtils.spawnParticlesOnEntity(this.getLevel(), entityIn, ParticleTypes.POOF, 3, 6);
      }

      entityIn.remove(RemovalReason.DISCARDED);
      this.setChanged();
   }

   public void releaseOrCreateMob(ServerLevel worldIn) {
      if (!this.hasNoMobs()
         && this.getEntityType() != null
         && this.getVariant() >= 0
         && (double)worldIn.getRandom().nextFloat() < 0.1 * (double)(this.getSumMobs() * this.getSumMobs())) {
         BlockPos blockpos = this.getBlockPos();
         if (worldIn.hasNearbyAlivePlayer(
            (double)blockpos.getX() + 0.5,
            (double)blockpos.getY() + 0.5,
            (double)blockpos.getZ() + 0.5,
            (double)((Integer)ConfigMobControl.critterSpawnRange.get()).intValue()
         )) {
            if (!this.getInhabitants().isEmpty()) {
               int i = worldIn.random.nextInt(this.inhabitants.size());
               Entity spawn = this.getEntityType().create(worldIn, this.inhabitants.get(i).entityData, null, blockpos, MobSpawnType.DISPENSER, true, false);
               if (spawn != null) {
                  worldIn.addFreshEntityWithPassengers(spawn);
                  this.inhabitants.remove(i);
                  this.setChanged();
               }
            } else if (this.getCount() > 0 && this.getEntityType() != null) {
               Entity spawn = this.getEntityType().create(worldIn);
               if (spawn != null) {
                  spawn.moveTo(
                     (double)blockpos.getX() + 0.5,
                     (double)blockpos.getY(),
                     (double)blockpos.getZ() + 0.5,
                     Mth.wrapDegrees(worldIn.random.nextFloat() * 360.0F),
                     0.0F
                  );
                  if (spawn instanceof Mob mobSpawn) {
                     ForgeEventFactory.onFinalizeSpawn(mobSpawn, worldIn, worldIn.getCurrentDifficultyAt(blockpos), MobSpawnType.CHUNK_GENERATION, null, null);
                  }

                  if (spawn instanceof ComplexMob entitySpawn) {
                     entitySpawn.setVariant(EntityUtils.getClampedNumberOfSpecies(this.variant, this.entityType));
                     entitySpawn.setHome(this.getBlockPos());
                  }

                  worldIn.addFreshEntityWithPassengers(spawn);
                  this.setCount(this.getCount() - 1);
                  this.setChanged();
               }
            }

            if (worldIn.getRandom().nextInt((Integer)ConfigMobControl.burrowRepopulationChance.get()) == 0 && this.getCount() < 20) {
               this.setCount(this.getCount() + 1);
            }
         }
      }
   }

   public void setCount(int newCount) {
      this.count = newCount;
   }

   public int getCount() {
      return this.count;
   }

   public void setVariant(int variant) {
      this.variant = variant;
   }

   public int getVariant() {
      return this.variant;
   }

   public void setEntityType(EntityType<?> type) {
      this.entityType = type;
   }

   public EntityType<?> getEntityType() {
      return this.entityType;
   }

   public void load(CompoundTag compound) {
      super.load(compound);
      this.inhabitants.clear();
      ListTag listnbt = compound.getList("Inhabitants", 10);
      this.setVariant(compound.getInt("Variant"));
      this.setCount(compound.getInt("Count"));
      if (compound.contains("entityType")) {
         this.setEntityType((EntityType<?>)EntityType.byString(compound.getString("entityType")).orElse(null));
      }

      for (int i = 0; i < listnbt.size(); i++) {
         CompoundTag CompoundTag = listnbt.getCompound(i);
         CritterBurrowBlockEntity.Inhabitants beehivetileentity$bee = new CritterBurrowBlockEntity.Inhabitants(CompoundTag.getCompound("EntityData"));
         this.inhabitants.add(beehivetileentity$bee);
      }
   }

   public void saveAdditional(CompoundTag compound) {
      super.saveAdditional(compound);
      compound.put("Inhabitants", this.getInhabitants());
      compound.putInt("Count", this.getCount());
      compound.putInt("Variant", this.getVariant());
      if (this.getEntityType() != null) {
         compound.putString("entityType", EntityUtils.getRegistryName(this.getEntityType()));
      }
   }

   public ListTag getInhabitants() {
      ListTag inhabitants = new ListTag();

      for (CritterBurrowBlockEntity.Inhabitants inhabitant : this.inhabitants) {
         CompoundTag CompoundTag = new CompoundTag();
         CompoundTag.put("EntityData", inhabitant.entityData);
         inhabitants.add(CompoundTag);
      }

      return inhabitants;
   }

   static class Inhabitants {
      private final CompoundTag entityData;

      private Inhabitants(CompoundTag nbt) {
         nbt.remove("UUID");
         this.entityData = nbt;
      }
   }
}
