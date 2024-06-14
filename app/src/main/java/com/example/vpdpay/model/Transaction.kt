package com.example.vpdpay.model

data class TransferDetails(
    val fromAccount: UserAccount,
    val toAccount: UserAccount,
    var amount: Double
)

data class UserAccount(
    val accountNumber: Long,
    val name: String,
    var balance: Double
)