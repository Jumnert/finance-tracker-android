package com.example.moneysavingtracker.data.repository

import com.example.moneysavingtracker.data.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class TransactionRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getTransactionCollection() = auth.currentUser?.let {
        firestore.collection("users").document(it.uid).collection("transactions")
    }

    fun getTransactions(): Flow<List<Transaction>> = callbackFlow {
        val collection = getTransactionCollection()
        if (collection == null) {
            trySend(emptyList())
            return@callbackFlow
        }

        val subscription = collection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val transactions = snapshot.documents.mapNotNull { doc ->
                        Transaction(
                            id = doc.id,
                            title = doc.getString("title") ?: "",
                            amount = doc.getDouble("amount") ?: 0.0,
                            date = doc.getString("date") ?: "",
                            category = doc.getString("category") ?: "",
                            isIncome = doc.getBoolean("isIncome") ?: false
                        )
                    }
                    trySend(transactions)
                }
            }
        awaitClose { subscription.remove() }
    }

    suspend fun addTransaction(transaction: Transaction) {
        val collection = getTransactionCollection() ?: return
        collection.add(
            mapOf(
                "title" to transaction.title,
                "amount" to transaction.amount,
                "date" to transaction.date,
                "category" to transaction.category,
                "isIncome" to transaction.isIncome,
                "timestamp" to FieldValue.serverTimestamp()
            )
        ).await()
    }

    suspend fun updateTransaction(transaction: Transaction) {
        val collection = getTransactionCollection() ?: return
        collection.document(transaction.id).update(
            mapOf(
                "title" to transaction.title,
                "amount" to transaction.amount,
                "category" to transaction.category,
                "isIncome" to transaction.isIncome
            )
        ).await()
    }

    suspend fun deleteTransaction(id: String) {
        getTransactionCollection()?.document(id)?.delete()?.await()
    }
}
