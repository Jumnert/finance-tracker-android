<div align="center">

<img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp" alt="MoneySavingTracker app icon" width="120" />

# MoneySavingTracker

**A modern Android expense tracker built with Kotlin, Jetpack Compose, Firebase Auth, and Cloud Firestore.**

Track income and expenses, sign in securely, verify accounts by email, and manage personal transaction history in a clean Compose-based UI.

<p>
  <img src="https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin badge" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose badge" />
  <img src="https://img.shields.io/badge/Firebase-Auth-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase Auth badge" />
  <img src="https://img.shields.io/badge/Firestore-Database-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firestore badge" />
  <img src="https://img.shields.io/badge/Material%203-Compose-1976D2?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material 3 badge" />
</p>

<p>
  <img src="https://img.shields.io/badge/Android-minSdk%2025-3DDC84?style=flat-square&logo=android&logoColor=white" alt="Android minSdk badge" />
  <img src="https://img.shields.io/badge/targetSdk-36-0A66C2?style=flat-square" alt="targetSdk badge" />
  <img src="https://img.shields.io/badge/Gradle-KTS-02303A?style=flat-square&logo=gradle&logoColor=white" alt="Gradle badge" />
  <img src="https://img.shields.io/badge/MVVM-StateFlow-orange?style=flat-square" alt="MVVM badge" />
</p>

</div>

<p align="center">
  <img src="app/src/main/res/img/mockup.png" alt="MoneySavingTracker app mockup" width="900" />
</p>

## Preview

| App | Stack |
|---|---|
| Personal finance dashboard | Kotlin + Jetpack Compose |
| Secure sign-in flow | Firebase Authentication |
| Realtime transaction sync | Cloud Firestore |
| Balance visibility + currency switch | MVVM + StateFlow |

## Why This Project Feels Nice

- Clean Compose navigation flow from login to dashboard
- Email/password auth, Google sign-in, reset-password flow, and email verification
- Per-user Firestore transaction storage
- Live transaction updates with `Flow`
- Simple balance masking for privacy
- USD and Khmer Riel display toggle

## Tech Stack

```text
Kotlin
Jetpack Compose
Material 3
Navigation Compose
ViewModel + StateFlow
Firebase Authentication
Cloud Firestore
Google Sign-In
Coil
Gradle Kotlin DSL
```

## Project Structure

```text
MoneySavingTracker/
├── app/
│   ├── src/main/java/com/example/moneysavingtracker/
│   │   ├── businesslogic/validation/
│   │   ├── data/repository/
│   │   ├── model/
│   │   ├── ui/screens/
│   │   ├── ui/theme/
│   │   ├── viewmodel/
│   │   └── MainActivity.kt
│   └── src/main/res/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew
```

## Architecture

The app follows a lightweight MVVM structure:

- `ui/screens/` handles Compose screens and reusable UI components
- `viewmodel/` manages UI state and user actions
- `data/repository/` talks to Firestore
- `model/` defines app data
- `businesslogic/validation/` contains auth validation rules

Data flow:

```text
Compose Screen
   -> ViewModel
   -> Repository
   -> Firestore / Firebase Auth
   -> Flow / StateFlow
   -> Compose recomposition
```

## Main Features

### Authentication

- Email/password sign-up and login
- Google sign-in
- Email verification gate before entering the dashboard
- Password reset flow

### Money Tracking

- Add income and expense transactions
- Edit and delete transactions
- Live Firestore-backed transaction list
- Running balance summary

### UX Details

- Balance can be hidden for privacy
- Currency display can switch between USD and KHR
- Friendly auth validation messages

## App Flow

### Auth Flow

```text
Register -> Verification Email -> Verify -> Main Dashboard
Login -> Main Dashboard
Forgot Password -> Reset Link -> Login
Google Sign-In -> Main Dashboard
```

### Firestore Path

```text
users/{uid}/transactions/{transactionId}
```

Stored transaction fields:

```json
{
  "title": "Lunch",
  "amount": 5.5,
  "date": "Mar 30, 2026",
  "category": "Food",
  "isIncome": false,
  "timestamp": "serverTimestamp()"
}
```

## Quick Start

### Prerequisites

- Android Studio
- JDK 11
- Android SDK 36
- A Firebase project
- Google Auth configured in Firebase

### Clone

```bash
git clone https://github.com/<your-username>/MoneySavingTracker.git
cd MoneySavingTracker
```

### Open

1. Open Android Studio
2. Choose `Open`
3. Select this project folder
4. Wait for Gradle sync

## Firebase Setup

### 1. Add the Android app in Firebase

Use this package name:

```text
com.example.moneysavingtracker
```

Download `google-services.json` and place it here:

```text
app/google-services.json
```

### 2. Enable authentication providers

Enable these in Firebase Authentication:

- Email/Password
- Google

### 3. Configure Google Sign-In

This project uses `default_web_client_id`, so make sure:

- `google-services.json` matches this Firebase project
- Your SHA-1 and SHA-256 fingerprints are registered
- Google sign-in is enabled in Firebase Auth

### 4. Enable Firestore

Create a Firestore database and use secure rules. A good starting point:

```text
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId}/transactions/{transactionId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
  }
}
```

## Run The App

### Android Studio

- Pick an emulator or physical device
- Press `Run`

### Gradle

```bash
./gradlew assembleDebug
./gradlew installDebug
```

## Useful Commands

```bash
./gradlew build
./gradlew test
./gradlew assembleDebug
./gradlew installDebug
```

## Key Files

- `app/src/main/java/com/example/moneysavingtracker/MainActivity.kt`
- `app/src/main/java/com/example/moneysavingtracker/ui/screens/NavGraph.kt`
- `app/src/main/java/com/example/moneysavingtracker/ui/screens/MainScreen.kt`
- `app/src/main/java/com/example/moneysavingtracker/viewmodel/AuthViewModel.kt`
- `app/src/main/java/com/example/moneysavingtracker/viewmodel/MainViewModel.kt`
- `app/src/main/java/com/example/moneysavingtracker/data/repository/TransactionRepository.kt`

## Notes

- Currency conversion is currently a simple fixed `1 USD = 4000 KHR`
- Email/password users must verify their email before entering the app
- Transaction dates are formatted when they are created

## Troubleshooting

### Google sign-in not working

- Confirm `app/google-services.json` exists
- Confirm Firebase Auth has Google enabled
- Confirm SHA keys were added in Firebase
- Confirm the emulator/device has Google Play services

### Firestore data not showing

- Make sure the user is authenticated
- Make sure Firestore is enabled
- Make sure rules allow access to `users/{uid}/transactions`

### Email verification still blocked

- Open the verification link from the inbox
- Return to the app and tap the verification button
- Make sure the app is connected to the correct Firebase project

## Future Ideas

- Charts and analytics
- Category filters
- Search
- Offline caching
- Better exchange-rate handling
- More tests
- Export or report support

## Contributing

```bash
git checkout -b feature/your-feature-name
git commit -m "Add your change"
git push origin feature/your-feature-name
```

Then open a pull request.

## License

Add your preferred open-source license here if you want to publish the project publicly.
