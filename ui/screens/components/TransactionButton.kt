package com.example.moneysavingtracker.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransactionButtons(
    onAddTransaction: (title: String, amount: Double, category: String, isIncome: Boolean) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var isIncome by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                isIncome = true
                showDialog = true
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add")
        }

        Button(
            onClick = {
                isIncome = false
                showDialog = true
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Remove")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Remove")
        }
    }

    AddTransactionDialog(
        showDialog = showDialog,
        isIncome = isIncome,
        onDismiss = { showDialog = false },
        onConfirm = { title, amount, category ->
            onAddTransaction(title, amount, category, isIncome)
        }
    )
}
