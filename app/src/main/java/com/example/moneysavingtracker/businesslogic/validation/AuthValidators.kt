package com.example.moneysavingtracker.businesslogic.validation

data class ValidationResult(
    val isValid: Boolean,
    val message: String? = null
)

object AuthValidators {
    fun validateRegister(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(false, "Please enter a username")
        }
        if (email.isBlank()) {
            return ValidationResult(false, "Please enter an email")
        }
        if (!isValidEmail(email)) {
            return ValidationResult(false, "Please enter a valid email")
        }
        if (password.isBlank()) {
            return ValidationResult(false, "Please enter a password")
        }
        if (password.length < 6) {
            return ValidationResult(false, "Password must be at least 6 characters")
        }
        if (password != confirmPassword) {
            return ValidationResult(false, "Passwords do not match")
        }
        return ValidationResult(true)
    }

    fun validateLogin(email: String, password: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Please enter an email")
        }
        if (!isValidEmail(email)) {
            return ValidationResult(false, "Please enter a valid email")
        }
        if (password.isBlank()) {
            return ValidationResult(false, "Please enter a password")
        }
        return ValidationResult(true)
    }

    fun validatePasswordReset(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Please enter an email")
        }
        if (!isValidEmail(email)) {
            return ValidationResult(false, "Please enter a valid email")
        }
        return ValidationResult(true)
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.matches(email.trim())
    }

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
}
