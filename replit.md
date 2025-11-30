# TripAndEvent Sanbot Android App

## Overview
A native Android Kotlin application for the TripAndEvent Sanbot Interactive Assistant robot. The app provides a welcoming interface for customers to interact with the Sanbot robot at TripAndEvent locations.

## Project Architecture

### Technology Stack
- **Language**: Kotlin 1.9+
- **Platform**: Android (API 26+)
- **UI**: XML Layouts with Material Design Components
- **Video**: AndroidX Media3 ExoPlayer
- **Build System**: Gradle 8.1 with Kotlin DSL

### Project Structure
```
app/
├── src/main/
│   ├── java/com/tripandevent/sanbot/
│   │   ├── ui/
│   │   │   ├── welcome/WelcomeActivity.kt     # Welcome/Idle screen
│   │   │   └── menu/MainMenuActivity.kt       # Main menu navigation
│   │   └── util/                              # Utility classes
│   ├── res/
│   │   ├── layout/                            # XML layouts
│   │   ├── values/                            # Colors, strings, themes
│   │   ├── drawable/                          # Vector graphics, backgrounds
│   │   ├── anim/                              # Animation resources
│   │   └── raw/                               # Video assets (future)
│   └── AndroidManifest.xml
└── build.gradle.kts
```

### Design System
- **Primary Colors**: Brand Blue (#0066CC), Brand Orange (#FF6B35)
- **Typography**: Poppins font family (H1: 32sp, H2: 24sp, H3: 20sp, Body: 16sp)
- **Target Resolution**: 1920x1080 (Portrait)
- **Touch Targets**: 280x80dp for primary buttons

## Current Features (MVP)
1. **Welcome Screen**: Logo, welcome text, animated start button
2. **Main Menu**: Grid of navigation options (Tours, Events, Info, Help)
3. **Animations**: Fade-in, slide-up, pulse effects

## Future Enhancements
- ExoPlayer video background integration
- Sanbot SDK integration for robot features
- Voice interaction support
- Additional content screens

## Running the Project
This is an Android project - build with Gradle and run on Android device/emulator.

```bash
./gradlew assembleDebug
```

## Recent Changes
- **Nov 30, 2025**: Initial project setup with Welcome and Main Menu screens
