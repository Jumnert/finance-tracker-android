package com.example.moneysavingtracker.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddTransactionDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (amount: Double, currency: String, category: String, description: String) -> Unit
) {
    if (showDialog) {
        var amountText by remember { mutableStateOf("") }
        var currencyIsDollar by remember { mutableStateOf(true) }
        var category by remember { mutableStateOf("Food") }
        var description by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    val amount = amountText.toDoubleOrNull() ?: 0.0
                    val currency = if (currencyIsDollar) "USD" else "KHR"
                    onConfirm(amount, currency, category, description)
                    onDismiss()
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            },
            title = { Text("Add Transaction") },
            text = {
                Column {
                    // Currency switch
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = currencyIsDollar,
                            onCheckedChange = { currencyIsDollar = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (currencyIsDollar) "Dollar" else "Khmer Riel")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Amount input
                    OutlinedTextField(
                        value = amountText,
                        onValueChange = { amountText = it },
                        label = { Text("Amount") }
                    )

                    // Convert to letters (simplified example)
                    Text(
                        text = if (amountText.isNotEmpty())
                            "In words: ${amountText} ${if (currencyIsDollar) "USD" else "KHR"}"
                        else "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Category dropdown (simplified)
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("Category") }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Description
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") }
                    )
                }
            }
        )
    }
}
