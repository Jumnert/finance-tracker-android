package com.example.moneysavingtracker.data.repository

import com.example.moneysavingtracker.data.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>> {
        // Placeholder for data source
        return flowOf(emptyList())
    }
}
