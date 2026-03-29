







package com.example.moneysavingtracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneysavingtracker.model.Transaction
import com.example.moneysavingtracker.ui.screens.components.BalanceSummaryCard
import com.example.moneysavingtracker.ui.screens.components.DashboardHeader
import com.example.moneysavingtracker.ui.screens.components.SectionHeader
import com.example.moneysavingtracker.ui.screens.components.TransactionButtons
import com.example.moneysavingtracker.ui.theme.CardGradient1
import com.example.moneysavingtracker.ui.theme.MoneySavingTrackerTheme

@Composable
fun DemoScreen(
    onLogout: () -> Unit = {}
) {
    val transactions = List(5) { index ->
        Transaction(
            id = index.toString(),
            title = "Sell iPad $index",
            amount = (index + 1) * 50.0,
            date = "Mar 26, 2026",
            category = "Salary",
            isIncome = true
        )
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            DashboardHeader(
                displayName = "Jumnert",
                onLogout = onLogout
            )

            BalanceSummaryCard(
                balanceText = "$12,450.00",
                footerText = "+$1,200 this month",
                brush = CardGradient1,
                onRefreshClick = {},
                onVisibilityClick = {},
                visibilityIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Toggle Visibility",
                        tint = Color.White
                    )
                }
            )

            TransactionButtons(
                onAddTransaction = { _, _, _, _ -> }
            )

            SectionHeader(
                title = "Activity",
                actionText = "View All"
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onEdit = {},
                        onDelete = {}
                    )
                }
            }
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
