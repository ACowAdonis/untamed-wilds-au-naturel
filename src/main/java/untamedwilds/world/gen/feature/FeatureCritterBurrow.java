package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import untamedwilds.block.CritterBurrowBlock;
import untamedwilds.block.blockentity.CritterBurrowBlockEntity;
import untamedwilds.config.ConfigMobControl;
import untamedwilds.entity.ComplexMobAmphibious;
import untamedwilds.entity.ISpecies;
import untamedwilds.init.ModBlock;
import untamedwilds.world.FaunaHandler;

public class FeatureCritterBurrow extends Feature<NoneFeatureConfiguration> {
   public FeatureCritterBurrow(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext context) {
      RandomSource rand = context.level().getRandom();
      BlockPos pos = context.origin();
      WorldGenLevel world = context.level();
      if (((List)ConfigMobControl.dimensionBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         int i = rand.nextInt(8) - rand.nextInt(8);
         int j = rand.nextInt(8) - rand.nextInt(8);
         int k = world.getHeight(Types.OCEAN_FLOOR_WG, pos.getX() + i, pos.getZ() + j);
         pos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
         Optional<FaunaHandler.SpawnListEntry> entry = WeightedRandom.getRandomItem(rand, FaunaHandler.getSpawnableList(FaunaHandler.animalType.CRITTER));
         if (entry.isPresent()) {
            Entity entity = entry.get().entityType.create(world.getLevel());
            int variant = -1;
            if (entity != null && world.getBlockState(pos).is(BlockTags.REPLACEABLE)) {
               if (!world.getFluidState(pos).isEmpty() && !(entity instanceof ComplexMobAmphibious)) {
                  return false;
               }

               if (entity instanceof ISpecies) {
                  variant = ((ISpecies)entity).setSpeciesByBiome(world.getBiome(pos), MobSpawnType.CHUNK_GENERATION);
                  if (variant == 99) {
                     entity.discard();
                     return false;
                  }
               }

               if (world.getBlockEntity(pos) == null) {
                  world.setBlock(
                     pos, (BlockState)((Block)ModBlock.BURROW.get()).defaultBlockState().setValue(CritterBurrowBlock.WATERLOGGED, !world.getFluidState(pos).isEmpty()), 2
                  );
                  world.getChunk(pos).markPosForPostprocessing(pos.below());
                  if (world.getBlockState(pos).getBlock() == ModBlock.BURROW.get()) {
                     CritterBurrowBlockEntity te = (CritterBurrowBlockEntity)world.getBlockEntity(pos);
                     if (te != null) {
                        te.setCount(Math.max(4, entry.get().getGroupCount() * (rand.nextInt(3) + 1)));
                        te.setEntityType(entry.get().entityType);
                        if (variant >= 0) {
                           te.setVariant(variant);
                        }
                     }

                     entity.discard();
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }
}
