package untamedwilds.world;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.ForgeEventFactory;
import untamedwilds.UntamedWilds;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.entity.ComplexMob;
import untamedwilds.entity.ISpecies;
import untamedwilds.util.EntityUtils;

public class FaunaSpawn {
   private static boolean canCreatureTypeSpawnAtLocation(Type placeType, LevelReader worldIn, BlockPos pos, @Nullable EntityType<?> entityTypeIn) {
      if (placeType == Type.NO_RESTRICTIONS) {
         if ((Boolean)ConfigGamerules.preventMobsOnWater.get()) {
            BlockPos pos2 = worldIn.getHeightmapPos(Types.OCEAN_FLOOR, pos);
            return worldIn.getFluidState(pos2).isEmpty();
         } else {
            return true;
         }
      } else {
         return entityTypeIn != null && worldIn.getWorldBorder().isWithinBounds(pos) ? canSpawnAtBody(placeType, worldIn, pos, entityTypeIn) : false;
      }
   }

   private static boolean canSpawnAtBody(Type placeType, LevelReader worldIn, BlockPos pos, @Nullable EntityType<?> entityTypeIn) {
      BlockState blockstate = worldIn.getBlockState(pos);
      FluidState fluidstate = worldIn.getFluidState(pos);
      BlockPos pos_below = pos.below();
      switch (placeType) {
         case IN_WATER:
            return fluidstate.is(FluidTags.WATER);
         case IN_LAVA:
            return fluidstate.is(FluidTags.LAVA);
         case ON_GROUND:
         default:
            BlockState blockstate1 = worldIn.getBlockState(pos_below);
            return !blockstate1.isValidSpawn(worldIn, pos_below, placeType, entityTypeIn)
               ? false
               : isSpawnableSpace(worldIn, pos, blockstate, fluidstate, entityTypeIn);
      }
   }

   private static boolean isSpawnableSpace(LevelReader worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn, EntityType<?> entityTypeIn) {
      return !state.isCollisionShapeFullBlock(worldIn, pos) && !state.isSignalSource() && fluidStateIn.isEmpty() && !state.is(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
         ? !entityTypeIn.isBlockDangerous(state)
         : false;
   }

   public static boolean performWorldGenSpawning(
      EntityType<?> entityType, Type spawnType, @Nullable Types heightMap, ServerLevelAccessor worldIn, BlockPos pos, RandomSource random, int groupSize
   ) {
      if (((List)ConfigMobControl.dimensionBlacklist.get()).contains(worldIn.getLevel().dimension().location().toString())) {
         return false;
      } else if (entityType != null && !worldIn.isClientSide()) {
         int i = pos.getX() + random.nextInt(16);
         int j = pos.getZ() + random.nextInt(16);
         if (heightMap != null) {
            pos.offset(i, 0, j);
            pos = worldIn.getHeightmapPos(heightMap, pos);
         }

         MutableBlockPos blockPos = new MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
         int k = 1;
         int species = -1;
         int packSize = 0;

         Mob pendingMob = null;

         label92:
         while (true) {
            if (packSize >= k) {
               return true;
            }

            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            if (packSize != 0) {
               x += random.nextInt(6);
               z += random.nextInt(6);
            }

            int attempt = 0;

            while (true) {
               if (attempt >= 4) {
                  break label92;
               }

               if (attempt != 0) {
                  if (attempt == 1) {
                     y += ConfigMobControl.treeSpawnBias.get();
                  }

                  x += random.nextInt(2);
                  z += random.nextInt(2);
               }

               blockPos.set(x, y, z);
               if (entityType.canSummon() && canCreatureTypeSpawnAtLocation(spawnType, worldIn, blockPos, entityType)) {
                  float f = entityType.getWidth();
                  double d0 = Mth.clamp((double)x, (double)blockPos.getX() + (double)f, (double)blockPos.getX() + 16.0 - (double)f);
                  double d1 = Mth.clamp((double)z, (double)blockPos.getZ() + (double)f, (double)blockPos.getZ() + 16.0 - (double)f);
                  label83:
                  if (worldIn.noCollision(entityType.getAABB(d0, (double)y, d1))
                     && SpawnPlacements.checkSpawnRules(entityType, worldIn, MobSpawnType.CHUNK_GENERATION, blockPos, worldIn.getRandom())) {
                     Entity entity;
                     try {
                        entity = entityType.create(worldIn.getLevel());
                     } catch (Exception var25) {
                        UntamedWilds.LOGGER.warn("Failed to create mob", var25);
                        break label83;
                     }

                     if (entity == null) {
                        UntamedWilds.LOGGER.warn("Entity creation returned null for type: {}", entityType);
                        break label83;
                     }

                     entity.moveTo((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), random.nextFloat() * 360.0F, 0.0F);
                     if (entity instanceof Mob mobEntity && mobEntity.checkSpawnRules(worldIn, MobSpawnType.CHUNK_GENERATION) && worldIn.noCollision(entity)) {
                        pendingMob = mobEntity;
                        if (mobEntity instanceof ComplexMob && mobEntity.isAlive()) {
                           if (mobEntity instanceof ISpecies) {
                              if (species == -1) {
                                 Holder<Biome> optional = worldIn.getBiome(blockPos);
                                 species = ((ISpecies)mobEntity).setSpeciesByBiome(optional, MobSpawnType.CHUNK_GENERATION);
                              }

                              ((ComplexMob)mobEntity).setVariant(species);
                           }

                           if (species == 99 || !ComplexMob.ENTITY_DATA_HASH.containsKey(entityType)) {
                              break label92;
                           }
                        }

                        k = EntityUtils.getPackSize(entityType, species);
                        ForgeEventFactory.onFinalizeSpawn(mobEntity, worldIn, worldIn.getCurrentDifficultyAt(mobEntity.blockPosition()), MobSpawnType.DISPENSER, null, null);
                        worldIn.addFreshEntityWithPassengers(mobEntity);
                        pendingMob = null;
                        packSize++;
                        break;
                     }
                  }
               }

               attempt++;
            }
         }

         if (pendingMob != null) {
            pendingMob.remove(RemovalReason.DISCARDED);
         }
         return false;
      } else {
         return false;
      }
   }
}
