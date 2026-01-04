# Untamed Wilds - Forge Mod Development

Decompiled and reverse-engineered Minecraft mod for Forge 1.20.1. Licensed under GNU GPL.

## Build

```bash
./gradlew clean build
# Output: build/libs/untamedwilds-4.0.4.jar
```

## Critical Build Notes

### Citadel Dependency
Citadel source is in `src/main/java/com/github/alexthe666/citadel/` for compilation but is **excluded from the JAR** (see build.gradle excludes). The runtime Citadel mod is provided by the modpack.

### Bridge Method Fix
The `fixBridgeMethods` Gradle task post-processes the JAR to rename `setupAnim(Entity,...)` → `m_6973_(Entity,...)` in model classes. This is required because ForgeGradle doesn't correctly reobfuscate bridge methods when Citadel source is present. **Do not remove this task.**

### BasicEntityModel Modification
The redundant abstract `setupAnim` declaration was removed from `BasicEntityModel.java` to reduce mapping conflicts. This is intentional.

## Project Structure

- `src/main/java/untamedwilds/` - Main mod code (edit this)
- `src/main/java/com/github/alexthe666/citadel/` - Citadel source (compile-only, don't bundle)
- `src/main/java/terrablender/` - TerraBlender stubs (compile-only)

## Runtime Dependencies

Modpack must include:
- Citadel 2.6.x
- TerraBlender (if biome features used)

## See Also

- `DEVELOPMENT_NOTES.md` - Detailed documentation of all fixes applied
