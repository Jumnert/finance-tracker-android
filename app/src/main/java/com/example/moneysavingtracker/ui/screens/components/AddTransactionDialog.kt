package com.example.moneysavingtracker.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneysavingtracker.businesslogic.validation.TransactionValidators

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionDialog(
    showDialog: Boolean,
    isIncome: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (title: String, amount: Double, category: String) -> Unit,
    initialTitle: String = "",
    initialAmount: String = "",
    initialCategory: String = "Food"
) {
    if (showDialog) {
        var title by remember { mutableStateOf(initialTitle) }
        var amountText by remember { mutableStateOf(initialAmount) }
        var category by remember { mutableStateOf(initialCategory) }
        var expanded by remember { mutableStateOf(false) }
        var validationMessage by remember { mutableStateOf<String?>(null) }
        
        val categories = if (isIncome) listOf("Salary", "Investment", "Gift", "Other") 
                         else listOf("Food", "Transport", "Entertainment", "Shopping", "Other")

        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    val validation = TransactionValidators.validateTransaction(
                        title = title,
                        amountText = amountText,
                        category = category
                    )

                    if (validation.isValid) {
                        val amount = amountText.toDouble()
                        onConfirm(title.trim(), amount, category)
                        onDismiss()
                    } else {
                        validationMessage = validation.message
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            },
            title = { Text(if (isIncome) "Income" else "Expense") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            validationMessage = null
                        },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    OutlinedTextField(
                        value = amountText,
                        onValueChange = {
                            amountText = it
                            validationMessage = null
                        },
                        label = { Text("Amount") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = category,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text("Category") },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    "Dropdown",
                                    Modifier.clickable { expanded = !expanded }
                                )
                            }
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat) },
                                    onClick = {
                                        category = cat
                                        validationMessage = null
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    validationMessage?.let { message ->
                        Text(
                            text = message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        )
    }
}
