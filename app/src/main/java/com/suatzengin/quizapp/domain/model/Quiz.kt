package com.suatzengin.quizapp.domain.model


data class Quiz(
    val quizId: Int,
    val totalScore: Int,
    val totalCorrect: Int,
    val totalWrong: Int,
    val userId: Int,
)
