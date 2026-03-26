package com.example.moneysavingtracker.ui.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneysavingtracker.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.example.moneysavingtracker.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val user by authViewModel.user.collectAsState()
    val startDestination = if (authViewModel.canAccessApp(user)) "main" else "login"
    val googleSignInClient = GoogleSignIn.getClient(
        context,
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    )
    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != Activity.RESULT_OK) {
            authViewModel.setError("Google sign-in was cancelled")
            return@rememberLauncherForActivityResult
        }

        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                .getResult(ApiException::class.java)
            val idToken = account.idToken

            if (idToken.isNullOrBlank()) {
                authViewModel.setError("Google sign-in failed. Missing ID token.")
            } else {
                authViewModel.signInWithGoogle(idToken) {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        } catch (e: ApiException) {
            authViewModel.setError(e.localizedMessage ?: "Google sign-in failed")
        }
    }

    fun launchGoogleSignIn() {
        googleSignInClient.signOut().addOnCompleteListener(context.findActivity()) {
            googleLauncher.launch(googleSignInClient.signInIntent)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate("register") },
                onForgotPasswordClick = { navController.navigate("forgot_password") },
                onGoogleLoginClick = ::launchGoogleSignIn,
                authViewModel = authViewModel
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    val email = authViewModel.user.value?.email.orEmpty()
                    navController.navigate("verify_email/${Uri.encode(email)}") {
                        popUpTo("login")
                    }
                },
                onLoginClick = { navController.popBackStack() },
                onGoogleSignUpClick = ::launchGoogleSignIn,
                authViewModel = authViewModel
            )
        }
        composable("verify_email/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email").orEmpty()

            EmailVerificationScreen(
                email = email,
                onVerified = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    authViewModel.logout {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                authViewModel = authViewModel
            )
        }
        composable("forgot_password") {
            ForgotScreen(
                onBackToLogin = { navController.popBackStack() },
                authViewModel = authViewModel
            )
        }
        composable("main") {
            MainScreen(
                authViewModel = authViewModel,
                onLogout = {
                    authViewModel.logout {
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}

private tailrec fun Context.findActivity(): Activity =
    when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> error("Unable to find Activity from context")
    }
