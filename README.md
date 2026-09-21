# Sanbot Agent — TripAndEvent Kiosk Application

> A purpose-built Android application for Sanbot humanoid robots, delivering an interactive travel booking experience through voice-guided kiosk interactions, rich media playback, and a glassmorphism-driven UI.

---

## Table of Contents

- [Executive Summary & Architecture](#1-executive-summary--architecture)
- [Tech Stack & Design Decisions](#2-tech-stack--design-decisions)
- [Key Features & Production Standards](#3-key-features--production-standards)
- [Getting Started](#4-getting-started)
- [Project Structure](#5-project-structure)
- [Testing & Quality Assurance](#6-testing--quality-assurance)
- [CI/CD & Deployment](#7-cicd--deployment)

---

## 1. Executive Summary & Architecture

### Problem Statement

TripAndEvent operates physical travel kiosks across Dubai, Abu Dhabi, and Sharjah. Customers interact with Sanbot humanoid robots to browse tour packages, watch promotional videos, and submit booking inquiries. The application must run reliably in unattended public environments — resetting after inactivity, operating in fullscreen immersive mode, and handling voice-driven interaction without human supervision.

### System Design

```
┌─────────────────────────────────────────────────────────┐
│                    SANBOT HARDWARE                       │
│              (Android-based humanoid robot)               │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌─────────────┐    ┌──────────────┐    ┌───────────┐  │
│  │   Welcome    │───▶│  Main Menu   │───▶│  Settings │  │
│  │   Screen     │    │   (Router)   │    │  (Admin)  │  │
│  └─────────────┘    └──────┬───────┘    └───────────┘  │
│                            │                             │
│           ┌────────────────┼────────────────┐            │
│           ▼                ▼                ▼            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │    Voice      │  │   Package    │  │    Media     │  │
│  │  Interaction  │  │   Browser    │  │   Gallery    │  │
│  │  (ExoPlayer)  │  │   (List+     │  │  (Video +    │  │
│  │               │  │   Details)   │  │   Photos)    │  │
│  └──────┬───────┘  └──────┬───────┘  └──────────────┘  │
│         │                 │                              │
│         ▼                 ▼                              │
│  ┌──────────────────────────────────┐                   │
│  │         Contact Form             │                   │
│  │  (Validation → Submission →      │                   │
│  │   Thank You → Auto-Reset)        │                   │
│  └──────────────────────────────────┘                   │
│                                                         │
├─────────────────────────────────────────────────────────┤
│  LAYER           COMPONENT                              │
│  ─────           ─────────                              │
│  UI Layer        Activities + ViewBinding + Animations   │
│  Base Layer      BaseFullScreenActivity (shared logic)  │
│  Data Layer      Models (@Parcelize) + Repositories     │
│  Resource Layer  Layouts / Drawables / Values / Anim    │
└─────────────────────────────────────────────────────────┘
```

### Core Architectural Decisions

| Concern | Decision | Rationale |
|---|---|---|
| State passing | `Parcelable` via `@Parcelize` | Zero-boilerplate serialization for Activity transitions — no Room/SQLite needed for this kiosk scope |
| Shared behavior | `BaseFullScreenActivity` | Centralizes fullscreen setup, inactivity timer, and navigation — every screen inherits it |
| Data access | Singleton `object` repositories | Static catalog data; no network calls on-device. Backend integration via Settings screen's API URL |
| Video playback | Media3 ExoPlayer | Google's recommended successor to legacy ExoPlayer; hardware-accelerated, supports adaptive streaming |
| UI paradigm | Activity-per-screen (not Fragment) | Kiosk workflows are linear and predictable; Activities enforce clear navigation boundaries |
| Parallel web prototype | Vanilla HTML/CSS/JS in `preview/` | Enables rapid stakeholder demos and design validation without deploying to hardware |

---

## 2. Tech Stack & Design Decisions

| Technology | Version | Why This Choice |
|---|---|---|
| **Kotlin** | 1.9.0 | Null safety, data classes, and `@Parcelize` eliminate entire categories of runtime errors — critical for unattended kiosks |
| **Android SDK** | compileSdk 34, minSdk 26 | API 26+ covers 95%+ of active Android devices; Android 8.0 enables picture-in-picture and notification channels |
| **ViewBinding** | Built-in | Type-safe view access without reflection (unlike `kotlin-android-extensions`) or annotation processing overhead (unlike DataBinding) |
| **Material 3** | 1.10.0 | Provides consistent theming engine with dynamic color support; `MaterialCardView` and `MaterialButton` reduce custom drawable work |
| **ConstraintLayout** | 2.1.4 | Flat view hierarchy for complex kiosk layouts — better measure/pass performance than nested LinearLayouts |
| **Lifecycle KTX** | 2.6.2 | `lifecycleScope` coroutine scoping prevents leaks in animation-heavy screens; ViewModel integration ready for future API layer |
| **Coroutines** | 1.7.3 | Structured concurrency for staggered entrance animations (`delay` + `launch`) without `Handler`/`Thread` boilerplate |
| **Media3 ExoPlayer** | 1.2.0 | Hardware-accelerated H.264/H.265 playback; lifecycle-aware player binding; replaces deprecated `ExoPlayer` library |
| **Kotlin Parcelize** | Plugin | Generates `Parcelable` implementations at compile time — reduces `ContactSubmission` from 40 lines to 6 lines |
| **Gradle Kotlin DSL** | 8.13.1 | Type-safe build configuration; catches dependency resolution errors at configuration time, not build time |
| **ProGuard** | Android default | ExoPlayer keep rules applied; minification ready for release builds |

---

## 3. Key Features & Production Standards

### Functional Features

| Feature | Implementation | Screen |
|---|---|---|
| **Voice Assistant** | Tap-to-speak with waveform visualization, simulated agent responses, conversation history | `VoiceInteractionActivity` |
| **Package Catalog** | 5 travel packages with rich detail (highlights, itinerary, inclusions, exclusions, pricing) | `PackageListActivity` → `PackageDetailsActivity` |
| **Media Gallery** | Grid-based photo browser + video player with ExoPlayer controls | `MediaGalleryActivity` → `VideoPlayerActivity` |
| **Contact Form** | Real-time field validation, package pre-selection, multi-channel submission | `ContactFormActivity` → `ThankYouActivity` |
| **Multi-channel Follow-up** | SMS, WhatsApp, and Email share buttons post-submission | `ThankYouActivity` |
| **Admin Settings** | API endpoint configuration, branch selection, connection testing, cache management | `SettingsActivity` |

### Production-Ready Standards

**Kiosk Resilience**
- `BaseFullScreenActivity` implements a 2-minute inactivity timer that auto-navigates back to the Welcome screen
- All system bars hidden via `WindowInsetsControllerCompat` — no accidental status bar pulls
- Portrait-locked orientation across all 10 Activities prevents rotation-related layout issues
- `adjustResize` soft input mode on form screens ensures keyboard doesn't occlude inputs

**UI/UX Polish**
- Staggered entrance animations on Main Menu (coroutine-driven sequential card reveals with `AccelerateDecelerateInterpolator`)
- Glassmorphism design system (`glassy.css` / `bg_glassy_card.xml`) with backdrop blur and semi-transparent surfaces
- 4 custom XML animations: `fade_in`, `fade_slide_in`, `pulse`, `slide_up`
- 32 vector drawables — fully resolution-independent, no bitmap assets

**Code Quality**
- Strict separation: `data/model/`, `data/repository/`, `ui/<feature>/`, `ui/base/`
- `@Parcelize` on all data classes eliminates manual serialization bugs
- `TextWatcher`-driven form validation with live submit button state management
- ProGuard rules pre-configured for ExoPlayer keep-alive
- AndroidManifest declares only required permissions (`INTERNET`, `RECORD_AUDIO`)

**Performance**
- ViewBinding eliminates `findViewById` overhead across all Activities
- ConstraintLayout flat hierarchy reduces overdraw on complex screens (Package Details, Contact Form)
- `RecyclerView` with `LinearLayoutManager` and `GridLayoutManager` for efficient list/grid rendering
- ExoPlayer lifecycle-aware initialization (`onStart`/`onStop`) prevents media resource leaks

---

## 4. Getting Started

### Prerequisites

| Requirement | Minimum Version | Recommended |
|---|---|---|
| Android Studio | Hedgehog (2023.1) | Iguana (2024.1) |
| JDK | 17 | 17 (LTS) |
| Android SDK | API 34 | API 34 |
| Gradle | 8.2 | 8.4 |
| Sanbot Device | SDK 26+ | — |

### Build & Run

```bash
# Clone the repository
git clone https://github.com/your-org/sanbot-agent.git
cd sanbot-agent

# Build debug APK
./gradlew assembleDebug

# Output location
# app/build/outputs/apk/debug/app-debug.apk

# Install on connected device/emulator
./gradlew installDebug

# Install on Sanbot via ADB
adb -s <SANBOT_SERIAL> install app/build/outputs/apk/debug/app-debug.apk
```

### Web Preview (No Android SDK Required)

The `preview/` directory contains a browser-based interactive prototype of all 10 screens.

```bash
# Option 1: Using npm
npm install
npx http-server preview -p 8080

# Option 2: Using Python
cd preview
python -m http.server 8080

# Option 3: Direct browser open
# Open preview/index.html in any modern browser
```

Navigate to `http://localhost:8080` to interact with the full UI flow.

### Sanbot Deployment Checklist

| Step | Command / Action |
|---|---|
| Enable USB Debugging | Settings → About → Tap Build Number 7 times → Developer Options → USB Debugging |
| Connect via ADB | `adb devices` — confirm Sanbot appears |
| Install APK | `adb -s <SERIAL> install -r app/build/outputs/apk/debug/app-debug.apk` |
| Launch App | `adb shell am start -n com.tripandevent.sanbot/.ui.welcome.WelcomeActivity` |
| Set as Launcher | Settings → Apps → TripAndEvent → Home App → Select "TripAndEvent" |

---

## 5. Project Structure

```
app/src/main/
├── AndroidManifest.xml                    # 10 Activities, 2 permissions
├── java/com/tripandevent/sanbot/
│   ├── data/
│   │   ├── model/
│   │   │   ├── ContactSubmission.kt       # Booking inquiry data class
│   │   │   ├── ConversationMessage.kt     # Voice chat message model
│   │   │   ├── MediaAsset.kt              # Photo/video metadata
│   │   │   └── TravelPackage.kt           # Package + ItineraryItem models
│   │   └── repository/
│   │       ├── MediaRepository.kt         # Static media catalog
│   │       └── PackageRepository.kt       # 5 packages with full details
│   └── ui/
│       ├── base/
│       │   └── BaseFullScreenActivity.kt  # Shared: fullscreen, inactivity, nav
│       ├── contact/
│       │   ├── ContactFormActivity.kt     # Validated form with TextWatcher
│       │   └── ThankYouActivity.kt        # Success + auto-return timer
│       ├── media/
│       │   ├── MediaGalleryActivity.kt    # RecyclerView grid + list
│       │   └── VideoPlayerActivity.kt     # ExoPlayer integration
│       ├── menu/
│       │   └── MainMenuActivity.kt        # Router with entrance animations
│       ├── packages/
│       │   ├── PackageDetailsActivity.kt  # Expandable sections
│       │   └── PackageListActivity.kt     # Scrollable catalog
│       ├── settings/
│       │   └── SettingsActivity.kt        # API config + diagnostics
│       ├── voice/
│       │   └── VoiceInteractionActivity.kt # Tap-to-speak UI
│       └── welcome/
│           └── WelcomeActivity.kt         # Entry point / idle screen
└── res/
    ├── anim/         (4 animations)
    ├── drawable/     (32 vector assets)
    ├── layout/       (16 layouts + 6 list items)
    ├── raw/          (background_video.mp4)
    └── values/       (colors, dimens, strings, themes)
```

---

## 6. Testing & Quality Assurance

### Current Test Coverage

| Category | Status | Notes |
|---|---|---|
| Unit Tests | Not yet implemented | Repository and model layers are pure Kotlin — trivially testable |
| Integration Tests | Not yet implemented | Activity navigation can be covered with Espresso |
| UI Tests | Not yet implemented | Kiosk flow validation via `UiAutomator` recommended |
| Linting | Android Lint (built-in) | Runs automatically on build |

### Recommended Test Strategy

```bash
# Run Android Lint
./gradlew lint

# Run unit tests (when implemented)
./gradlew test

# Run instrumentation tests (when implemented)
./gradlew connectedAndroidTest

# Generate coverage report
./gradlew jacocoTestReport
```

### Key Test Scenarios for This Codebase

| Test Type | Scenario | Target |
|---|---|---|
| Unit | `PackageRepository.getPackageById()` returns correct package | `data/repository/` |
| Unit | `PackageRepository.getPackagesByCategory()` filters correctly | `data/repository/` |
| Unit | `ContactSubmission` parceling round-trips all fields | `data/model/` |
| Integration | Welcome → Menu → Package List → Details → Contact → Thank You navigation chain | All Activities |
| E2E | 2-minute inactivity timeout returns to Welcome screen | `BaseFullScreenActivity` |
| E2E | Form validation rejects invalid email and short phone numbers | `ContactFormActivity` |

---

## 7. CI/CD & Deployment

### Build Pipeline

```
┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
│  Commit   │───▶│  Lint    │───▶│  Build   │───▶│  Test    │
│  Push     │    │  Check   │    │  APK     │    │  Suite   │
└──────────┘    └──────────┘    └──────────┘    └──────────┘
                                                       │
                                                       ▼
                                                ┌──────────┐
                                                │  Sign    │
                                                │  Release │
                                                └────┬─────┘
                                                     │
                                         ┌───────────┼───────────┐
                                         ▼           ▼           ▼
                                    ┌────────┐ ┌────────┐ ┌──────────┐
                                    │  Play  │ │  ADB   │ │  Sanbot  │
                                    │  Store │ │  Push  │ │  OTA     │
                                    └────────┘ └────────┘ └──────────┘
```

### Recommended GitHub Actions Workflow

```yaml
# .github/workflows/android-ci.yml
name: Android CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Build Debug APK
        run: ./gradlew assembleDebug
      - name: Run Lint
        run: ./gradlew lint
      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: debug-apk
          path: app/build/outputs/apk/debug/
```

### Release Signing

| Config | Debug | Release |
|---|---|---|
| Keystore | Android Debug Key | `tripandevent-release.jks` (not in repo) |
| Minification | Disabled | Enabled with ProGuard |
| Version Code | Auto-increment | Manual via `build.gradle.kts` |
| Distribution | ADB / Sanbot direct | Play Store + OTA to kiosk fleet |

---

## License

Proprietary — TripAndEvent. All rights reserved.

---

<div align="center">

**Built for physical spaces. Engineered for reliability.**

`Kotlin` · `Android` · `Media3` · `Material 3` · `Sanbot`

</div>
