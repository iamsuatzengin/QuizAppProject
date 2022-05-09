package com.suatzengin.quizapp.domain.repository

import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.data.local.relations.UserWithQuizzes
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.model.User

interface QuizRepository {

    suspend fun createUser(user: User)

    suspend fun login(mail: String, password: String): User?

    fun getUsersWithQuizzes(id: Int): List<UserWithQuizzes>

    suspend fun insertQuestion(question: Question): Long?

    suspend fun insertAnswer(answer: Answer)

    fun getQuestionsWithAnswers(): List<QuestionWithAnswers>
}