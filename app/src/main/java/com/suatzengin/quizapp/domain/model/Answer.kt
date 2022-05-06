package com.suatzengin.quizapp.domain.model

data class Answer(
    val answerId: Int,
    val questionId: Int,
    val answerText: String,
    val isCorrect: Boolean,
)
