package com.suatzengin.quizapp.data.repository

import com.suatzengin.quizapp.data.local.UserDao
import com.suatzengin.quizapp.data.local.relations.UserWithQuizzes
import com.suatzengin.quizapp.domain.model.User
import com.suatzengin.quizapp.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : QuizRepository {

    override suspend fun createUser(user: User) = userDao.createUser(user)

    override suspend fun login(mail: String, password: String): User? = userDao.login(mail, password)

    override fun getUsersWithQuizzes(id: Int): List<UserWithQuizzes> {
        return userDao.getUsersWithQuizzes(id)
    }
}