package com.example.vpdpay.view

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vpdpay.adapter.TransferHistoryAdapter
import com.example.vpdpay.databinding.ActivityTransferBinding
import com.example.vpdpay.db.AppDatabase
import com.example.vpdpay.model.TransferDetails
import com.example.vpdpay.repositories.TransactionRepository
import com.example.vpdpay.utils.userAccount
import com.example.vpdpay.viewModel.TransferViewModel
import com.example.vpdpay.viewModel.TransferViewModelFactory
import kotlinx.coroutines.launch

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    private lateinit var transferHistoryAdapter: TransferHistoryAdapter
    private val viewModel: TransferViewModel by viewModels {
        val database = AppDatabase.getDatabase(this)
        val repository = TransactionRepository(database.transactionDao())
        TransferViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transferHistoryAdapter = TransferHistoryAdapter()


        binding.run {

            val accountNumbers = userAccount.map { it.accountNumber }.toTypedArray()

            // ... (spinner setup)
            val adapter =
                ArrayAdapter(this@TransferActivity, R.layout.simple_spinner_item, accountNumbers)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            fromAccountSpinner.adapter = adapter
            toAccountSpinner.adapter = adapter

            // ... (recycler view setup)
            transferRV.layoutManager = LinearLayoutManager(this@TransferActivity)
            transferRV.adapter = transferHistoryAdapter

            transferButton.setOnClickListener {
                val fromAccount = fromAccountSpinner.selectedItem.toString()
                val toAccount = toAccountSpinner.selectedItem.toString()
                val amount = amountEt.text.toString().toDoubleOrNull()

                transferButton.setOnClickListener {
                    if (amount != null) {
                        viewModel.performTransfer(
                            fromAccount,
                            toAccount,
                            amount
                        ) // Use to initiate Transfer
                    } else {
                        resultTextView.text = "Please enter a valid amount"
                    }
                }
            }

            // Observe showConfirmation to display the dialog

            viewModel.showConfirmation.observe(this@TransferActivity) { transferDetails ->
                if (transferDetails != null) {
                    showConfirmationDialog(transferDetails)
                }
            }

            // Observe transferResult LiveData
            viewModel.transfer.observe(this@TransferActivity) { result ->
                // Update the UI with the transfer result
                resultTextView.text = result
            }

            lifecycleScope.launch {
                viewModel.allTransactions.observe(this@TransferActivity) { transfers ->
                    transferHistoryAdapter.setTransactions(transfers)
                }
            }
        }


    }

    private fun showConfirmationDialog(transferDetails: TransferDetails) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Transfer")
            .setMessage(
                "Transfer ${transferDetails.amount} from " +
                        "${transferDetails.fromAccount.accountNumber} to " +
                        "${transferDetails.toAccount.accountNumber}?"
            )
            .setPositiveButton("Confirm") { _, _ ->
                viewModel.confirmTransfer(transferDetails)
            }
            .setNegativeButton("Cancel") { _, _ ->
                viewModel.cancelTransfer()
            }
            .show()
    }
}