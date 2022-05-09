package com.suatzengin.quizapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question

@Dao
interface QuizDao {

    @Insert
    suspend fun insertQuestion(question: Question): Long?

    @Insert
    suspend fun insertAnswer(answer: Answer)

    @Transaction
    @Query("SELECT * FROM question")
    fun getQuestionsWithAnswers(): List<QuestionWithAnswers>
}