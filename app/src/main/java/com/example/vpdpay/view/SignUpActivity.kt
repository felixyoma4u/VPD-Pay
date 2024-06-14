package com.example.vpdpay.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vpdpay.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity() {
    private val TAG = "SignupActivity"
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth
        binding.run {
            registerTv.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            }
            signupBtn.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(
                    emailEt.text.toString().trim(),
                    passwordEt.text.toString().trim()
                )
                    .addOnCompleteListener(this@SignUpActivity) { task ->
                        if (task.isSuccessful) {
                            progressBar.visibility = View.GONE
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                        } else {
                            progressBar.visibility = View.GONE
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
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
            startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
        }
    }

}