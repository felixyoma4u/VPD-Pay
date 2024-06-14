package com.example.vpdpay.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vpdpay.db.entities.TransferEntity

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransferEntity)

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): LiveData<List<TransferEntity>>
}