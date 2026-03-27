## Project Identity

- Project name: MoneySavingTracker
- App type: Android mobile application
- Main purpose: Help users record income and expenses and monitor their balance
- Package name: `com.example.moneysavingtracker`
- App name shown in resources: `MoneySavingTracker`

## Core Objective

MoneySavingTracker was developed to help users manage personal finances in a simple and organized way. The app allows users to create an account, sign in securely, verify their email, recover a forgotten password, and manage transactions from a mobile dashboard.

## Main Features

- Email and password registration
- Email and password login
- Gmail email verification after sign-up
- Google sign-in
- Forgot password / reset password
- Add transaction
- Edit transaction
- Delete transaction
- View total balance
- Hide or show balance
- Switch displayed currency between US Dollar and Khmer Riel
- View recent transaction activity

## Authentication Details

- Firebase Authentication is used for account management
- Users can register with email and password
- After registration, the app sends a verification email
- Users must verify their email before full access is allowed
- The app also supports Google authentication
- Google sign-in uses `default_web_client_id`
- Password reset emails are sent through Firebase Authentication

## Firebase Details

- Firebase is used as the backend service
- Firebase Authentication handles login and account security
- Cloud Firestore stores transaction data
- `google-services.json` is placed in the `app` folder
- Google sign-in requires SHA-1 and SHA-256 fingerprints in Firebase Console

## Firestore Structure

The transaction data is stored per user. Each authenticated user has their own transaction records inside a subcollection.

Example structure:

```text
users
  -> userId
     -> transactions
        -> transactionId
```

Each transaction contains:

- `id`
- `title`
- `amount`
- `date`
- `category`
- `isIncome`

## Screens in the App

- Login screen
- Register screen
- Email verification screen
- Forgot password screen
- Main dashboard screen

## Main Technical Stack

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Cloud Firestore
- Android Studio
- Gradle
- GitHub

## Architecture and Logic

- The app uses ViewModels for state handling
- `AuthViewModel` manages login, register, Google sign-in, email verification, password reset, and logout
- `MainViewModel` manages transactions, total balance, visibility toggle, and currency toggle
- Validation logic is used for email, password, and transaction input

## Real Project Behavior

- If a user signs up with email and password, the app sends a verification email
- If the email is not verified, the user cannot continue into the main app
- If the user signs in with Google, Firebase verifies the Google credential
- The dashboard shows transaction activity and current balance
- Transactions are updated in Firestore and reflected in the UI

## Chapter Notes for the Report

### Introduction

The report should explain that the project focuses on secure authentication and personal finance tracking on Android.

### Project Overview

The app is intended for students, young adults, and working people who want a basic money tracking application.

### Key Features

The report should clearly describe how each authentication feature works and how the transaction system works.

### Technology Stack

The report should mention Kotlin, Jetpack Compose, Firebase Authentication, Firestore, Gradle, and Android Studio.

### Development Process

The report should explain that the project involved planning screens, building Compose UI, integrating Firebase, and validating user inputs.

### Implementation and Deployment

The report should explain Android setup, Firebase setup, `google-services.json`, and Git-based project management.

### Challenges and Solutions

Important challenges to mention:

- email verification control
- Google sign-in setup
- SHA fingerprint setup
- Firestore synchronization

### Testing

The report should mention testing for:

- registration
- login
- Gmail verification
- Google sign-in
- password reset
- transaction add/edit/delete

### Firebase Walkthrough

The report must include step-by-step Firebase integration:

- create Firebase project
- register Android app
- download `google-services.json`
- add dependencies
- enable authentication methods
- implement Gmail verification
- implement Google sign-in
- add SHA fingerprints
- test the authentication flow

### Results and Evaluation

The report should say the project successfully combines secure authentication and finance tracking in one Android app.

### Future Improvement

Possible future improvements:

- charts and reports
- category analytics
- savings goals
- offline support
- better filtering

## Short Summary Paragraph

MoneySavingTracker is an Android mobile application built with Kotlin and Jetpack Compose to help users manage personal income and expenses. The app integrates Firebase Authentication for secure login, Gmail verification, password reset, and Google sign-in. It uses Cloud Firestore to store user transactions in a structured way under each authenticated account. The project demonstrates practical mobile development, backend integration, and user-centered financial tracking features in one complete application.
