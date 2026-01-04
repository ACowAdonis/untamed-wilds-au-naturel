# Project Context

## Overview

This is **Untamed Wilds - Au Naturel**, a maintained fork of the Untamed Wilds Minecraft mod for version 1.20.1 (Forge).

## Tech Stack

- **Minecraft**: 1.20.1
- **Forge**: 47.4.10+
- **Java**: 17
- **Build System**: Gradle 8.x with ForgeGradle plugin
- **Animation Library**: Citadel (Alex's Mobs dependency)

## Project Structure

```
src/main/java/untamedwilds/
├── entity/           # All mob entities
│   ├── ai/           # AI goals and behaviors
│   │   └── target/   # Targeting goals (HuntMobTarget, HuntPlayerTarget, etc.)
│   ├── mammal/       # Mammal entities (bears, big cats, etc.)
│   └── ...
├── config/           # Configuration classes (ConfigGamerules, etc.)
├── init/             # Registration classes (ModEntity, ModItems, etc.)
└── world/            # World generation
```

## Key Files

- `build.gradle` - Build configuration with auto-incrementing version numbers
- `gradle.properties` - Mod metadata and version info
- `version.properties` - Build number tracking
- `src/main/java/untamedwilds/config/ConfigGamerules.java` - Game rule configurations

## Entity System

Entities extend from base classes:
- `ComplexMob` - Base for all complex mobs
- `ComplexMobTerrestrial` - Land animals with hunger, sleeping, sitting behaviors
- `ComplexMobAquatic` - Aquatic creatures

## AI Goal System

Uses Minecraft's goal selector system:
- `goalSelector` - Behavior goals (movement, eating, sleeping)
- `targetSelector` - Targeting goals (hunting, defending)

Goals are prioritized by number (lower = higher priority).

## Building

```bash
./gradlew build          # Standard build
./gradlew clean build    # Clean build (resets caches)
```

Build output: `build/libs/untamedwilds-{version}-b{build}.jar`

## Common Tasks

### Adding a new AI goal
1. Create goal class in `entity/ai/` or `entity/ai/target/`
2. Add to entity's `registerGoals()` method
3. Set appropriate priority level

### Adding a config option
1. Add field to `ConfigGamerules.java`
2. Add builder definition in constructor
3. Access via `ConfigGamerules.optionName.get()`

### Modifying entity attributes
1. Find entity class in `entity/` subdirectories
2. Modify `registerAttributes()` for base stats
3. Modify `updateAttributes()` for variant-specific stats
