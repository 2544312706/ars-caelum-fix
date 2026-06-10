# Ars Caelum

Ars Caelum is an Ars Nouveau addon for NeoForge on Minecraft 1.21.1.

## Release Notes

### 1.21.1-3.0.0-hotfix

- Fixed the server-side config correction loop that repeatedly logged `ars_caelum-server.toml is not correct. Correcting`.
- Updated `starter_ritual.ritual_offset` config validation to accept numeric list values as parsed by NeoForge/NightConfig.
- Adjusted release build dependency resolution so the hotfix jar can be built and published cleanly.

## Development

- Build the mod jar with `./gradlew jar` or `gradlew.bat jar`.
- The release artifact is generated under `build/libs`.

## Useful Links

- https://github.com/baileyholl/Ars-Nouveau
- https://github.com/baileyholl/Ars-Nouveau/tree/1.18.x/src/main/java/com/hollingsworth/arsnouveau/api
- https://github.com/bernie-g/geckolib/wiki
