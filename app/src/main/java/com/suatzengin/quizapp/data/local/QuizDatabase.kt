package com.suatzengin.quizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.model.Quiz
import com.suatzengin.quizapp.domain.model.User

@Database(
    entities = [(User::class), (Quiz::class), (Question::class), (Answer::class)],
    version = 3, exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun quizDao(): QuizDao
}