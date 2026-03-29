package com.example.moneysavingtracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneysavingtracker.model.Transaction
import com.example.moneysavingtracker.ui.screens.components.TransactionButtons
import com.example.moneysavingtracker.ui.screens.components.AddTransactionDialog
import com.example.moneysavingtracker.ui.screens.components.BalanceSummaryCard
import com.example.moneysavingtracker.ui.screens.components.DashboardHeader
import com.example.moneysavingtracker.ui.screens.components.SectionHeader
import com.example.moneysavingtracker.ui.theme.CardGradient1
import com.example.moneysavingtracker.viewmodel.AuthViewModel
import com.example.moneysavingtracker.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel(),
    onLogout: () -> Unit = {}
) {
    val transactions by viewModel.transactions.collectAsState()
    val isVisible by viewModel.isBalanceVisible.collectAsState()
    val isRiel by viewModel.isCurrencyRiel.collectAsState()
    val user by authViewModel.user.collectAsState()
    
    val totalBalance = transactions.sumOf { if (it.isIncome) it.amount else -it.amount }
    val displayName = user?.displayName
        ?.takeIf { it.isNotBlank() }
        ?: user?.email
            ?.substringBefore("@")
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        ?: "Friend"
    val photoUrl = user?.photoUrl

    var transactionToEdit by remember { mutableStateOf<Transaction?>(null) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp),
        ) {
            DashboardHeader(
                displayName = displayName,
                photoUrl = photoUrl,
                onLogout = onLogout
            )

            BalanceSummaryCard(
                balanceText = viewModel.formatBalance(totalBalance, isRiel, isVisible),
                footerText = if (isRiel) "Currency: Khmer Riel" else "Currency: US Dollar",
                brush = CardGradient1,
                onRefreshClick = { viewModel.toggleCurrency() },
                onVisibilityClick = { viewModel.toggleBalanceVisibility() },
                visibilityIcon = {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Lock else Icons.Default.Notifications,
                        contentDescription = "Toggle Visibility",
                        tint = Color.White
                    )
                }
            )

            // Add/Remove Buttons
            TransactionButtons(
                onAddTransaction = { title, amount, category, isIncome ->
                    viewModel.addTransaction(title, amount, category, isIncome)
                }
            )

            SectionHeader(title = "Activity", actionText = "View All")

            // Transactions List
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onEdit = { transactionToEdit = it },
                        onDelete = { viewModel.deleteTransaction(it.id) }
                    )
                }
            }
        }
    }

    transactionToEdit?.let { transaction ->
        AddTransactionDialog(
            showDialog = true,
            isIncome = transaction.isIncome,
            initialTitle = transaction.title,
            initialAmount = transaction.amount.toString(),
            initialCategory = transaction.category,
            onDismiss = { transactionToEdit = null },
            onConfirm = { title, amount, category ->
                viewModel.updateTransaction(
                    transaction.copy(
                        title = title,
                        amount = amount,
                        category = category
                    )
                )
                transactionToEdit = null
            }
        )
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    onEdit: (Transaction) -> Unit,
    onDelete: (Transaction) -> Unit
) {
    var itemMenuExpanded by remember { mutableStateOf(false) }

    val icon = when (transaction.category) {
        "Food" -> Icons.Default.ShoppingCart
        "Salary" -> Icons.Default.Star
        "Transport" -> Icons.Default.LocationOn
        "Entertainment" -> Icons.Default.PlayArrow
        "Shopping" -> Icons.Default.Add
        else -> Icons.Default.Check
    }

    val amountColor = if (transaction.isIncome) Color(0xFF4CAF50) else Color(0xFFF44336)
    val amountPrefix = if (transaction.isIncome) "+" else "-"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { itemMenuExpanded = true },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = transaction.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = transaction.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = "$amountPrefix$${String.format("%.2f", transaction.amount)}",
                color = amountColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            DropdownMenu(
                expanded = itemMenuExpanded,
                onDismissRequest = { itemMenuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = {
                        itemMenuExpanded = false
                        onEdit(transaction)
                    },
                    leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = {
                        itemMenuExpanded = false
                        onDelete(transaction)
                    },
                    leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red) }
                )
            }
        }
    }
}
