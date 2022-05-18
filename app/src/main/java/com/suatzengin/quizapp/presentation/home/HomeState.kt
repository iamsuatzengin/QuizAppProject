package com.suatzengin.quizapp.presentation.home

import com.suatzengin.quizapp.domain.model.Quiz

data class HomeState(
    val error: String? = null,
    val isLoading: Boolean? = false,
    val quizzes: List<Quiz> = emptyList()
)