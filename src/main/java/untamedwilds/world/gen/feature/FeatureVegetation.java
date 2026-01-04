package untamedwilds.world.gen.feature;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.common.Tags.Biomes;
import untamedwilds.block.IPostGenUpdate;
import untamedwilds.config.ConfigFeatureControl;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;

public class FeatureVegetation extends Feature<ProbabilityFeatureConfiguration> {
   public FeatureVegetation(Codec<ProbabilityFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
      RandomSource rand = context.level().getRandom();
      WorldGenLevel world = context.level();
      BlockPos genPos = world.getHeightmapPos(Types.OCEAN_FLOOR, context.origin());
      if (((List)ConfigFeatureControl.dimensionFeatureBlacklist.get()).contains(world.getLevel().dimension().location().toString())) {
         return false;
      } else {
         boolean flag = false;
         int originX = genPos.getX() + 8;
         int originY = genPos.getY();
         int originZ = genPos.getZ() + 8;
         MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();
         Pair<Block, Integer> flora = FeatureVegetation.FloraTypes.getFloraForPos(world, genPos);
         if (flora != null) {
            Block block = (Block)flora.getFirst();
            int size = (Integer)flora.getSecond();

            for (int i = 0; i < size; i++) {
               // Use set() instead of cumulative move() to stay within chunk bounds
               blockpos$mutableblockpos.set(
                  originX + rand.nextInt(4) - rand.nextInt(4),
                  originY + rand.nextInt(2) - rand.nextInt(2),
                  originZ + rand.nextInt(4) - rand.nextInt(4)
               );
               if (world.getBlockState(blockpos$mutableblockpos.below()).is(ModTags.ModBlockTags.ALOE_PLANTABLE_ON)
                  && !world.getBlockState(blockpos$mutableblockpos).isFaceSturdy(world, blockpos$mutableblockpos, Direction.UP)
                  && world.getFluidState(blockpos$mutableblockpos).isEmpty()
                  && block != null) {
                  world.setBlock(blockpos$mutableblockpos, block.defaultBlockState(), 2);
                  world.getChunk(blockpos$mutableblockpos).markPosForPostprocessing(blockpos$mutableblockpos.below());
                  if (block instanceof IPostGenUpdate) {
                     ((IPostGenUpdate)block).updatePostGen(world, blockpos$mutableblockpos);
                  }

                  flag = true;
               }
            }
         }

         return flag;
      }
   }

   public static enum FloraTypes {
      TEMPERATE_BUSH(
         (Block)ModBlock.BUSH_TEMPERATE.get(),
         6,
         (Boolean)ConfigFeatureControl.addFlora.get(),
         false,
         24,
         new TagKey[]{BiomeTags.IS_FOREST, Biomes.IS_SWAMP, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_TAIGA, Biomes.IS_PLAINS}
      ),
      CREOSOTE_BUSH(
         (Block)ModBlock.BUSH_CREOSOTE.get(), 2, (Boolean)ConfigFeatureControl.addFlora.get(), false, 4, new TagKey[]{BiomeTags.IS_BADLANDS, Biomes.IS_DESERT}
      ),
      ELEPHANT_EAR((Block)ModBlock.ELEPHANT_EAR.get(), 6, (Boolean)ConfigFeatureControl.addFlora.get(), false, 24, new TagKey[]{BiomeTags.IS_JUNGLE}),
      HEMLOCK((Block)ModBlock.HEMLOCK.get(), 1, (Boolean)ConfigFeatureControl.addFlora.get(), false, 12, new TagKey[]{BiomeTags.IS_FOREST}),
      TITAN_ARUM((Block)ModBlock.TITAN_ARUM.get(), 6, (Boolean)ConfigFeatureControl.addFlora.get(), false, 1, new TagKey[]{BiomeTags.IS_JUNGLE}),
      ZIMBABWE_ALOE((Block)ModBlock.ZIMBABWE_ALOE.get(), 4, (Boolean)ConfigFeatureControl.addFlora.get(), false, 1, new TagKey[]{BiomeTags.IS_BADLANDS}),
      FLOWER_YARROW(
         (Block)ModBlock.YARROW.get(),
         6,
         (Boolean)ConfigFeatureControl.addFlora.get(),
         false,
         18,
         new TagKey[]{BiomeTags.IS_FOREST, Biomes.IS_PLAINS, BiomeTags.IS_MOUNTAIN}
      ),
      GRASS_JUNEGRASS((Block)ModBlock.JUNEGRASS.get(), 8, (Boolean)ConfigFeatureControl.addFlora.get(), false, 18, new TagKey[]{Biomes.IS_PLAINS}),
      CANOLA((Block)ModBlock.CANOLA.get(), 6, (Boolean)ConfigFeatureControl.addFlora.get(), false, 12, new TagKey[]{Biomes.IS_PLAINS});

      public Block type;
      public int rarity;
      public boolean enabled;
      public boolean spawnsInWater;
      public int size;
      public TagKey<Biome>[] spawnBiomes;

      private FloraTypes(Block type, int rolls, boolean add, boolean spawnsInWater, int size, TagKey<Biome>[] biomes) {
         this.type = type;
         this.rarity = rolls;
         this.enabled = add;
         this.spawnsInWater = spawnsInWater;
         this.spawnBiomes = biomes;
         this.size = size;
      }

      public static Pair<Block, Integer> getFloraForPos(WorldGenLevel world, BlockPos pos) {
         Holder<Biome> biome = world.getBiome(pos);
         List<FeatureVegetation.FloraTypes> types = new ArrayList<>();

         for (FeatureVegetation.FloraTypes type : values()) {
            if (type.enabled && (type.spawnsInWater || world.getBlockState(pos).getBlock() != Blocks.WATER)) {
               for (TagKey<Biome> biomeTypes : type.spawnBiomes) {
                  if (biome.is(biomeTypes)) {
                     for (int i = 0; i < type.rarity; i++) {
                        types.add(type);
                     }
                  }
               }
            }
         }

         if (!types.isEmpty()) {
            int i = world.getRandom().nextInt(types.size());
            return new Pair(types.get(i).type, types.get(i).size);
         } else {
            return null;
         }
      }
   }
}
