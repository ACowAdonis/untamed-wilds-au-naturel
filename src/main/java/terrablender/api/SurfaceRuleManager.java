package terrablender.api;

import net.minecraft.world.level.levelgen.SurfaceRules;

/**
 * Compile-time stub for TerraBlender API.
 * The real TerraBlender will be available at runtime from the modpack.
 * This allows Citadel's TerrablenderCompat to compile.
 */
public class SurfaceRuleManager {

    public enum RuleCategory {
        OVERWORLD,
        NETHER
    }

    public enum RuleStage {
        BEFORE_BEDROCK,
        AFTER_BEDROCK
    }

    public static void addToDefaultSurfaceRulesAtStage(RuleCategory category, RuleStage stage, int priority, SurfaceRules.RuleSource rule) {
        // Stub - real implementation provided by TerraBlender at runtime
    }

    public static void addSurfaceRules(RuleCategory category, String namespace, SurfaceRules.RuleSource rule) {
        // Stub - real implementation provided by TerraBlender at runtime
    }
}
