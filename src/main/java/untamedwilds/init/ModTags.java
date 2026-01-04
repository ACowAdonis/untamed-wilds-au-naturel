package untamedwilds.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ModTags {
   public static class EntityTags {
      public static final TagKey<EntityType<?>> CAGE_BLACKLIST = TagKey.create(
         Registries.ENTITY_TYPE, new ResourceLocation("untamedwilds", "cage_trap_blacklist")
      );
      public static final TagKey<EntityType<?>> HAS_GUARD_AI = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("untamedwilds", "has_guard_ai"));
   }

   public static class ModBlockTags {
      public static final TagKey<Block> REEDS_PLANTABLE_ON = BlockTags.create(new ResourceLocation("untamedwilds", "reeds_plantable_on"));
      public static final TagKey<Block> ALOE_PLANTABLE_ON = BlockTags.create(new ResourceLocation("untamedwilds", "aloe_plantable_on"));
      public static final TagKey<Block> GRAZEABLE_BLOCKS = BlockTags.create(new ResourceLocation("untamedwilds", "grazeable_blocks"));
      public static final TagKey<Block> GRAZEABLE_ALGAE = BlockTags.create(new ResourceLocation("untamedwilds", "grazeable_algae"));
      public static final TagKey<Block> VALID_REPTILE_NEST = BlockTags.create(new ResourceLocation("untamedwilds", "valid_reptile_nest"));
      public static final TagKey<Block> SOFT_SOIL = BlockTags.create(new ResourceLocation("untamedwilds", "soft_soil"));
   }
}
