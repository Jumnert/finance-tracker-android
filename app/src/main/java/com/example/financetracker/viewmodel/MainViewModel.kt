package com.example.financetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.financetracker.model.Transaction
import com.example.financetracker.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val repository: TransactionRepository = TransactionRepository()) : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        _transactions.value = repository.getTransactions()
    }
}
