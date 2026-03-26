package com.example.moneysavingtracker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneysavingtracker.businesslogic.validation.AuthValidators
import com.example.moneysavingtracker.ui.screens.components.AuthScreenLayout
import com.example.moneysavingtracker.ui.screens.components.AuthTextField
import com.example.moneysavingtracker.viewmodel.AuthViewModel

@Composable
fun ForgotScreen(
    onBackToLogin: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val error by authViewModel.error.collectAsState()

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            authViewModel.clearError()
        }
    }

    AuthScreenLayout(
        title = "Find Your Account",
        subtitle = "Enter your email to receive a reset link"
    ) {
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                val validation = AuthValidators.validatePasswordReset(email)

                if (!validation.isValid) {
                    Toast.makeText(context, validation.message, Toast.LENGTH_SHORT).show()
                    return@Button
                }
                
                authViewModel.resetPassword(email) {
                    Toast.makeText(context, "Reset link sent to your email", Toast.LENGTH_SHORT).show()
                    onBackToLogin()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Send Reset Link")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Back to Login")
        }
    }
}
