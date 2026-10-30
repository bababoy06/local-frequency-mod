# Local Frequency Mod

A standalone Minecraft Fabric mod for proximity radio, temporary pings, and coordinate-based radio towers.

## Current milestone

The first prototype adds a pixel-inspired radio receiver screen with a configurable keybind. It is currently a UI mockup: signal counts, tower status, and signal strength are placeholder values until the discovery and networking systems are implemented.

## Target platform

- Minecraft 1.21.11
- Fabric Loader
- Java 21 at runtime (the Java version Minecraft 1.21.11 ships with)
- Project ID: `local_frequency`

## Controls

- **"R"**: open or close the Radio Receiver (change this under Options → Controls → Key Binds)

## Planned milestones

1. Receiver screen and keybind
2. Tuning sound and audio settings
3. Local player presence and one-time arrival chirp
4. Temporary pings and location markers
5. Coordinate-based towers with idle/active lifecycle
6. Long-range relay networking

The mod is intended to work independently of CivRails. Any CivRails integration will be optional.

## Development

Open this project as a Gradle project in IntelliJ IDEA or VS Code with a Java 25 JDK installed (used to run Gradle and Loom). The mod itself is compiled to Java 21 bytecode and declares Java 21 as its minimum runtime, so it runs on the Java version the Minecraft launcher provides. The build uses Fabric Loom and Minecraft's official Mojang mappings.

The Gradle wrapper (Gradle 9.7.0, required by Loom 1.18) is included, so no separate Gradle install is needed. Run `./gradlew build` (or `gradlew.bat build` on Windows) to compile and package the mod; the JAR is written to `build/libs`. Use `./gradlew runClient` to start a development client.
