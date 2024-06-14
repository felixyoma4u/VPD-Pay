package com.example.vpdpay.repositories

import androidx.lifecycle.LiveData
import com.example.vpdpay.db.dao.TransactionDao
import com.example.vpdpay.db.entities.TransferEntity

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions: LiveData<List<TransferEntity>> = transactionDao.getAllTransactions()

    suspend fun insert(transaction: TransferEntity) {
        transactionDao.insert(transaction)
    }

}