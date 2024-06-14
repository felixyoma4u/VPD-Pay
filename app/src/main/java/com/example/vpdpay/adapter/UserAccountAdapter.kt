package com.example.vpdpay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vpdpay.R
import com.example.vpdpay.model.UserAccount

class UserAccountAdapter(private var accounts: List<UserAccount>) :
    RecyclerView.Adapter<UserAccountAdapter.UserAccountViewHolder>() {
    fun unpdateList(list: List<UserAccount>){
        accounts = list
        notifyDataSetChanged()
    }

        class UserAccountViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val accountNumber: TextView = itemView.findViewById(R.id.accountNumber)
            val accountName: TextView = itemView.findViewById(R.id.accountName)
            val accountBalance: TextView = itemView.findViewById(R.id.accountBalance)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_account, parent, false)
        return UserAccountViewHolder(view)
    }

    override fun getItemCount(): Int {
       return accounts.size
    }

    override fun onBindViewHolder(holder: UserAccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.accountNumber.text = "Account Number: ${account.accountNumber.toString()}"
        holder.accountName.text = "Name: ${account.name}"
        holder.accountBalance.text = "Balance: ${account.balance.toString()}"
    }
}