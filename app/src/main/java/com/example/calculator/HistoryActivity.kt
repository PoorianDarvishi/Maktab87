package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity(), CellClickListener{
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val listCalculate = intent!!.getStringArrayExtra("CALCULATE")


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(listCalculate as Array<String>,this)
        recyclerview.adapter = adapter


    }

    override fun onCellClickListener(data: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra("CAL", data)
        })
        finish()
    }
}