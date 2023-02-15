package com.example.question

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val questionTex = findViewById<TextView>(R.id.textViewQuestion)
        val trueButton = findViewById<Button>(R.id.buttonTrue)
        val falseButton = findViewById<Button>(R.id.buttonFalse)
        val listTrueFalse = listOf(trueButton, falseButton)
        val cheatButton = findViewById<Button>(R.id.buttonCheat)
        val nextButton = findViewById<Button>(R.id.buttonNext)
        val prevButton = findViewById<Button>(R.id.buttonPrev)
        val textViewScore = findViewById<TextView>(R.id.textViewScore)

        fun isClickableFalse() {
            for (button in listTrueFalse) {
                button.isEnabled = false
            }
        }

        fun isClickableTrue() {
            for (button in listTrueFalse) {
                button.isEnabled = true
            }
            nextButton.isEnabled = true
            prevButton.isEnabled = true
        }

        fun nextIsClickableFalse() {
            nextButton.isEnabled = false
        }

        fun prevIsClickableFalse() {
            prevButton.isEnabled = false
        }

        fun checkPage() {
            if (Question.checkQuestion()) {
                for (button in listTrueFalse) {
                    isClickableFalse()
                }
            } else {
                isClickableTrue()
            }
            if (Question.checkNextOrPrev() == "Next") {
                nextIsClickableFalse()
            } else if (Question.checkNextOrPrev() == "Prev") {
                prevIsClickableFalse()
            }
        }

        fun showQuestionText() {
            questionTex.text = Question.showQuestion()
        }

        fun showPoint() {
            textViewScore.text = Question.showPoint()

        }
        showQuestionText()
        showPoint()
        for (button in listTrueFalse) {
            button.setOnClickListener {
                val result = Question.checkAnswer(button.tag.toString())
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                showPoint()
                isClickableFalse()
            }
        }
        nextButton.setOnClickListener {
            Question.nextQuestion()
            showQuestionText()
            checkPage()
        }
        prevButton.setOnClickListener {
            Question.prevQuestion()
            showQuestionText()
            checkPage()
        }
        val resultActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) Question.addTooCheat()
            }
        cheatButton.setOnClickListener {
            resultActivity.launch(Intent(this, CheatActivity::class.java))
        }
        checkPage()
    }
}