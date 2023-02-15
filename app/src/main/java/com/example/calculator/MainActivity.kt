package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val history = findViewById<Button>(R.id.history)
        val textResult = findViewById<TextView>(R.id.textResult)
        val clearButton = findViewById<Button>(R.id.clear)
        val assignButton = findViewById<Button>(R.id.assign)
        val dotButton = findViewById<Button>(R.id.dot)
        val negativeAndPositive = findViewById<Button>(R.id.negativeAndPositive)
        val listNum = listOf<Button>(
            findViewById(R.id.one),
            findViewById(R.id.tow),
            findViewById(R.id.three),
            findViewById(R.id.four),
            findViewById(R.id.five),
            findViewById(R.id.six),
            findViewById(R.id.seven),
            findViewById(R.id.eight),
            findViewById(R.id.nine),
            findViewById(R.id.zero)
        )
        val operatorsLis = listOf<Button>(
            findViewById(R.id.plus),
            findViewById(R.id.subtraction),
            findViewById(R.id.multiplication),
            findViewById(R.id.division),
        )
        clearButton.setOnClickListener {
            Calculator.clear()
            textResult.text = Calculator.showResult()
            for (button in operatorsLis) button.isClickable = true
        }
        for (button in listNum) {
            button.setOnClickListener {
                Calculator.addNum(button.tag.toString())
                textResult.text = Calculator.showResult()
            }
        }
        negativeAndPositive.setOnClickListener {
            Calculator.negativeAndPositive()
            textResult.text = Calculator.showResult()
        }
        for (button in operatorsLis) {
            button.setOnClickListener {
                when (button.tag.toString()) {
                    "+" -> Calculator.operator(Operators.PLUS)
                    "-" -> Calculator.operator(Operators.SUBTRACTION)
                    "*" -> Calculator.operator(Operators.MULTIPLICATION)
                    "/" -> Calculator.operator(Operators.DIVISION)
                }
                for (operatorButton in operatorsLis) operatorButton.isClickable = false
            }
        }
        assignButton.setOnClickListener {
            Calculator.assign()
            for (button in operatorsLis) button.isClickable = true
            textResult.text = Calculator.showResult()
        }
        dotButton.setOnClickListener {
            Calculator.addDot()
            textResult.text = Calculator.showResult()
        }
        val resultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val data = it.data
                if (data != null){
                    Calculator.inputHistory(data.getStringExtra("CAL").toString())
                    textResult.text = Calculator.showResult()
                }
            }
        }
        history.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            intent.putExtra("CALCULATE", Calculator.showHistory())
            resultActivity.launch(intent)
        }

    }
}