package com.example.calculator

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

object Calculator {
    private var result = 0.0
    private var number1 = 0.0
    private var number2 = 0.0
    private var power = -1
    private lateinit var operator: Operators
    private var isDot = false
    private var isDotFirst = false
    private var isAssign = false
    private var listCalculate = mutableListOf<String>()
    fun showResult(): String {
        return if (!isDotFirst && !isDot && result.toInt() >= result) {
            (result.toInt()).toString()
        } else if (isDotFirst) {
            isDotFirst = false
            "${result.toInt()}."
        } else {
            if (result.toString() == "Infinity"){
                result.toString()
            } else if (result.toString().substring(result.toString().indexOf('.')).length > 5) {
                BigDecimal(result).setScale(5, RoundingMode.FLOOR).toString()
            } else {
                result.toString()
            }

        }
    }

    fun addNum(num: String) {
        if (isAssign) {
            isAssign = false
            result = 0.0
        }
        if (!isDot) {
            result = result * 10 + num.toInt()
        } else {
            result += (num.toInt() * 10.0.pow(power))
            power--
        }

    }

    fun addDot() {
        isDot = true
        isDotFirst = true
    }

    fun clear() {
        result = 0.0
        number1 = 0.0
        number2 = 0.0
        isAssign = false
        resetDot()

    }

    fun operator(operator: Operators) {
        this.operator = operator
        number1 = result
        result = 0.0
        resetDot()
    }

    fun assign() {
        if (number2 == 0.0 || !isAssign) {
            number2 = result
        }

        result = when (operator) {
            Operators.PLUS -> {
                listCalculate.add("$number1 + $number2")
                number1 + number2
            }
            Operators.SUBTRACTION -> {
                listCalculate.add("$number1 - $number2")
                number1 - number2
            }
            Operators.MULTIPLICATION -> {
                listCalculate.add("$number1 * $number2")
                number1 * number2
            }
            Operators.DIVISION -> {
                listCalculate.add("$number1 / $number2")
                number1 / number2
            }
        }
        number1 = result
        isDotFirst = false
        isAssign = true
    }

    fun negativeAndPositive() {
        result *= -1
    }

    private fun resetDot() {
        isDot = false
        isDotFirst = false
        power = -1
    }
    fun showHistory(): Array<String> {
        return listCalculate.toTypedArray()
    }
    fun inputHistory(cal : String){
        val cal = cal.split(" ")
        number1 = cal[0].toDouble()
        number2 = cal[2].toDouble()
        val listOperators = listOf("+","-","*","/")
        for (operator in listOperators){
            if (operator in cal){
                result = when (operator){
                    "+" -> number1 + number2
                    "-" -> number1 - number2
                    "*" -> number1 * number2
                    "/" -> number1 / number2
                    else -> 0.0
                }
                isDotFirst = false
                isAssign = true
                break
            }
        }
    }
}