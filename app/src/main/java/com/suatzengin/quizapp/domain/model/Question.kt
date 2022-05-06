package com.suatzengin.quizapp.domain.model

import java.util.*

data class Question(
    val questionId: Int,
    val quizId: Int,
    val text: String,
    val correctCount: Int,
    val isLearned: Boolean,
    val nextDate: Date
)