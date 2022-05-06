package com.suatzengin.quizapp.domain.repository

import com.suatzengin.quizapp.data.local.relations.UserWithQuizzes
import com.suatzengin.quizapp.domain.model.User

interface QuizRepository {

    suspend fun createUser(user: User)

    suspend fun login(mail: String, password: String): User?

    fun getUsersWithQuizzes(id: Int): List<UserWithQuizzes>
}