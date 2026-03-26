package com.example.moneysavingtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    
    private val _user = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private fun FirebaseUser?.isAllowedIntoApp(): Boolean {
        if (this == null) return false
        val usesPasswordAuth = providerData.any { it.providerId == "password" }
        return !usesPasswordAuth || isEmailVerified
    }

    fun canAccessApp(user: FirebaseUser?): Boolean = user.isAllowedIntoApp()

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                auth.currentUser?.reload()?.await()
                val currentUser = auth.currentUser

                if (currentUser.isAllowedIntoApp()) {
                    _user.value = currentUser
                    onSuccess()
                } else {
                    auth.signOut()
                    _user.value = null
                    _error.value = "Please verify your email before logging in."
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun register(username: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                val currentUser = auth.currentUser
                currentUser?.updateProfile(
                    userProfileChangeRequest {
                        displayName = username.trim()
                    }
                )?.await()
                currentUser?.sendEmailVerification()?.await()
                _user.value = auth.currentUser
                onSuccess()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential).await()
                auth.currentUser?.reload()?.await()
                val currentUser = auth.currentUser

                if (currentUser.isAllowedIntoApp()) {
                    _user.value = currentUser
                    onSuccess()
                } else {
                    _error.value = "Please verify your email before continuing."
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        auth.signOut()
        _user.value = null
        onSuccess()
    }

    fun resetPassword(email: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email).await()
                onSuccess()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun setError(message: String) {
        _error.value = message
    }

    fun resendEmailVerification(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.currentUser?.sendEmailVerification()?.await()
                onSuccess()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun refreshVerificationStatus(onVerified: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.currentUser?.reload()?.await()
                val currentUser = auth.currentUser
                _user.value = currentUser

                if (currentUser.isAllowedIntoApp()) {
                    onVerified()
                } else {
                    _error.value = "Your email is not verified yet."
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
