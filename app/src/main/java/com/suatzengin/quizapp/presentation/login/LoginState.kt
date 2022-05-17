package com.suatzengin.quizapp.presentation.login

data class LoginState(
    val error: String? = null,
    val isLoading: Boolean? = false,
    val isLoggedIn: Boolean? = false,
    val userId: Int? = null
)