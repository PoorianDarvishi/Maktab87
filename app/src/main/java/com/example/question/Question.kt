package com.example.question

object Question {
    private val listQuestion = listOf(
        "A dog sweats by panting its tongue.",
        "It takes a sloth two weeks to digest a meal.",
        "The largest living frog is the Goliath frog of West Africa.",
        "When exiting a cave, bats always go in the direction of the wind.",
        "Galapagos tortoises sleep up to 16 hours a day.",
        "Infants have more bones than adults.",
        "Humans lose an average of 75 strands of hair a month.",
        "The human body has four lungs.",
        "A monkey was the first non-human to go into space.",
        "The goat is the national animal of Scotland.",
    )
    private val listAnswer = listOf(
        "false",
        "true",
        "true",
        "false",
        "true",
        "true",
        "false",
        "false",
        "false",
        "false",
    )
    private val listChoose = mutableListOf<String>()
    private val listCheat = mutableListOf<String>()
    private var numberQuestionAndAnswer = 0
    private var point = 0
    private var negativeNum = 0
    private var statusPoint = false
    fun nextQuestion() {
        if (numberQuestionAndAnswer < listQuestion.size - 1) {
            numberQuestionAndAnswer++
        }
    }

    fun prevQuestion() {
        if (numberQuestionAndAnswer > 0) {
            numberQuestionAndAnswer--
        }
    }

    fun showQuestion(): String {
        return listQuestion[numberQuestionAndAnswer]
    }

    fun showAnswer(): String {
        return listAnswer[numberQuestionAndAnswer]
    }

    fun checkAnswer(answer: String): String {
        val result: String = if (listQuestion[numberQuestionAndAnswer] in listCheat) {
            "Cheating is wrong"
        } else {
            when (answer) {
                listAnswer[numberQuestionAndAnswer] -> {
                    statusPoint = true
                    "Correct"
                }
                else -> {
                    statusPoint = false
                    "Incorrect"
                }
            }
        }
        return if (listQuestion[numberQuestionAndAnswer] !in listChoose) {
            addTooQuestionChoose()
            checkPoint(statusPoint)
            result
        } else {
            result
        }
    }

    fun checkQuestion(): Boolean {
        return listQuestion[numberQuestionAndAnswer] in listChoose
    }

    private fun checkPoint(boolean: Boolean) {
        if (boolean) {
            point++
        } else {
            if (negativeNum < 2) {
                negativeNum++
            } else {
                negativeNum = 0
                point--
            }
        }
    }

    fun showPoint(): String {
        return "your score is: $point"
    }

    private fun addTooQuestionChoose() {
        listChoose.add(listQuestion[numberQuestionAndAnswer])
    }

    fun addTooCheat() {
        listCheat.add(listQuestion[numberQuestionAndAnswer])
        addTooQuestionChoose()
    }

    fun checkNextOrPrev(): String {
        return if (numberQuestionAndAnswer == 0) {
            "Prev"
        } else if (numberQuestionAndAnswer == listQuestion.size - 1) {
            "Next"
        } else {
            ""
        }

    }
}