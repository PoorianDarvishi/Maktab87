package com.example.practiceone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SignUpActivity: AppCompatActivity() {
    companion object{
        const val PASSWORD = "PASSWORD"
        const val USERNAME = "USERNAME"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val userName = findViewById<TextView>(R.id.plain_text_input_Username)
        val password = findViewById<TextView>(R.id.plain_text_input_password)
        val signUp = findViewById<TextView>(R.id.signUp)
        userName.text = intent?.getStringExtra(USERNAME)
        password.text = intent?.getStringExtra(PASSWORD)
        signUp.setOnClickListener {
            if (userName.text.toString().length <= 3 || password.text.toString().length <= 7) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                setResult(
                    RESULT_OK,
                    Intent()
                        .putExtra(USERNAME, userName.text.toString())
                        .putExtra(PASSWORD, password.text.toString())
                )
                finish()
            }
        }
    }
}