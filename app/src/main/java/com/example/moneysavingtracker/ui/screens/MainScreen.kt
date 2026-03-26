package com.example.moneysavingtracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.moneysavingtracker.R
import com.example.moneysavingtracker.data.model.Transaction
import com.example.moneysavingtracker.ui.screens.components.TransactionButtons
import com.example.moneysavingtracker.ui.screens.components.AddTransactionDialog
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

    var profileExpanded by remember { mutableStateOf(false) }
    var transactionToEdit by remember { mutableStateOf<Transaction?>(null) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp),
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome back",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = displayName,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Box (
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { profileExpanded = true },
                    contentAlignment = Alignment.Center
                ){
                    if (photoUrl != null) {
                        AsyncImage(
                            model = photoUrl,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .border(4.dp, Color.White, CircleShape),
                            contentScale = ContentScale.Crop,
                            error = painterResource(id = R.drawable.pfp),
                            fallback = painterResource(id = R.drawable.pfp),
                            placeholder = painterResource(id = R.drawable.pfp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.pfp),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .border(4.dp, Color.White, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    DropdownMenu(
                        expanded = profileExpanded,
                        onDismissRequest = { profileExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                profileExpanded = false
                                onLogout()
                            },
                            leadingIcon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) }
                        )
                    }
                }
            }

            // Balance Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .background(
                            brush = CardGradient1,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total Balance",
                                color = Color.White.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Row {
                                IconButton(onClick = { viewModel.toggleCurrency() }) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "Switch Currency",
                                        tint = Color.White
                                    )
                                }
                                IconButton(onClick = { viewModel.toggleBalanceVisibility() }) {
                                    Icon(
                                        imageVector = if (isVisible) Icons.Default.Lock else Icons.Default.Notifications, // Using Notifications as a placeholder for 'Eye' if not available, usually visibility/visibility_off
                                        contentDescription = "Toggle Visibility",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                        
                        Text(
                            text = viewModel.formatBalance(totalBalance, isRiel, isVisible),
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = if (isRiel) "Currency: Khmer Riel" else "Currency: US Dollar",
                            color = Color.White.copy(alpha = 0.9f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Add/Remove Buttons
            TransactionButtons(
                onAddTransaction = { title, amount, category, isIncome ->
                    viewModel.addTransaction(title, amount, category, isIncome)
                }
            )

            // Activity Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Activity",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

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

    if (transactionToEdit != null) {
        AddTransactionDialog(
            showDialog = true,
            isIncome = transactionToEdit!!.isIncome,
            initialTitle = transactionToEdit!!.title,
            initialAmount = transactionToEdit!!.amount.toString(),
            initialCategory = transactionToEdit!!.category,
            onDismiss = { transactionToEdit = null },
            onConfirm = { title, amount, category ->
                viewModel.updateTransaction(
                    transactionToEdit!!.copy(
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
