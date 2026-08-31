# Duet Adventure

A Kotlin Multiplatform intimacy app for couples: a browsable, searchable library of positions with
categories, favourites, a suggestion of the day, and the ability to add your own entries with photos.

Built from a single shared codebase for **Android and iOS**.

> Adults only (18+). The app ships with a curated set of predefined entries and stores everything
> locally on the device — no account, no backend, no data leaves the phone.

## Features

- **Browse by category** — the full library, filtered into categories
- **Search** by name
- **Favourites** — mark entries and get to them from a dedicated tab
- **Suggestion of the day** — a rotating daily pick, persisted so it stays stable through the day
- **Add your own** — create custom entries with a photo from the device gallery
- **Detail view** for each entry
- **Profile** screen
- Bottom-navigation shell with type-safe routes

## Architecture

Layered multiplatform modules with a one-way dependency direction:

```
composeApp  ──►  designsystem
     │
     └────────►  domain  ──►  core
```

| Module | Responsibility | Depends on |
|---|---|---|
| `composeApp` | Compose Multiplatform UI, ViewModels, navigation | `designsystem`, `domain` |
| `domain` | Use cases, domain models, mappers, predefined-content provider | `core` |
| `core` | Room database, entities, DAOs, data sources, repositories, image storage | — |
| `designsystem` | Colors, typography, `AppTheme`, shared UI elements | — |

Business logic lives in single-responsibility use cases — `GetPosesByCategoryUseCase`,
`SearchPosesByNameUseCase`, `ToggleFavouriteUseCase`, `GetPoseOfTheDayUseCase`, `GetPoseByIdUseCase` —
so screens stay thin and the rules are testable without a UI.

## Tech stack

| | |
|---|---|
| Language | Kotlin 2.4 |
| UI | Compose Multiplatform, Material 3 |
| Navigation | Navigation 3 (`org.jetbrains.androidx.navigation3`, the Compose Multiplatform port) |
| DI | Koin |
| Persistence | Room (KMP) with the bundled SQLite driver |
| Async | Coroutines + Flow |
| Other | kotlinx.serialization, kotlinx.datetime, multiplatform photo picker |

### Platform-specific code

The shared code sits in `commonMain`; platform differences are handled with `expect`/`actual` rather
than by forking features. Two places need it:

```
core/src/{androidMain,iosMain}/…/database/{AndroidDatabase,IOSDatabase}.kt   # Room builder
core/src/{androidMain,iosMain}/…/datasource/ImageStorage.{android,ios}.kt    # saving user photos
```

`iosApp/` holds the thin SwiftUI host for the iOS entry point.

## Running

```bash
./gradlew :composeApp:assembleDebug     # Android
```

For iOS, open `iosApp/` in Xcode and run from there.

## Tests

```bash
./gradlew :composeApp:testDebugUnitTest
./gradlew :composeApp:iosSimulatorArm64Test
```

## License

Apache License 2.0 — see [LICENSE](./LICENSE).
