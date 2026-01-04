# Untamed Wilds - Au Naturel

A maintained fork of the [Untamed Wilds](https://www.curseforge.com/minecraft/mc-mods/untamed-wilds) mod for Minecraft 1.20.1 (Forge).

## About

Untamed Wilds expands Minecraft's natural world with diverse wildlife, enhanced ecosystems, and realistic animal behaviors. This fork includes bug fixes, performance improvements, and new gameplay mechanics.

## Original Project

- **Original Author**: RayTrace082
- **Original Project**: [Untamed Wilds on CurseForge](https://www.curseforge.com/minecraft/mc-mods/untamed-wilds)
- **License**: GNU General Public License (GPL)

## Fork Changes

This fork includes the following improvements over the original 4.0.4 release:

### Bug Fixes
- Fixed mobs getting stuck in water (improved land pathfinding in `SmartSwimGoal_Land`)
- Added step height attributes to ground mobs (boar, aardvark, tarantula, snake, tortoise, spitter)
- Fixed C2ME chunk bounds issues in world generation features
- Optimized bear damage boost effect to prevent save file bloat
- Added entity search caching to `HuntMobTarget` for performance

### New Features
- **Predator Player Hunting**: Bears, big cats, and hyenas can now view players as potential prey
  - Configurable "man-eater" spawn chance (5% default)
  - Configurable hungry predator hunt chance (2%/sec default)
  - Sneaking players are exempt
  - Pandas excluded from aggressive behavior

### Build System
- Auto-incrementing build numbers for version tracking

## Requirements

- Minecraft 1.20.1
- Forge 47.4.10+
- [Citadel](https://www.curseforge.com/minecraft/mc-mods/citadel) (dependency)

## Building

```bash
./gradlew build
```

Output JAR will be in `build/libs/`.

## Configuration

Configuration files are generated in your Minecraft config folder under `untamedwilds/`.

Key gamerule options:
- `predator_player_hunting` - Enable/disable predator player targeting
- `predator_hungry_hunt_chance` - Chance per second for hungry predators to target players
- `predator_man_eater_chance` - Chance on spawn for a predator to become a "man-eater"

## License

This project is licensed under the GNU General Public License (GPL), the same license as the original Untamed Wilds mod.

## Credits

- **RayTrace082** - Original Untamed Wilds mod
- **Minecraft Forge Team** - Forge modding framework
- **Alex/Citadel** - Animation library
