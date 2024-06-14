package com.example.vpdpay.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vpdpay.repositories.TransactionRepository

class TransferViewModelFactory(private val repository: TransactionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransferViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}