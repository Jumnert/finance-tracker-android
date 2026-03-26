package com.example.moneysavingtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneysavingtracker.data.model.Transaction
import com.example.moneysavingtracker.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val repository = TransactionRepository()

    val transactions: StateFlow<List<Transaction>> = repository.getTransactions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isBalanceVisible = MutableStateFlow(true)
    val isBalanceVisible = _isBalanceVisible.asStateFlow()

    private val _isCurrencyRiel = MutableStateFlow(false)
    val isCurrencyRiel = _isCurrencyRiel.asStateFlow()

    fun toggleBalanceVisibility() {
        _isBalanceVisible.value = !_isBalanceVisible.value
    }

    fun toggleCurrency() {
        _isCurrencyRiel.value = !_isCurrencyRiel.value
    }

    fun addTransaction(title: String, amount: Double, category: String, isIncome: Boolean) {
        viewModelScope.launch {
            val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())
            repository.addTransaction(Transaction("", title, amount, date, category, isIncome))
        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch { repository.updateTransaction(transaction) }
    }

    fun deleteTransaction(id: String) {
        viewModelScope.launch { repository.deleteTransaction(id) }
    }

    fun getTotalBalance(): Double {
        return transactions.value.sumOf { if (it.isIncome) it.amount else -it.amount }
    }
    
    fun formatBalance(amount: Double, isRiel: Boolean, isVisible: Boolean): String {
        if (!isVisible) return "*******"
        return if (isRiel) {
            val rielAmount = amount * 4000
            "${String.format("%,.0f", rielAmount)} ៛"
        } else {
            "$${String.format("%,.2f", amount)}"
        }
    }
}
