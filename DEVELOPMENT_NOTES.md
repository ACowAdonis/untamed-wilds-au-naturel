# Untamed Wilds Development Notes

This document tracks the reverse engineering and build setup for the Untamed Wilds mod (1.20.1).

## Project Overview

- **Original Mod**: Untamed Wilds 4.0.4 for Minecraft 1.20.1
- **License**: GNU GPL (permissive, allows modification)
- **Status**: Abandoned by original author
- **Goal**: Fix AI and behavioral issues for use in a modpack

## Build Environment

- **Forge Version**: 47.4.10
- **Minecraft**: 1.20.1
- **Java**: 17
- **Mappings**: Official Mojang mappings

## Key Fixes Applied

### 1. Config Initialization Order
**File**: `src/main/java/untamedwilds/config/ConfigBase.java`

The original decompiled code had static field initialization in the wrong order. The `build()` method was called before config values were defined.

**Fix**: Reorder static fields so config classes (FEATURES, GAMERULES, MOBS, COMPAT) are initialized BEFORE calling `common_builder.build()`.

### 2. Citadel Dependency Handling
**File**: `build.gradle`

Citadel is a dependency that provides animation and model utilities. The mod was bundling Citadel classes, causing conflicts with the standalone Citadel mod in modpacks.

**Fix**:
- Compile Citadel from source (in `src/main/java/com/github/alexthe666/citadel/`)
- Exclude from final JAR with: `exclude 'com/github/alexthe666/**'`
- Also exclude TerraBlender: `exclude 'terrablender/**'`

### 3. Bridge Method Remapping (Critical)
**File**: `build.gradle` (fixBridgeMethods task)

The most complex issue. Model classes extend `AdvancedEntityModel<SpecificEntity>` which requires a bridge method for `setupAnim(Entity, ...)`. At runtime, Minecraft expects this method to be named `m_6973_` (SRG name), but ForgeGradle's reobfuscation wasn't renaming it correctly due to mapping conflicts with Citadel source.

**Fix**: Post-process the JAR using ASM bytecode manipulation to rename:
- `setupAnim(Entity, float, float, float, float, float)` → `m_6973_(Entity, float, float, float, float, float)`

This is done automatically by the `fixBridgeMethods` Gradle task.

### 4. Redundant Abstract Declaration
**File**: `src/main/java/com/github/alexthe666/citadel/client/model/basic/BasicEntityModel.java`

Removed the redundant `@Override public abstract void setupAnim(...)` declaration. This was inherited from EntityModel and the redeclaration contributed to mapping conflicts.

## Build Commands

```bash
# Clean build
./gradlew clean build

# Output JAR location
build/libs/untamedwilds-4.0.4.jar
```

## Project Structure

```
forge-mdk/
├── src/main/java/
│   ├── untamedwilds/           # Main mod code
│   │   ├── client/model/       # Entity models (38 model classes)
│   │   ├── config/             # Configuration classes
│   │   ├── entity/             # Entity definitions and AI
│   │   └── ...
│   ├── com/github/alexthe666/citadel/  # Citadel source (compile-only)
│   └── terrablender/           # TerraBlender stubs (compile-only)
├── libs/
│   └── citadel-2.6.2-1.20.1.jar  # Reference JAR (not used in build)
└── build.gradle
```

## Runtime Dependencies

These mods must be present in the modpack:
- **Citadel** 2.6.x - Animation and model library
- **TerraBlender** (if biome features are used)

## Known Issues & Workarounds

### FaunaSpawn.java Variable Scope
The `pendingMob` variable needed to be declared outside the loop to properly clean up entities that fail spawn checks.

### Decompiler Artifacts
Some decompiled code has unusual patterns (label92, label83, etc.) from the decompiler. These work but could be refactored for clarity.

## Future Work

- Investigate and fix AI/behavioral issues (original goal)
- Clean up decompiler artifacts for better readability
- Consider updating deprecated API usage (ResourceLocation constructors, etc.)

## References

- Original mod: [CurseForge](https://www.curseforge.com/minecraft/mc-mods/untamed-wilds)
- Citadel: [CurseForge](https://www.curseforge.com/minecraft/mc-mods/citadel)
- Forge Documentation: [docs.minecraftforge.net](https://docs.minecraftforge.net)
