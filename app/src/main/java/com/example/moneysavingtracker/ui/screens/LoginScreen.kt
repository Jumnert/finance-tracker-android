package com.example.moneysavingtracker.ui.screens

import android.R
import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneysavingtracker.ui.theme.MoneySavingTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onClick: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            
        ) {

            Text(
                text = "Welcome to Money Saving Tracker",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 22.sp
            )

            Text(
                text = "Login to start saving",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 12.sp
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 22.sp
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Gmail") }, // 👈 floating label
                    placeholder = { Text("Enter your email") } // 👈 hint text
                )
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 22.sp
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Gmail") }, // 👈 floating label
                    placeholder = { Text("Enter your email") } // 👈 hint text
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Text("Haven't create account?")
                    Text(" Register",
                        fontWeight = FontWeight.Medium)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier.width(150.dp),
                    onClick = onClick,

                    shape = MaterialTheme.shapes.medium) {
                    Text("Login")
                }
        }
    }}}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MoneySavingTrackerTheme {
        LoginScreen()
    }
}
