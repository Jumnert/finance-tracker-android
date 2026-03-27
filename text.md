# MoneySavingTracker Report

## Contents

- Chapter 1: Introduction to MoneySavingTracker
  - Purpose of the Report
  - 1.1 Objective
  - 1.2 Scope
- Chapter 2: Project Overview
  - 2.1 App Concept and Purpose
  - 2.2 Target Users
- Chapter 3: Key Features
  - 3.1 User Authentication
  - 3.2 Gmail Email Verification
  - 3.3 Google Sign-In
  - 3.4 Password Reset
  - 3.5 Transaction Management
  - 3.6 Balance Display and Currency Toggle
- Chapter 4: Technology Stack
  - 4.1 Development Tools
  - 4.2 Programming Language and UI Framework
  - 4.3 Backend and Database Services
  - 4.4 Architecture and State Management
  - 4.5 Database Structure
  - 4.6 Project Flow
- Chapter 5: Development Process
  - 5.1 Planning and Design
  - 5.2 Frontend Development
  - 5.3 Backend Development
  - 5.4 Validation and Business Logic
- Chapter 6: Implementation and Deployment
  - 6.1 Environment Setup
  - 6.2 Firebase Configuration
  - 6.3 Git and Version Control
- Chapter 7: Challenges and Solutions
  - 7.1 Authentication and Verification
  - 7.2 Google Sign-In Configuration
  - 7.3 Data Synchronization and Firestore
- Chapter 8: Testing and Quality Assurance
  - 8.1 Functional Testing
  - 8.2 Usability Testing
  - 8.3 Performance Testing
- Chapter 9: Firebase Integration Walkthrough
  - 9.1 Introduction
  - 9.2 Creating a Firebase Project
  - 9.3 Registering the Android App
  - 9.4 Adding `google-services.json`
  - 9.5 Configuring Gradle
  - 9.6 Syncing the Project
  - 9.7 Enabling Authentication Methods
  - 9.8 Email and Password Authentication
  - 9.9 Gmail Verification
  - 9.10 Google Authentication Integration
  - 9.11 Adding SHA Fingerprints
  - 9.12 Testing the Authentication Flow
  - 9.13 Conclusion
- Chapter 10: Results and Evaluation
  - 10.1 Key Achievements
  - 10.2 Lessons Learned
  - 10.3 Future Improvement
- Chapter 11: Recommendations and Conclusion
  - 11.1 Recommendations
  - 11.2 Conclusion
- References
- Appendix
- Plagiarism Report

## Chapter 1: Introduction to MoneySavingTracker

### Purpose of the Report

This report explains the purpose, design, implementation, and evaluation of the MoneySavingTracker mobile application. The project was developed as an Android application to help users manage personal income and expenses in a simple and organized way.

### 1.1 Objective

The main objective of MoneySavingTracker is to help users record transactions, monitor their current balance, and manage financial activity through a clean mobile interface. The application also aims to provide secure user authentication using Firebase.

Example code:

```kotlin
val appGoal = "Help users track income and expenses"
println(appGoal)
```

### 1.2 Scope

The scope of the project includes user registration, login, Gmail verification, Google authentication, password reset, and transaction management. It also includes real-time transaction storage using Firebase Firestore. The current version does not yet include analytics charts, export reports, or multi-device budgeting tools.

Example code:

```kotlin
val featuresInScope = listOf("Register", "Login", "Google Sign-In", "Email Verification", "Transactions")
```

## Chapter 2: Project Overview

### 2.1 App Concept and Purpose

MoneySavingTracker is a personal finance mobile application designed to help users keep track of their money. The app allows users to create an account, sign in securely, add income and expense records, and view their financial activity in one place.

Example code:

```kotlin
data class AppConcept(
    val name: String,
    val purpose: String
)

val project = AppConcept("MoneySavingTracker", "Track and manage personal finances")
```

### 2.2 Target Users

The target users of the application are students, young adults, and working professionals who want a simple tool to record daily spending and savings. The app is especially useful for users who need a basic and accessible finance tracker on mobile.

Example code:

```kotlin
val targetUsers = listOf("Students", "Young adults", "Workers")
```

## Chapter 3: Key Features

### 3.1 User Authentication

The application supports account creation and login using email and password through Firebase Authentication. This helps protect user data and gives each user a separate account.

Example code:

```kotlin
FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
```

### 3.2 Gmail Email Verification

After registration, the app sends a verification email to the user's Gmail account. The user must verify the email before full access is allowed. This improves security and prevents fake accounts.

Example code:

```kotlin
FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
```

### 3.3 Google Sign-In

The application also supports Google authentication. Users can log in or sign up quickly by selecting their Google account instead of manually entering an email and password.

Example code:

```kotlin
GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(context.getString(R.string.default_web_client_id))
    .requestEmail()
    .build()
```

### 3.4 Password Reset

If users forget their password, the app provides a reset-password feature. Firebase sends a reset link to the user's email so they can recover access to the account.

Example code:

```kotlin
FirebaseAuth.getInstance().sendPasswordResetEmail(email)
```

### 3.5 Transaction Management

Users can add, update, and delete transactions. Each transaction contains a title, amount, date, category, and transaction type. This allows the user to keep track of both income and expenses.

Example code:

```kotlin
data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,
    val category: String,
    val isIncome: Boolean
)
```

### 3.6 Balance Display and Currency Toggle

The main dashboard displays the current balance. The app also allows users to hide or show the balance and switch between US Dollar and Khmer Riel display.

Example code:

```kotlin
val balanceText = if (isRiel) "4000 ៛" else "$1.00"
```

## Chapter 4: Technology Stack

### 4.1 Development Tools

The project uses Android Studio for development, Gradle for build management, GitHub for version control, and Firebase Console for backend setup and authentication configuration.

Example code:

```kotlin
val tools = listOf("Android Studio", "Gradle", "GitHub", "Firebase Console")
```

### 4.2 Programming Language and UI Framework

The application is developed using Kotlin. Jetpack Compose is used to build the user interface in a modern and declarative style.

Example code:

```kotlin
val mainLanguage = "Kotlin"
val uiFramework = "Jetpack Compose"
```

### 4.3 Backend and Database Services

Firebase Authentication is used for user login and account security. Cloud Firestore is used to store and retrieve transaction records for each user.

Example code:

```kotlin
val auth = FirebaseAuth.getInstance()
val firestore = FirebaseFirestore.getInstance()
```

### 4.4 Architecture and State Management

The app follows a simple MVVM structure. ViewModels manage authentication state and transaction data, while Composable screens observe and display those states.

Example code:

```kotlin
class MainViewModel : ViewModel()
class AuthViewModel : ViewModel()
```

### 4.5 Database Structure

The Firestore database stores transaction data under each authenticated user. A simple structure is used where each user has a `transactions` subcollection.

Example structure:

```text
users
  └── userId
      └── transactions
          └── transactionId
```

### 4.6 Project Flow

The general app flow starts from registration or login, followed by email verification if needed. After successful authentication, the user enters the main dashboard, manages transactions, and can log out when finished.

Example code:

```kotlin
val appFlow = listOf("Register/Login", "Verify Email", "Open Dashboard", "Manage Transactions")
```

## Chapter 5: Development Process

### 5.1 Planning and Design

The development process started by identifying the main user needs: secure login, simple transaction input, and a dashboard for viewing balance and activity. Based on these requirements, screens were planned for login, registration, verification, forgot password, and the main transaction screen.

Example code:

```kotlin
val screens = listOf("Login", "Register", "Email Verification", "Forgot Password", "Main")
```

### 5.2 Frontend Development

Frontend development focused on building the user interface with Jetpack Compose. Screens were created for authentication and the dashboard, while reusable components were created for forms, buttons, and dialogs.

Example code:

```kotlin
@Composable
fun MainScreen() {
    Text("MoneySavingTracker")
}
```

### 5.3 Backend Development

Backend development focused on Firebase integration. Authentication logic was implemented with FirebaseAuth, while transaction data was stored in Firestore. Each signed-in user can only manage their own transactions.

Example code:

```kotlin
firestore.collection("users").document(uid).collection("transactions")
```

### 5.4 Validation and Business Logic

Validation logic was implemented to check email format, password rules, matching passwords, and valid transaction input. This helps reduce invalid data and improves the reliability of the application.

Example code:

```kotlin
if (password.length < 6) {
    println("Password must be at least 6 characters")
}
```

## Chapter 6: Implementation and Deployment

### 6.1 Environment Setup

The development environment includes Android Studio, a JDK, Gradle, and the Android SDK. Firebase dependencies were added to the Gradle files and the project was configured to use Google services.

Example code:

```kotlin
android {
    compileSdk = 36
}
```

### 6.2 Firebase Configuration

The Firebase project was connected to the app using `google-services.json`. The Android package name `com.example.moneysavingtracker` was registered in Firebase, and authentication methods were enabled in Firebase Console.

Example path:

```text
app/google-services.json
```

### 6.3 Git and Version Control

Git was used to track project progress and manage updates. GitHub can be used to share the source code with team members and instructors.

Example code:

```bash
git init
git add .
git commit -m "Initial MoneySavingTracker setup"
```

## Chapter 7: Challenges and Solutions

### 7.1 Authentication and Verification

One challenge was controlling access for users who register with email and password. This was solved by checking whether the email was verified before allowing full app access.

Example code:

```kotlin
val usesPasswordAuth = providerData.any { it.providerId == "password" }
return !usesPasswordAuth || isEmailVerified
```

### 7.2 Google Sign-In Configuration

Another challenge was configuring Google Sign-In correctly. This required Firebase setup, the generated `default_web_client_id`, and matching SHA fingerprints in Firebase Console.

Example code:

```kotlin
.requestIdToken(context.getString(R.string.default_web_client_id))
```

### 7.3 Data Synchronization and Firestore

Handling live transaction updates was another challenge. This was solved by using a Firestore snapshot listener, which updates the transaction list whenever data changes.

Example code:

```kotlin
collection.orderBy("timestamp", Query.Direction.DESCENDING)
```

## Chapter 8: Testing and Quality Assurance

### 8.1 Functional Testing

Functional testing was performed on registration, login, Google sign-in, email verification, password reset, and transaction CRUD operations. Each feature was tested to confirm the expected behavior.

Example code:

```kotlin
assert(isLoginSuccessful)
```

### 8.2 Usability Testing

Usability testing focused on whether users could understand the authentication flow and easily add or manage transactions. Feedback can be used to improve labels, button placement, and navigation clarity.

Example code:

```kotlin
val userFeedback = "The transaction screen is easy to use"
```

### 8.3 Performance Testing

Performance testing focused on app responsiveness, transaction loading speed, and the smoothness of navigation between screens.

Example code:

```kotlin
val loadTimeMs = 250
println("Load time: $loadTimeMs ms")
```

## Chapter 9: Firebase Integration Walkthrough

### 9.1 Introduction

Firebase was integrated into the application to provide authentication, cloud support, and user account management. In this project, Firebase is mainly used for user sign-up, login, Google authentication, and Gmail email verification.

### 9.2 Creating a Firebase Project

The first step is to create a new project in Firebase Console. After opening the Firebase website, a new project is created by entering the project name and completing the setup process. This project becomes the backend service connected to the Android application.

### 9.3 Registering the Android App

After creating the Firebase project, the Android application must be registered. In Firebase Console, the Android app option is selected and the package name of the application is entered. For this project, the package name is `com.example.moneysavingtracker`. This step is important because Firebase uses the package name to identify the correct application.

### 9.4 Adding `google-services.json`

Once the Android app is registered, Firebase provides a file called `google-services.json`. This file contains the configuration that links the mobile application with the Firebase project. The file is downloaded and placed inside the `app` folder of the Android project.

Example path:

```text
app/google-services.json
```

### 9.5 Configuring Gradle

The next step is to configure Gradle so the app can use Firebase services. The Google Services plugin is added and Firebase dependencies are included in the app-level Gradle file. In this project, Firebase Authentication, Firestore, and Google Play Services Authentication are used.

Example code:

```kotlin
plugins {
    id("com.google.gms.google-services")
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:34.11.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.android.gms:play-services-auth")
}
```

### 9.6 Syncing the Project

After adding the Firebase configuration file and dependencies, the project is synced with Gradle. This generates required resources such as `default_web_client_id`, which is later used by Google Sign-In. Without a successful sync, Firebase resources may not be recognized by the application.

### 9.7 Enabling Authentication Methods

Inside Firebase Console, the `Authentication` section is opened and the sign-in methods are enabled. For this project, two authentication methods are important:

- Email/Password authentication
- Google authentication

These methods allow users to either create an account with email and password or log in using their Google account.

### 9.8 Email and Password Authentication

Email and password authentication is used for normal account registration and login. When a user signs up, Firebase creates a new account with the provided email and password. During login, Firebase checks these credentials and grants access if they are correct.

Example code:

```kotlin
FirebaseAuth.getInstance()
    .createUserWithEmailAndPassword(email, password)
```

### 9.9 Gmail Verification

After a new account is created with email and password, a verification email is sent to the user's Gmail account. The user must open their inbox and click the verification link. This confirms that the email address is real and belongs to the user. The app can then check whether the user has verified the email before giving full access to the main features.

Example code:

```kotlin
FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
```

This Gmail verification step improves account security and helps reduce fake registrations.

### 9.10 Google Authentication Integration

Google authentication allows users to sign in quickly with their Google account. In the app, Google Sign-In is configured by requesting the user's email and ID token. The ID token is then passed to Firebase Authentication so Firebase can verify the user and complete the login process.

Example code:

```kotlin
GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(context.getString(R.string.default_web_client_id))
    .requestEmail()
    .build()
```

This generated client ID comes from Firebase configuration and is required for Google login to work correctly.

### 9.11 Adding SHA Fingerprints

For Google authentication to work on real devices and different development machines, SHA-1 and SHA-256 fingerprints must be added to Firebase Console. These fingerprints identify the app signing certificate. If the SHA values are missing, Google sign-in may fail even when the rest of the Firebase setup is correct.

### 9.12 Testing the Authentication Flow

After completing the integration, the authentication system should be tested step by step:

1. Run the application.
2. Register a new account using email and password.
3. Check the Gmail inbox for the verification email.
4. Click the email verification link.
5. Return to the app and confirm the account is verified.
6. Test login with the verified account.
7. Test Google sign-in using a Google account.

This testing process confirms that normal registration, Gmail verification, and Google authentication are all functioning correctly.

### 9.13 Conclusion

Firebase integration provides a secure and scalable backend foundation for the application. Through Firebase Authentication, the app supports normal login, Google sign-in, and Gmail verification. These features improve the user experience while also increasing account security and reliability.

## Chapter 10: Results and Evaluation

### 10.1 Key Achievements

The project successfully implements a working Android finance tracker with secure authentication, Firestore-based transaction storage, and a user-friendly dashboard for daily money management.

Example code:

```kotlin
val achievements = listOf("Firebase Auth", "Google Sign-In", "Email Verification", "Transaction CRUD")
```

### 10.2 Lessons Learned

The project helped strengthen understanding of Jetpack Compose, Firebase integration, MVVM state handling, and real-time Firestore updates. It also showed the importance of correct Firebase configuration, especially for Google authentication.

Example code:

```kotlin
val lesson = "Correct Firebase setup is essential for authentication features"
```

### 10.3 Future Improvement

Future versions of the application can include monthly reports, charts, category analytics, savings goals, receipt attachments, and better filtering of transaction history.

Example code:

```kotlin
val futureFeatures = listOf("Charts", "Savings Goals", "Monthly Reports", "Advanced Filters")
```

## Chapter 11: Recommendations and Conclusion

### 11.1 Recommendations

It is recommended to improve Firestore security rules, add more visual reports, support offline caching, and introduce a more advanced dashboard for financial insights.

Example code:

```kotlin
val recommendation = "Add charts and stronger Firestore security rules"
```

### 11.2 Conclusion

MoneySavingTracker is a practical mobile application that helps users manage income and expenses with secure authentication and cloud-based transaction storage. The project demonstrates the successful integration of Android development, Jetpack Compose, Firebase Authentication, Firestore, Gmail verification, and Google Sign-In in one complete application.

Example code:

```kotlin
println("MoneySavingTracker project completed successfully")
```

## References

- Firebase Documentation
- Android Developer Documentation
- Kotlin Documentation
- Jetpack Compose Documentation
- Google Sign-In for Android Documentation

Example code:

```kotlin
val references = listOf("Firebase", "Android Docs", "Kotlin Docs", "Compose Docs")
```

## Appendix

The appendix may include screenshots of the login screen, register screen, email verification screen, forgot password screen, and main transaction dashboard.

Example code:

```kotlin
val appendixItems = listOf("Login Screen", "Register Screen", "Dashboard", "Firebase Setup Screenshots")
```

## Plagiarism Report

The plagiarism report section can be attached separately if required by the school or institution.

Example code:

```kotlin
val plagiarismStatus = "Attached separately"
```

# MoneySavingTracker Report

## Contents

- Chapter 1: Introduction to MoneySavingTracker
  - Purpose of the Report
  - 1.1 Objective
  - 1.2 Scope
- Chapter 2: Project Overview
  - 2.1 App Concept and Purpose
  - 2.2 Target Users
- Chapter 3: Key Features
  - 3.1 User Authentication
  - 3.2 Gmail Email Verification
  - 3.3 Google Sign-In
  - 3.4 Password Reset
  - 3.5 Transaction Management
  - 3.6 Balance Display and Currency Toggle
- Chapter 4: Technology Stack
  - 4.1 Development Tools
  - 4.2 Programming Language and UI Framework
  - 4.3 Backend and Database Services
  - 4.4 Architecture and State Management
  - 4.5 Database Structure
  - 4.6 Project Flow
- Chapter 5: Development Process
  - 5.1 Planning and Design
  - 5.2 Frontend Development
  - 5.3 Backend Development
  - 5.4 Validation and Business Logic
- Chapter 6: Implementation and Deployment
  - 6.1 Environment Setup
  - 6.2 Firebase Configuration
  - 6.3 Git and Version Control
- Chapter 7: Challenges and Solutions
  - 7.1 Authentication and Verification
  - 7.2 Google Sign-In Configuration
  - 7.3 Data Synchronization and Firestore
- Chapter 8: Testing and Quality Assurance
  - 8.1 Functional Testing
  - 8.2 Usability Testing
  - 8.3 Performance Testing
- Chapter 9: Firebase Integration Walkthrough
  - 9.1 Introduction
  - 9.2 Creating a Firebase Project
  - 9.3 Registering the Android App
  - 9.4 Adding `google-services.json`
  - 9.5 Configuring Gradle
  - 9.6 Syncing the Project
  - 9.7 Enabling Authentication Methods
  - 9.8 Email and Password Authentication
  - 9.9 Gmail Verification
  - 9.10 Google Authentication Integration
  - 9.11 Adding SHA Fingerprints
  - 9.12 Testing the Authentication Flow
  - 9.13 Conclusion
- Chapter 10: Results and Evaluation
  - 10.1 Key Achievements
  - 10.2 Lessons Learned
  - 10.3 Future Improvement
- Chapter 11: Recommendations and Conclusion
  - 11.1 Recommendations
  - 11.2 Conclusion
- References
- Appendix
- Plagiarism Report

## Chapter 1: Introduction to MoneySavingTracker

### Purpose of the Report

This report explains the purpose, design, implementation, and evaluation of the MoneySavingTracker mobile application. The project was developed as an Android application to help users manage personal income and expenses in a simple and organized way.

### 1.1 Objective

The main objective of MoneySavingTracker is to help users record transactions, monitor their current balance, and manage financial activity through a clean mobile interface. The application also aims to provide secure user authentication using Firebase.

Example code:

```kotlin
val appGoal = "Help users track income and expenses"
println(appGoal)
```

### 1.2 Scope

The scope of the project includes user registration, login, Gmail verification, Google authentication, password reset, and transaction management. It also includes real-time transaction storage using Firebase Firestore. The current version does not yet include analytics charts, export reports, or multi-device budgeting tools.

Example code:

```kotlin
val featuresInScope = listOf("Register", "Login", "Google Sign-In", "Email Verification", "Transactions")
```

## Chapter 2: Project Overview

### 2.1 App Concept and Purpose

MoneySavingTracker is a personal finance mobile application designed to help users keep track of their money. The app allows users to create an account, sign in securely, add income and expense records, and view their financial activity in one place.

Example code:

```kotlin
data class AppConcept(
    val name: String,
    val purpose: String
)

val project = AppConcept("MoneySavingTracker", "Track and manage personal finances")
```

### 2.2 Target Users

The target users of the application are students, young adults, and working professionals who want a simple tool to record daily spending and savings. The app is especially useful for users who need a basic and accessible finance tracker on mobile.

Example code:

```kotlin
val targetUsers = listOf("Students", "Young adults", "Workers")
```

## Chapter 3: Key Features

### 3.1 User Authentication

The application supports account creation and login using email and password through Firebase Authentication. This helps protect user data and gives each user a separate account.

Example code:

```kotlin
FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
```

### 3.2 Gmail Email Verification

After registration, the app sends a verification email to the user's Gmail account. The user must verify the email before full access is allowed. This improves security and prevents fake accounts.

Example code:

```kotlin
FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
```

### 3.3 Google Sign-In

The application also supports Google authentication. Users can log in or sign up quickly by selecting their Google account instead of manually entering an email and password.

Example code:

```kotlin
GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(context.getString(R.string.default_web_client_id))
    .requestEmail()
    .build()
```

### 3.4 Password Reset

If users forget their password, the app provides a reset-password feature. Firebase sends a reset link to the user's email so they can recover access to the account.

Example code:

```kotlin
FirebaseAuth.getInstance().sendPasswordResetEmail(email)
```

### 3.5 Transaction Management

Users can add, update, and delete transactions. Each transaction contains a title, amount, date, category, and transaction type. This allows the user to keep track of both income and expenses.

Example code:

```kotlin
data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,
    val category: String,
    val isIncome: Boolean
)
```

### 3.6 Balance Display and Currency Toggle

The main dashboard displays the current balance. The app also allows users to hide or show the balance and switch between US Dollar and Khmer Riel display.

Example code:

```kotlin
val balanceText = if (isRiel) "4000 ៛" else "$1.00"
```

## Chapter 4: Technology Stack

### 4.1 Development Tools

The project uses Android Studio for development, Gradle for build management, GitHub for version control, and Firebase Console for backend setup and authentication configuration.

Example code:

```kotlin
val tools = listOf("Android Studio", "Gradle", "GitHub", "Firebase Console")
```

### 4.2 Programming Language and UI Framework

The application is developed using Kotlin. Jetpack Compose is used to build the user interface in a modern and declarative style.

Example code:

```kotlin
val mainLanguage = "Kotlin"
val uiFramework = "Jetpack Compose"
```

### 4.3 Backend and Database Services

Firebase Authentication is used for user login and account security. Cloud Firestore is used to store and retrieve transaction records for each user.

Example code:

```kotlin
val auth = FirebaseAuth.getInstance()
val firestore = FirebaseFirestore.getInstance()
```

### 4.4 Architecture and State Management

The app follows a simple MVVM structure. ViewModels manage authentication state and transaction data, while Composable screens observe and display those states.

Example code:

```kotlin
class MainViewModel : ViewModel()
class AuthViewModel : ViewModel()
```

### 4.5 Database Structure

The Firestore database stores transaction data under each authenticated user. A simple structure is used where each user has a `transactions` subcollection.

Example structure:

```text
users
  └── userId
      └── transactions
          └── transactionId
```

### 4.6 Project Flow

The general app flow starts from registration or login, followed by email verification if needed. After successful authentication, the user enters the main dashboard, manages transactions, and can log out when finished.

Example code:

```kotlin
val appFlow = listOf("Register/Login", "Verify Email", "Open Dashboard", "Manage Transactions")
```

## Chapter 5: Development Process

### 5.1 Planning and Design

The development process started by identifying the main user needs: secure login, simple transaction input, and a dashboard for viewing balance and activity. Based on these requirements, screens were planned for login, registration, verification, forgot password, and the main transaction screen.

Example code:

```kotlin
val screens = listOf("Login", "Register", "Email Verification", "Forgot Password", "Main")
```

### 5.2 Frontend Development

Frontend development focused on building the user interface with Jetpack Compose. Screens were created for authentication and the dashboard, while reusable components were created for forms, buttons, and dialogs.

Example code:

```kotlin
@Composable
fun MainScreen() {
    Text("MoneySavingTracker")
}
```

### 5.3 Backend Development

Backend development focused on Firebase integration. Authentication logic was implemented with FirebaseAuth, while transaction data was stored in Firestore. Each signed-in user can only manage their own transactions.

Example code:

```kotlin
firestore.collection("users").document(uid).collection("transactions")
```

### 5.4 Validation and Business Logic

Validation logic was implemented to check email format, password rules, matching passwords, and valid transaction input. This helps reduce invalid data and improves the reliability of the application.

Example code:

```kotlin
if (password.length < 6) {
    println("Password must be at least 6 characters")
}
```

## Chapter 6: Implementation and Deployment

### 6.1 Environment Setup

The development environment includes Android Studio, a JDK, Gradle, and the Android SDK. Firebase dependencies were added to the Gradle files and the project was configured to use Google services.

Example code:

```kotlin
android {
    compileSdk = 36
}
```

### 6.2 Firebase Configuration

The Firebase project was connected to the app using `google-services.json`. The Android package name `com.example.moneysavingtracker` was registered in Firebase, and authentication methods were enabled in Firebase Console.

Example path:

```text
app/google-services.json
```

### 6.3 Git and Version Control

Git was used to track project progress and manage updates. GitHub can be used to share the source code with team members and instructors.

Example code:

```bash
git init
git add .
git commit -m "Initial MoneySavingTracker setup"
```

## Chapter 7: Challenges and Solutions

### 7.1 Authentication and Verification

One challenge was controlling access for users who register with email and password. This was solved by checking whether the email was verified before allowing full app access.

Example code:

```kotlin
val usesPasswordAuth = providerData.any { it.providerId == "password" }
return !usesPasswordAuth || isEmailVerified
```

### 7.2 Google Sign-In Configuration

Another challenge was configuring Google Sign-In correctly. This required Firebase setup, the generated `default_web_client_id`, and matching SHA fingerprints in Firebase Console.

Example code:

```kotlin
.requestIdToken(context.getString(R.string.default_web_client_id))
```

### 7.3 Data Synchronization and Firestore

Handling live transaction updates was another challenge. This was solved by using a Firestore snapshot listener, which updates the transaction list whenever data changes.

Example code:

```kotlin
collection.orderBy("timestamp", Query.Direction.DESCENDING)
```

## Chapter 8: Testing and Quality Assurance

### 8.1 Functional Testing

Functional testing was performed on registration, login, Google sign-in, email verification, password reset, and transaction CRUD operations. Each feature was tested to confirm the expected behavior.

Example code:

```kotlin
assert(isLoginSuccessful)
```

### 8.2 Usability Testing

Usability testing focused on whether users could understand the authentication flow and easily add or manage transactions. Feedback can be used to improve labels, button placement, and navigation clarity.

Example code:

```kotlin
val userFeedback = "The transaction screen is easy to use"
```

### 8.3 Performance Testing

Performance testing focused on app responsiveness, transaction loading speed, and the smoothness of navigation between screens.

Example code:

```kotlin
val loadTimeMs = 250
println("Load time: $loadTimeMs ms")
```

## Chapter 9: Firebase Integration Walkthrough

### 9.1 Introduction

Firebase was integrated into the application to provide authentication, cloud support, and user account management. In this project, Firebase is mainly used for user sign-up, login, Google authentication, and Gmail email verification.

### 9.2 Creating a Firebase Project

The first step is to create a new project in Firebase Console. After opening the Firebase website, a new project is created by entering the project name and completing the setup process. This project becomes the backend service connected to the Android application.

### 9.3 Registering the Android App

After creating the Firebase project, the Android application must be registered. In Firebase Console, the Android app option is selected and the package name of the application is entered. For this project, the package name is `com.example.moneysavingtracker`. This step is important because Firebase uses the package name to identify the correct application.

### 9.4 Adding `google-services.json`

Once the Android app is registered, Firebase provides a file called `google-services.json`. This file contains the configuration that links the mobile application with the Firebase project. The file is downloaded and placed inside the `app` folder of the Android project.

Example path:

```text
app/google-services.json
```

### 9.5 Configuring Gradle

The next step is to configure Gradle so the app can use Firebase services. The Google Services plugin is added and Firebase dependencies are included in the app-level Gradle file. In this project, Firebase Authentication, Firestore, and Google Play Services Authentication are used.

Example code:

```kotlin
plugins {
    id("com.google.gms.google-services")
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:34.11.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.android.gms:play-services-auth")
}
```

### 9.6 Syncing the Project

After adding the Firebase configuration file and dependencies, the project is synced with Gradle. This generates required resources such as `default_web_client_id`, which is later used by Google Sign-In. Without a successful sync, Firebase resources may not be recognized by the application.

### 9.7 Enabling Authentication Methods

Inside Firebase Console, the `Authentication` section is opened and the sign-in methods are enabled. For this project, two authentication methods are important:

- Email/Password authentication
- Google authentication

These methods allow users to either create an account with email and password or log in using their Google account.

### 9.8 Email and Password Authentication

Email and password authentication is used for normal account registration and login. When a user signs up, Firebase creates a new account with the provided email and password. During login, Firebase checks these credentials and grants access if they are correct.

Example code:

```kotlin
FirebaseAuth.getInstance()
    .createUserWithEmailAndPassword(email, password)
```

### 9.9 Gmail Verification

After a new account is created with email and password, a verification email is sent to the user's Gmail account. The user must open their inbox and click the verification link. This confirms that the email address is real and belongs to the user. The app can then check whether the user has verified the email before giving full access to the main features.

Example code:

```kotlin
FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
```

This Gmail verification step improves account security and helps reduce fake registrations.

### 9.10 Google Authentication Integration

Google authentication allows users to sign in quickly with their Google account. In the app, Google Sign-In is configured by requesting the user's email and ID token. The ID token is then passed to Firebase Authentication so Firebase can verify the user and complete the login process.

Example code:

```kotlin
GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(context.getString(R.string.default_web_client_id))
    .requestEmail()
    .build()
```

This generated client ID comes from Firebase configuration and is required for Google login to work correctly.

### 9.11 Adding SHA Fingerprints

For Google authentication to work on real devices and different development machines, SHA-1 and SHA-256 fingerprints must be added to Firebase Console. These fingerprints identify the app signing certificate. If the SHA values are missing, Google sign-in may fail even when the rest of the Firebase setup is correct.

### 9.12 Testing the Authentication Flow

After completing the integration, the authentication system should be tested step by step:

1. Run the application.
2. Register a new account using email and password.
3. Check the Gmail inbox for the verification email.
4. Click the email verification link.
5. Return to the app and confirm the account is verified.
6. Test login with the verified account.
7. Test Google sign-in using a Google account.

This testing process confirms that normal registration, Gmail verification, and Google authentication are all functioning correctly.

### 9.13 Conclusion

Firebase integration provides a secure and scalable backend foundation for the application. Through Firebase Authentication, the app supports normal login, Google sign-in, and Gmail verification. These features improve the user experience while also increasing account security and reliability.

## Chapter 10: Results and Evaluation

### 10.1 Key Achievements

The project successfully implements a working Android finance tracker with secure authentication, Firestore-based transaction storage, and a user-friendly dashboard for daily money management.

Example code:

```kotlin
val achievements = listOf("Firebase Auth", "Google Sign-In", "Email Verification", "Transaction CRUD")
```

### 10.2 Lessons Learned

The project helped strengthen understanding of Jetpack Compose, Firebase integration, MVVM state handling, and real-time Firestore updates. It also showed the importance of correct Firebase configuration, especially for Google authentication.

Example code:

```kotlin
val lesson = "Correct Firebase setup is essential for authentication features"
```

### 10.3 Future Improvement

Future versions of the application can include monthly reports, charts, category analytics, savings goals, receipt attachments, and better filtering of transaction history.

Example code:

```kotlin
val futureFeatures = listOf("Charts", "Savings Goals", "Monthly Reports", "Advanced Filters")
```

## Chapter 11: Recommendations and Conclusion

### 11.1 Recommendations

It is recommended to improve Firestore security rules, add more visual reports, support offline caching, and introduce a more advanced dashboard for financial insights.

Example code:

```kotlin
val recommendation = "Add charts and stronger Firestore security rules"
```

### 11.2 Conclusion

MoneySavingTracker is a practical mobile application that helps users manage income and expenses with secure authentication and cloud-based transaction storage. The project demonstrates the successful integration of Android development, Jetpack Compose, Firebase Authentication, Firestore, Gmail verification, and Google Sign-In in one complete application.

Example code:

```kotlin
println("MoneySavingTracker project completed successfully")
```

## References

- Firebase Documentation
- Android Developer Documentation
- Kotlin Documentation
- Jetpack Compose Documentation
- Google Sign-In for Android Documentation

Example code:

```kotlin
val references = listOf("Firebase", "Android Docs", "Kotlin Docs", "Compose Docs")
```

## Appendix

The appendix may include screenshots of the login screen, register screen, email verification screen, forgot password screen, and main transaction dashboard.

Example code:

```kotlin
val appendixItems = listOf("Login Screen", "Register Screen", "Dashboard", "Firebase Setup Screenshots")
```

## Plagiarism Report

The plagiarism report section can be attached separately if required by the school or institution.

Example code:

```kotlin
val plagiarismStatus = "Attached separately"
```
