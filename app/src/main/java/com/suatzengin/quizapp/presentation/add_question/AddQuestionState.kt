package com.suatzengin.quizapp.presentation.add_question

data class AddQuestionState(
    val error: String? = null,
    val isLoading: Boolean? = false,
    val isAdded: Boolean? = false,
    val questionId: Long? = null
)
