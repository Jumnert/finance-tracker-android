# Google Docs AI Prompt for MoneySavingTracker Report

Create a formal academic project report based on the following requirements.

The document title should be:
`MoneySavingTracker Report`

Write the report in clear, simple, academic English suitable for a university project submission.

Formatting requirements:
- Use a professional report structure
- Add a title page heading
- Add a table of contents
- Use chapter headings and subheadings
- Write in paragraph form
- Keep the tone formal and clean
- Make the report easy to read
- Do not use casual language
- Do not use emojis
- Do not make the writing too robotic

Project details:
- Project name: MoneySavingTracker
- Platform: Android mobile application
- Language: Kotlin
- UI framework: Jetpack Compose
- Backend: Firebase
- Database: Cloud Firestore
- Authentication: Firebase Authentication

The app is a personal finance tracker that helps users manage income and expenses. It supports account registration, login, Gmail email verification, Google sign-in, password reset, and transaction management. Users can add, edit, and delete transactions. The app also shows the current balance and allows users to switch between US Dollar and Khmer Riel display.

Important implementation details to include:
- The Android package name is `com.example.moneysavingtracker`
- The app uses Firebase Authentication for email/password login
- The app sends Gmail verification emails after registration
- The app checks whether the email is verified before allowing full access
- The app supports Google authentication using `default_web_client_id`
- The app stores user transactions in Firestore
- The Firestore structure stores transactions under each user
- The app uses ViewModels for authentication and transaction state
- The app uses a dashboard screen to show balance and transaction activity

Please write the report using the following structure:

1. Introduction to MoneySavingTracker
- Purpose of the Report
- Objective
- Scope

2. Project Overview
- App Concept and Purpose
- Target Users

3. Key Features
- User Authentication
- Gmail Email Verification
- Google Sign-In
- Password Reset
- Transaction Management
- Balance Display and Currency Toggle

4. Technology Stack
- Development Tools
- Programming Language and UI Framework
- Backend and Database Services
- Architecture and State Management
- Database Structure
- Project Flow

5. Development Process
- Planning and Design
- Frontend Development
- Backend Development
- Validation and Business Logic

6. Implementation and Deployment
- Environment Setup
- Firebase Configuration
- Git and Version Control

7. Challenges and Solutions
- Authentication and Verification
- Google Sign-In Configuration
- Data Synchronization and Firestore

8. Testing and Quality Assurance
- Functional Testing
- Usability Testing
- Performance Testing

9. Firebase Integration Walkthrough
- Introduction
- Creating a Firebase Project
- Registering the Android App
- Adding `google-services.json`
- Configuring Gradle
- Syncing the Project
- Enabling Authentication Methods
- Email and Password Authentication
- Gmail Verification
- Google Authentication Integration
- Adding SHA Fingerprints
- Testing the Authentication Flow
- Conclusion

10. Results and Evaluation
- Key Achievements
- Lessons Learned
- Future Improvement

11. Recommendations and Conclusion
- Recommendations
- Conclusion

12. References

13. Appendix

14. Plagiarism Report

Extra writing instructions:
- Make the content match a real Android finance tracking project
- Do not mention dating, matchmaking, chat, or any unrelated example project
- Keep the content aligned with MoneySavingTracker only
- Explain Firebase integration step by step
- Clearly explain Google authentication and Gmail verification
- Mention that Firestore stores each user’s transactions in their own subcollection
- Include practical explanations, not only definitions
- Make the final document sound like a real student project report

If possible, make the document around medium-to-long length so it feels complete enough for submission.
