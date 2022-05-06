package com.suatzengin.quizapp.domain.model

data class User(
    val userId: Int,
    val name: String,
    val surname: String,
    val mail: String,
    val password: String,
    val isAdmin: Boolean
)
