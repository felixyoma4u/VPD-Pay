package com.example.vpdpay.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vpdpay.db.dao.TransactionDao
import com.example.vpdpay.db.entities.TransferEntity

@Database(entities = [TransferEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "transaction_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}