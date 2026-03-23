package com.example.moneysavingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneysavingtracker.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val transactions by viewModel.transactions.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(
                text = "Money Tracker",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            if (transactions.isEmpty()) {
                Text(text = "No transactions yet.")
            } else {
                LazyColumn {
                    items(transactions) { transaction ->
                        Text(text = "${transaction.category}: $${transaction.amount}")
                    }
                }
            }
        }
    }
}
