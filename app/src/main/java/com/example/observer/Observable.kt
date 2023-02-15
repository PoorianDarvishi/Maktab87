package com.example.observer

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class Observable : DefaultLifecycleObserver {
    private val listObservers = mutableListOf<Observer>()
    private var listNumber = (1..100).toMutableList()
    private var flag = true
    fun register(observer: Observer) {
        listObservers.add(observer)
    }

    private fun notification(number: Int) {
        for (observer in listObservers) {
            observer.update(number)
        }
    }

    private fun action() {
        Thread {
            var index = 1
            val size = listNumber.size
            while (flag) {
                notification(listNumber[0])
                listNumber.removeAt(0)
                if (index == size) break
                index++
                Thread.sleep(3000)
            }
        }.start()
    }


    override fun onResume(owner: LifecycleOwner) {
        flag = true
        action()
    }


    override fun onPause(owner: LifecycleOwner) {
        flag = false
    }
}