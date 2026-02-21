package com.example.financetracker.repository

import com.example.financetracker.model.Transaction

class TransactionRepository {
    fun getTransactions(): List<Transaction> {
        return listOf(
            Transaction(1, "Groceries", 50.0),
            Transaction(2, "Rent", 1200.0),
            Transaction(3, "Salary", 5000.0)
        )
    }
}
