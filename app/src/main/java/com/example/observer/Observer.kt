package com.example.observer

import android.util.Log

class Observer(private val name: String, observable: Observable) : Update {
    private val listNumber = mutableListOf<Int>()

    init {
        observable.register(this)
    }

    override fun update(number: Int) {
        listNumber.add(number)
        Log.d("$name Received", "$number")
    }
}