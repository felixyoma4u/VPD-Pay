package com.example.vpdpay.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vpdpay.adapter.UserAccountAdapter
import com.example.vpdpay.databinding.ActivityHomeBinding
import com.example.vpdpay.utils.userAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {

    private lateinit var userAcountAdapter: UserAccountAdapter
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userList = userAccount
        userAcountAdapter = UserAccountAdapter(userList)
        binding.viewAccountsRV.adapter = userAcountAdapter

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.run {
            balAmount.text = userList[0].balance.toString()
            holderTv.text = userList[0].name

            buttonWithdraw.setOnClickListener {
                Toast.makeText(
                    this@HomeActivity,
                    "Coming soon",
                    Toast.LENGTH_SHORT
                ).show()
            }
            buttonTransfer.setOnClickListener {
                startActivity(Intent(this@HomeActivity, TransferActivity::class.java))
            }

            buttonHistory.setOnClickListener {
                startActivity(Intent(this@HomeActivity, TransferActivity::class.java))
            }
            signout.setOnClickListener {
                auth.signOut()
                finish()
            }
        }


    }
}