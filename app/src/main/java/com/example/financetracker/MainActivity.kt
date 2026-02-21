package com.example.financetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.model.Transaction
import com.example.financetracker.ui.theme.FinanceTrackerTheme
import com.example.financetracker.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TransactionScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val transactions by viewModel.transactions.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                text = "Transactions",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(transactions) { transaction ->
            TransactionItem(transaction)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Column {
        Text(text = transaction.title, style = MaterialTheme.typography.titleLarge)
        Text(text = "$${transaction.amount}", style = MaterialTheme.typography.bodyMedium)
    }
}
