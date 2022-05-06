package com.suatzengin.quizapp.presentation.register

data class RegisterState(
    val error: String? = null,
    val isLoading: Boolean? = false,
    val isRegister: Boolean? = false,

)