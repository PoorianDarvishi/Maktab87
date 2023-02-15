package com.example.observer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val observable by lazy {  Observable() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(observable)
        Observer("FirstObserver", observable)
        Observer("SecondObserver", observable)
        Observer("ThirdObserver", observable)
    }
}