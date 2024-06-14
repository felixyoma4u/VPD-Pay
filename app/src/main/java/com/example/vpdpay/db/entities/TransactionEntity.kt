package com.example.vpdpay.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis()
)