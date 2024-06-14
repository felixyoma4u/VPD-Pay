package com.example.vpdpay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vpdpay.R
import com.example.vpdpay.db.entities.TransferEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransferHistoryAdapter : RecyclerView.Adapter<TransferHistoryAdapter.TransferViewHolder>() {
    private var transactions: List<TransferEntity> = emptyList()


    class TransferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fromAccount: TextView = itemView.findViewById(R.id.fromAccountTv)
        private val toAccount: TextView = itemView.findViewById(R.id.toAccountTv)
        private val amount: TextView = itemView.findViewById(R.id.amountTv)
        private val time: TextView = itemView.findViewById(R.id.timestampTv)

        fun bind(transaction: TransferEntity) {
            fromAccount.text = "From: ${transaction.fromAccount}"
            toAccount.text = "To: ${transaction.toAccount}"
            amount.text = "Amount: ${transaction.amount}"
            time.text = "Time: ${formatTimestamp(transaction.timestamp)}"
        }

        private fun formatTimestamp(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            return dateFormat.format(Date(timestamp))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction_history, parent, false)
        return TransferViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    fun setTransactions(newTransactions: List<TransferEntity>) {
        transactions = newTransactions
        notifyDataSetChanged() // Notify any changes to the RecyclerView
    }
}