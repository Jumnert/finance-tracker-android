package com.example.moneysavingtracker.model

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,
    val category: String,
    val isIncome: Boolean
)
