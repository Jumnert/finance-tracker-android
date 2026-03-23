package com.example.moneysavingtracker.ui.screens.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun TransactionButtons() {
    var showDialog by remember { mutableStateOf(false) }
    var isAdd by remember { mutableStateOf(true) } // track which button was pressed

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Add Income button
        Button(
            onClick = {
                isAdd = true
                showDialog = true
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(4.dp), // less rounded
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add")
        }

        // Remove Expense button
        Button(
            onClick = {
                isAdd = false
                showDialog = true
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Remove")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Remove")
        }
    }

    // Show dialog when either button is clicked
    AddTransactionDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = { amount, currency, category, description ->
            if (isAdd) {
                // Handle Add Income
                println("Added $amount $currency to $category: $description")
            } else {
                // Handle Remove Expense
                println("Removed $amount $currency from $category: $description")
            }
        }
    )
}
