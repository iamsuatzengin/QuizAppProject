package com.suatzengin.quizapp.data.repository

import com.suatzengin.quizapp.data.local.QuizDao
import com.suatzengin.quizapp.data.local.UserDao
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.data.local.relations.UserWithQuizzes
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.model.User
import com.suatzengin.quizapp.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val quizDao: QuizDao
) : QuizRepository {

    override suspend fun createUser(user: User) = userDao.createUser(user)

    override suspend fun login(mail: String, password: String): User? = userDao.login(mail, password)

    override fun getUsersWithQuizzes(id: Int): List<UserWithQuizzes> {
        return userDao.getUsersWithQuizzes(id)
    }

    override suspend fun insertQuestion(question: Question): Long? = quizDao.insertQuestion(question)

    override suspend fun insertAnswer(answer: Answer) = quizDao.insertAnswer(answer)

    override fun getQuestionsWithAnswers(): List<QuestionWithAnswers> {
        return quizDao.getQuestionsWithAnswers()
    }
}