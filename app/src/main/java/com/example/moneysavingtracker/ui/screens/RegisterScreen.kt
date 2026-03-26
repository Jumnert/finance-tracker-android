package com.example.moneysavingtracker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneysavingtracker.businesslogic.validation.AuthValidators
import com.example.moneysavingtracker.ui.screens.components.AuthPasswordField
import com.example.moneysavingtracker.ui.screens.components.AuthScreenLayout
import com.example.moneysavingtracker.ui.screens.components.AuthTextField
import com.example.moneysavingtracker.ui.screens.components.GoogleAuthButton
import com.example.moneysavingtracker.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onGoogleSignUpClick: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val error by authViewModel.error.collectAsState()

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            authViewModel.clearError()
        }
    }

    AuthScreenLayout(title = "Create Account") {
        AuthTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthPasswordField(
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthPasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val validation = AuthValidators.validateRegister(
                    username = username,
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword
                )

                if (validation.isValid) {
                    authViewModel.register(username, email, password, onRegisterSuccess)
                } else {
                    Toast.makeText(context, validation.message, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GoogleAuthButton(
            text = "Sign up with Google",
            onClick = onGoogleSignUpClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account? ")
            TextButton(onClick = onLoginClick) {
                Text("Login", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
