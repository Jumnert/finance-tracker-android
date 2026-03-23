package com.example.moneysavingtracker.data.model

data class Transaction(
    val id: Int,
    val amount: Double,
    val category: String,
    val description: String
)
