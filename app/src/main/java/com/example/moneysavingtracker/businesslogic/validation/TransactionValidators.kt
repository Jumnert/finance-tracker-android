package com.example.moneysavingtracker.businesslogic.validation

object TransactionValidators {
    fun validateTransaction(
        title: String,
        amountText: String,
        category: String
    ): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(false, "Please enter a title")
        }
        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0.0) {
            return ValidationResult(false, "Please enter a valid amount")
        }
        if (category.isBlank()) {
            return ValidationResult(false, "Please choose a category")
        }
        return ValidationResult(true)
    }
}
