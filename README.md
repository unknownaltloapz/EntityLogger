# EntityLogger

EntityLogger is a client-side Fabric mod for Minecraft 26.3. It adds a white glowing outline to falling block entities and block display entities by modifying their client render state.

## Features

- Press **P** to enable or disable EntityLogger.
- The key can be changed in **Options → Controls → Key Binds**.
- The activation message reports how many matching entities are currently loaded for rendering.
- Entity outlines are applied directly to the client render state, so server synchronization cannot immediately clear the effect.
- The mod only runs on the client and does not need to be installed on the server.

## Requirements

- Minecraft Java Edition 26.3
- Fabric Loader 0.20.1 or newer
- Fabric API 0.166.0+26.3 or newer
- Java 25 or newer

## Build

On Windows, run:

```powershell
.\\gradlew.bat build
```

The compiled mod is generated in `build/libs/`.
