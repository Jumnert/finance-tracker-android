package com.example.moneysavingtracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneysavingtracker.R
import com.example.moneysavingtracker.ui.screens.components.TransactionButtons
import com.example.moneysavingtracker.ui.theme.CardGradient1
import com.example.moneysavingtracker.ui.theme.MoneySavingTrackerTheme
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
data class Transaction(
    val title: String,
    val amount: String,
    val date: String,
    val isIncome: Boolean
)
@Composable
fun DemoScreen(
    onLogout: () -> Unit = {}
) {
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val currentDate = formatter.format(Date())
    var expanded by remember { mutableStateOf(false) }

    val transactions = List(5) {
        Transaction(
            title = "Sell iPad $it",
            amount = "+${(it + 1) * 50}$",
            date = currentDate,
            isIncome = true
        )
    }
    Scaffold(

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp),

        ) {
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
                        text = "Jumnert",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box (
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { expanded = true },
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.pfp),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize().border(4.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                expanded = false
                                onLogout()
                            }
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
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
                        Text(
                            text = "Total Balance",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically

                        ){
                            Text(
                                text = "$12,450.00",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.headlineMedium
                            )
                            IconButton(
                                onClick = {
                                    println("You can swap money")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Swap Money",
                                    tint = MaterialTheme.colorScheme.surface
                                )
                            }


                        }

                        Text(
                            text = "+$1,200 this month",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            // Right after your Total Balance Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                   ,
                horizontalArrangement = Arrangement.spacedBy(16 .dp) // spacing between buttons
            ) {
                TransactionButtons(
                    onAddTransaction = { title, amount, category, isIncome ->
                        // Add logic here to update transactions
                    }
                )
            }


            Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Activity",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium


                    )
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // 🔥 IMPORTANT
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionItem(transaction)
                }
            }






          }

    }
}

private fun RowScope.onAddExpense() {
    TODO("Not yet implemented")
}

private fun RowScope.onAddIncome() {
    TODO("Not yet implemented")
}
@Composable
fun TransactionItem(transaction: Transaction) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Card(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(transaction.title)
                    Text(transaction.date)
                }
            }

            Text(
                text = transaction.amount,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DemoScreenPreview() {
    MoneySavingTrackerTheme {
        DemoScreen()
    }
}
