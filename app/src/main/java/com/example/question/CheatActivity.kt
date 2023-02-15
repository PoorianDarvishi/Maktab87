package com.example.question

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {
    companion object{
        private const val CHEAT = "STATUSCHEAT"
        private const val TEXT = "STATUSTEXT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val textAnswer = findViewById<TextView>(R.id.textViewAnswer)
        val buttonShowAnswer = findViewById<Button>(R.id.buttonShowAnswer)
        fun showAnswer() {
            textAnswer.text = Question.showAnswer()
        }
        buttonShowAnswer.setOnClickListener {
            showAnswer()
            setResult(RESULT_OK, Intent())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val textAnswer = findViewById<TextView>(R.id.textViewAnswer)
        outState.putString(TEXT, textAnswer.text.toString())
        if(checkText(textAnswer.text.toString())){
            outState.putBoolean(CHEAT, true)
        }
        super.onSaveInstanceState(outState)
    }
    private fun checkText(text: String): Boolean {
        return text == "true" || text == "false"
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textAnswer = findViewById<TextView>(R.id.textViewAnswer)
        textAnswer.text = savedInstanceState.getString(TEXT)
        if (savedInstanceState.getBoolean(CHEAT)){
            setResult(RESULT_OK, Intent())
        }
    }
}