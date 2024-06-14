package com.example.vpdpay.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vpdpay.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.run {
            registerTv.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        SignUpActivity::class.java
                    )
                )
            }
            signinBtn.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(
                    emailEt.text.toString().trim(),
                    passwordEt.text.toString().trim()
                )
                    .addOnCompleteListener(this@LoginActivity) { task ->
                        if (task.isSuccessful) {
                            progressBar.visibility = View.GONE
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }


            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
    }

}