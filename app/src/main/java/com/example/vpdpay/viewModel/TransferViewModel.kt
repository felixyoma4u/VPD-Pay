package com.example.vpdpay.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vpdpay.db.entities.TransferEntity
import com.example.vpdpay.model.TransferDetails
import com.example.vpdpay.repositories.TransactionRepository
import com.example.vpdpay.utils.userAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TransferViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    private val _transfer = MutableLiveData<String>()
    val transfer: LiveData<String> = _transfer

    // LiveData to trigger the confirmation dialog
    private val _showConfirmation = MutableLiveData<TransferDetails?>()
    val showConfirmation: LiveData<TransferDetails?> = _showConfirmation

    fun performTransfer(fromAccountNum: String, toAccountNum: String, amount: Double) {
        val fromAccount = userAccount.find { it.accountNumber.toString() == fromAccountNum }
        val toAccount = userAccount.find { it.accountNumber.toString() == toAccountNum }

        if (fromAccount != null && toAccount != null && amount > 0) {
            // Trigger confirmation dialog
            _showConfirmation.value = TransferDetails(fromAccount, toAccount, amount)
        } else {
            _transfer.value = "Invalid transfer details."
        }
    }

    fun confirmTransfer(transferDetails: TransferDetails) {
        // Perform the actual transfer logic here
        if (transferDetails.fromAccount.balance >= transferDetails.amount) {
            transferDetails.fromAccount.balance -= transferDetails.amount
            transferDetails.toAccount.balance += transferDetails.amount
            // Insert transaction into database
            coroutineScope.launch {
                repository.insert(
                    TransferEntity(
                        fromAccount = transferDetails.fromAccount.accountNumber.toString(),
                        toAccount = transferDetails.toAccount.accountNumber.toString(),
                        amount = transferDetails.amount
                    )
                )
            }
            _transfer.value = "Transfer successful!"
        } else {
            _transfer.value = "Transfer failed! Insufficient balance."
        }
        // Reset showConfirmation to hide the dialog
        _showConfirmation.value = null
    }

    val allTransactions: LiveData<List<TransferEntity>> = repository.allTransactions


    fun cancelTransfer() {
        // Reset showConfirmation to hide the dialog
        _showConfirmation.value = null
    }

}