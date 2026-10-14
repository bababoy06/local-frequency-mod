# Local Frequency Mod

A standalone Minecraft Fabric mod for proximity radio, temporary pings, and coordinate-based radio towers.

## Current milestone

The first prototype adds a pixel-inspired radio receiver screen with a configurable keybind. It is currently a UI mockup: signal counts, tower status, and signal strength are placeholder values until the discovery and networking systems are implemented.

## Target platform

- Minecraft 1.21.11
- Fabric Loader
- Java 21
- Project ID: `local_frequency`

## Controls

- **R** — open or close the Radio Receiver (change this under Options → Controls → Key Binds)

## Planned milestones

1. Receiver screen and keybind
2. Tuning sound and audio settings
3. Local player presence and one-time arrival chirp
4. Temporary pings and location markers
5. Coordinate-based towers with idle/active lifecycle
6. Long-range relay networking

The mod is intended to work independently of CivRails. Any CivRails integration will be optional.

## Development

Open this project as a Gradle project in IntelliJ IDEA or VS Code with Java 21 installed. The build uses Fabric Loom and Minecraft's official Mojang mappings.

Run the Gradle `build` task to compile and package the mod. The remapped JAR will be written to `build/libs`.
