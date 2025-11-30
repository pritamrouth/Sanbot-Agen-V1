# TripAndEvent Sanbot Android App

## Overview
A native Android Kotlin application for the TripAndEvent Sanbot Interactive Assistant robot. The app provides a welcoming interface for customers to interact with the Sanbot robot at TripAndEvent locations.

## Project Architecture

### Technology Stack
- **Language**: Kotlin 1.9+
- **Platform**: Android (API 26+)
- **UI**: XML Layouts with Material Design 3 + Glassmorphism
- **Video**: AndroidX Media3 ExoPlayer
- **Build System**: Gradle 8.4 with Kotlin DSL
- **Design Pattern**: MVVM with ViewBinding

### Project Structure
```
app/
├── src/main/
│   ├── java/com/tripandevent/sanbot/
│   │   ├── ui/
│   │   │   ├── welcome/WelcomeActivity.kt
│   │   │   ├── menu/MainMenuActivity.kt
│   │   │   ├── voice/VoiceInteractionActivity.kt
│   │   │   ├── packages/PackageListActivity.kt & PackageDetailsActivity.kt
│   │   │   ├── contact/ContactFormActivity.kt & ThankYouActivity.kt
│   │   │   ├── media/MediaGalleryActivity.kt & VideoPlayerActivity.kt
│   │   │   ├── settings/SettingsActivity.kt
│   │   │   └── base/BaseFullScreenActivity.kt
│   │   ├── data/
│   │   │   ├── model/
│   │   │   │   ├── TravelPackage.kt
│   │   │   │   ├── MediaAsset.kt
│   │   │   │   ├── ConversationMessage.kt
│   │   │   │   └── ContactSubmission.kt
│   │   │   └── repository/
│   │   │       ├── PackageRepository.kt
│   │   │       └── MediaRepository.kt
│   ├── res/
│   │   ├── layout/ (10 screens + item layouts)
│   │   ├── drawable/ (Glassmorphic drawables, icons)
│   │   ├── values/ (colors, dimens, strings, themes)
│   ├── AndroidManifest.xml (all 10 activities registered)
│   └── build.gradle.kts

preview/ (Web-based interactive preview)
```

### Design System - Modern Glassmorphism
- **Light Theme**: Soft cyan gradient backgrounds (#E0F7FA → #B3E5FC)
- **Glassy Cards**: Semi-transparent with 1px light borders and blur effect
- **Primary Colors**: Blue (#0066CC), Orange (#FF6B35)
- **Text Color**: Navy (#1A237E) for minimalist readability
- **Typography**: Poppins font (H1: 32sp, H2: 24sp, H3: 20sp, Body: 16sp)
- **Touch Targets**: 64-80dp buttons with smooth hover states
- **Animations**: Subtle fade-in and scale transitions

### 10 Screens Implemented ✅

1. **Welcome Screen** - TripAndEvent branding, Start button with animations
2. **Main Menu** - 4 color-coded glassy cards for navigation
3. **Voice Interaction** - Central glassy circle with microphone, chat controls, voice display
4. **Package List** - Scrollable list of glassy package cards
5. **Package Details** - Hero image, expandable sections (highlights, itinerary, inclusions, exclusions)
6. **Contact Form** - Name, phone, email, notes inputs with validation
7. **Thank You Screen** - Success confirmation with SMS/WhatsApp/Email sharing buttons
8. **Media Gallery** - Videos and photos in glassy containers
9. **Video Player** - ExoPlayer with playback controls
10. **Settings** - PIN-protected (1234) API configuration and diagnostics

### Key Features
✅ **Full-Screen Immersive Mode** - Hides system bars for kiosk experience
✅ **2-Minute Inactivity Timer** - Auto-returns to Welcome screen
✅ **Glassmorphic UI** - Modern semi-transparent cards with backdrop blur
✅ **Light Theme** - Soft cyan/blue gradient backgrounds with navy text
✅ **Minimalist Design** - Removed assistant labels, clean layouts
✅ **ExoPlayer Integration** - Video backgrounds and player support
✅ **Parcelable Data Models** - Seamless intent-based navigation
✅ **Material Design 3** - Rounded corners, modern shadows
✅ **Responsive Layouts** - ConstraintLayout with adaptive sizing

### Base Activity Features
- Automatic 2-minute inactivity timeout
- Full-screen, edge-to-edge display
- Toolbar-less, immersive mode
- Swipe-to-show system bars
- Overridable inactivity behavior

### PIN for Settings
- **Test PIN**: 1234
- Replace with secure storage in production

### Building the Project
```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

### Recent Changes
- **Nov 30, 2025**: Initial project setup with all 10 screens
- **Nov 30, 2025**: Added ExoPlayer video background support
- **Nov 30, 2025**: Fixed duplicate ContactSubmission definitions
- **Nov 30, 2025**: Implemented glassmorphic design with light theme
- **Nov 30, 2025**: Removed "Your Assistant" text, updated to minimalist modern style
- **Nov 30, 2025**: Added glassy card styling to all screens
- **Nov 30, 2025**: Created web-based interactive preview

### Preview
Web preview available at port 5000 showing all screens with interactive navigation.

### API Integration Ready
- Voice transcription endpoints prepared
- Package management API structure defined
- CRM lead creation endpoints ready
- Customer action APIs (SMS, WhatsApp, Email) integrated
- See attached API contract for full specifications

### User Preferences
- Modern glassmorphic design with light theme
- Minimalist layouts without unnecessary text
- Light cyan/blue gradient backgrounds
- Semi-transparent glassy cards with subtle borders
- Navy blue text for readability
- Smooth animations and hover effects
