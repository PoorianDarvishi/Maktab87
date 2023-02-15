package com.example.practiceone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userName = findViewById<TextView>(R.id.plain_text_input_Username)
        val password = findViewById<TextView>(R.id.plain_text_input_password)
        val logIn = findViewById<Button>(R.id.logIn)
        val signUp = findViewById<TextView>(R.id.signUp)
        var usernameText: String? = null
        var passwordText: String? = null
        val resultActivity =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            when(result.resultCode){
                RESULT_OK ->{
                    val data = result.data
                    if (data != null) {
                            usernameText = data.getStringExtra(SignUpActivity.USERNAME)
                            passwordText = data.getStringExtra(SignUpActivity.PASSWORD)
                            userName.text = usernameText
                            password.text = passwordText
                    }
                }
            }
        }
        logIn.setOnClickListener {
            if (userName.text.toString() == usernameText &&
                password.text.toString() == passwordText){
                Snackbar.make(it,"Your Welcome :)",Snackbar.LENGTH_INDEFINITE).setAction("undo") {}.show()
            }else {
                Toast.makeText(this, "Your username or password is false :(",Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            intent.putExtra(SignUpActivity.USERNAME, userName.text.toString())
            intent.putExtra(SignUpActivity.PASSWORD, password.text.toString())
            resultActivity.launch(intent)
        }
    }
}