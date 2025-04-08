# Weather KMM App

A Kotlin Multiplatform Mobile (KMM) weather application targeting **Android** and **iOS**, featuring:

- Shared business logic in Kotlin (MVVM architecture)
- Native UI implementations on each platform (Jetpack Compose for Android, SwiftUI for iOS)
- Offline caching with SQLDelight
- Dependency injection via Koin

- ![Simulator Screenshot - iPhone 16 - 2025-04-08 at 17 34 39](https://github.com/user-attachments/assets/e401ce6a-d45f-4428-baba-29ada76cfca7)

- ![Simulator Screenshot - iPhone 16 - 2025-04-08 at 17 34 39](https://github.com/user-attachments/assets/b39e7fed-8669-4791-acd6-32a33561e07a)


  
This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

---

## 🚀 Features

- **Current Weather**: Displays temperature (°C/°F), weather description, UV index, humidity, and precipitation for a given location.
- **8‑Day Forecast**: Shows daily high/low temperatures (°C/°F), weather description, and date.
- **Offline Support**: Last fetched data is cached locally via SQLDelight and served when offline.
- **Reverse Geocoding**: Resolves latitude/longitude to a human-readable location name.
- **Pull‑to‑Refresh**: Manually refresh weather data.

---

## 🏗 Architecture

The project follows the **MVVM** pattern with a clean separation:

```
shared/                # KMM shared module
  ├── core/            # DI, utils, constants
  ├── data/            # Remote (Ktor) + Local (SQLDelight) data sources
  ├── domain/          # Models, repository interface, use cases
  └── presentation/    # ViewModels (shared business logic)

androidApp/            # Android UI (Jetpack Compose)
iosApp/                # iOS UI (SwiftUI)
```

- **ViewModel**: Shared Kotlin class exposes `StateFlow<WeatherUiState>`.
- **Repository**: Combines remote fetch and local cache, exposes a `Flow` of UI states.
- **UseCase**: Encapsulates business logic (optional layer).

---

## 🧰 Tech Stack & Libraries

### Dependency Injection
- **Koin**
  - `io.insert-koin:koin-core`
  - `io.insert-koin:koin-android`

### Date & Time
- **kotlinx-datetime**
- **kotlinx-serialization-json**

### Local Storage
- **SQLDelight**
  - `app.cash.sqldelight:native-driver`
  - `app.cash.sqldelight:android-driver`

### Networking
- **Ktor**
  - `io.ktor:ktor-client-core`
  - `io.ktor:ktor-client-content-negotiation`
  - `io.ktor:ktor-serialization-kotlinx-json`
  - `io.ktor:ktor-client-okhttp` (Android)
  - `io.ktor:ktor-client-darwin` (iOS)
  - `io.ktor:ktor-client-auth`
  - `io.ktor:ktor-client-logging`

### Coroutines & Flow
- **kotlinx-coroutines-core**
- **KMP Native Coroutines** (optional for Swift Combine interop)

### Architecture
- **MVVM** pattern
- **Kotlinx Serialization** for JSON parsing
- **Kotlinx DateTime** for date handling

---

## ⚙️ Getting Started

### Prerequisites

- **Android Studio** (Arctic Fox or later) with KMM plugin
- **Xcode** 13+ for iOS target
- **CocoaPods** (if using pods) or SPM for dependency management

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/weather-kmm-app.git
   ```
2. Open the project in Android Studio to build the shared and Android modules.
3. Open `iosApp/WeatherApp.xcworkspace` in Xcode.
4. Ensure the shared framework is embedded in the iOS target:
   - Add `shared.xcframework` to **Frameworks, Libraries, and Embedded Content**.
5. Build and run on Android emulator or iOS simulator.

---

## 📱 Running

- **Android**: Run `androidApp` module on an emulator or device.
- **iOS**: Run `iosApp` scheme in Xcode on simulator or device.

---

## 🤝 Contributing

Contributions are welcome! Please open issues or pull requests for enhancements or bug fixes.

---

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

