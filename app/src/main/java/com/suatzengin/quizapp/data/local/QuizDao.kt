package com.suatzengin.quizapp.data.local

import androidx.room.*
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.model.Quiz

@Dao
interface QuizDao {

    @Insert
    suspend fun insertQuestion(question: Question): Long?

    @Insert
    suspend fun insertAnswer(answer: Answer)

    @Insert
    suspend fun insertQuiz(quiz: Quiz)

    @Update
    suspend fun updateQuiz(quiz: Quiz)

    @Transaction
    @Query("SELECT * FROM question WHERE is_learned = 0 ORDER BY random() LIMIT 10  ")
    fun getQuestionsWithAnswers(): List<QuestionWithAnswers>


}