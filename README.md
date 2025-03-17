

## Overview
A simple Android application that allows users to set alarms with a modern, intuitive interface. This project demonstrates Android development using Jetpack Compose, Material 3 design principles, and system integration with the device's alarm functionality.

## Features
- Set alarms with an intuitive time picker interface
- Modern UI with animations and visual feedback
- Integration with the device's native alarm system
- Clean, user-friendly design with a blue color scheme

## Screenshots
![image](https://github.com/user-attachments/assets/f05c449d-2a2c-4180-9277-83107a943e9f)     ![image](https://github.com/user-attachments/assets/aa35de18-54f5-4d9c-8553-26cca846b289)


## Technologies Used
- Kotlin
- Jetpack Compose
- Material 3 Design
- Android SDK
- Gradle

## Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- Android SDK version 24+
- Gradle 8.10+
- JDK 11+

## Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/yourusername/SimpleAlarmClockApp.git
cd SimpleAlarmClockApp
```

### Open and Build in Android Studio
1. Open Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the cloned repository and click "Open"
4. Wait for the project to sync and build
5. Connect an Android device or use the emulator

### Run the Application
- Click the "Run" button (green triangle) in Android Studio
- Select a deployment target (emulator or connected device)
- The app should install and launch automatically

## How to Use
1. Launch the Alarm Clock app
2. The main screen displays a time picker with the current time
3. Select your desired alarm time using the time picker
4. Tap the "SET ALARM" button to add the alarm to your device
5. The system alarm app will open to confirm your alarm settings

## Project Structure
```
app/
├── src/main/
│   ├── java/com/example/alarmclockapp/
│   │   ├── MainActivity.kt        # Main activity with alarm setting logic
│   │   └── ui/theme/              # Theme configuration
│   ├── res/
│   │   ├── drawable/              # App icons and resources
│   │   ├── values/
│   │   │   ├── colors.xml         # Color definitions
│   │   │   ├── strings.xml        # String resources
│   │   │   └── themes.xml         # App theme definitions
│   └── AndroidManifest.xml        # App configuration with alarm permissions
└── build.gradle.kts               # App module build configuration
```

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Learning Resources
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material 3 Design](https://m3.material.io/)
- [Kotlin Programming Language](https://kotlinlang.org/docs/home.html)
- [Android Developers Documentation](https://developer.android.com/docs)

## Permissions Used
- `com.android.alarm.permission.SET_ALARM` - Required to integrate with the system alarm functionality

## Acknowledgements
- Android Developers documentation and tutorials
- Material Design for UI inspiration
- Jetpack Compose samples for implementation patterns

---
*This app demonstrates modern Android development techniques using Jetpack Compose and Material 3 design principles while providing a practical utility for setting alarms.*
