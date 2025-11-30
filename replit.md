# TripAndEvent Sanbot Android App

## Overview
A native Android Kotlin application for the TripAndEvent Sanbot Interactive Assistant robot. The app provides a welcoming interface for customers to interact with the Sanbot robot at TripAndEvent locations.

## Project Architecture

### Technology Stack
- **Language**: Kotlin 1.9+
- **Platform**: Android (API 26+)
- **UI**: XML Layouts with Material Design Components
- **Video**: AndroidX Media3 ExoPlayer
- **Build System**: Gradle 8.4 with Kotlin DSL

### Project Structure
```
app/
├── src/main/
│   ├── java/com/tripandevent/sanbot/
│   │   ├── ui/
│   │   │   ├── welcome/WelcomeActivity.kt     # Welcome/Idle screen with ExoPlayer
│   │   │   └── menu/MainMenuActivity.kt       # Main menu navigation
│   │   └── util/                              # Utility classes
│   ├── res/
│   │   ├── layout/                            # XML layouts
│   │   ├── values/                            # Colors, strings, themes, dimens
│   │   ├── drawable/                          # Vector graphics, backgrounds
│   │   ├── anim/                              # Animation resources
│   │   └── raw/                               # Video assets (add background_video.mp4)
│   └── AndroidManifest.xml
└── build.gradle.kts

preview/                                        # Web-based app preview
├── index.html
├── css/style.css
└── js/app.js
```

### Design System
- **Primary Colors**: Brand Blue (#0066CC), Brand Orange (#FF6B35)
- **Secondary Colors**: Dark Gray (#2C3E50), Light Gray (#ECF0F1)
- **Accent Colors**: Success Green (#27AE60), Warning Yellow (#F39C12), Error Red (#E74C3C)
- **Typography**: Poppins font family (H1: 32sp, H2: 24sp, H3: 20sp, Body: 16sp, Button: 18sp)
- **Target Resolution**: 1920x1080 (Portrait)
- **Touch Targets**: 280x80dp for primary buttons with 16dp corner radius

## Current Features (MVP)
1. **Welcome Screen**: 
   - TripAndEvent logo with fade-in animation
   - Welcome text with slide-up animation
   - Subtitle with 70% opacity
   - Start button with pulse animation
   - ExoPlayer video background (falls back to gradient if no video)
   - Full-screen immersive mode
   
2. **Main Menu**: 
   - Grid of navigation options (Tours, Events, Information, Help)
   - Color-coded cards with hover/touch effects
   - Staggered entry animations
   - Back navigation to Welcome screen

3. **Animations**: 
   - Logo: 500ms fade-in
   - Text: 400ms slide-up with 300ms stagger
   - Button: Scale-in + continuous pulse effect

## Adding Video Background
To add a video background:
1. Place your video file in `app/src/main/res/raw/` as `background_video.mp4`
2. The ExoPlayer will automatically detect and play it in a loop
3. If no video is found, the gradient placeholder is displayed

## Building the Project
This is an Android project - build with Gradle and run on Android device/emulator.

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean build
```

The APK will be in `app/build/outputs/apk/debug/` or `app/build/outputs/release/`

## Preview
A web-based preview of the app design is available at port 5000 when the workflow is running.

## Recent Changes
- **Nov 30, 2025**: Initial project setup with Welcome and Main Menu screens
- **Nov 30, 2025**: Added ExoPlayer video background with fallback support
- **Nov 30, 2025**: Created web-based preview with interactive animations
