package com.suatzengin.quizapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name = "quiz_id")
    val quizId: Int,
    val text: String,
    @ColumnInfo(name = "correct_count")
    val correctCount: Int,
    @ColumnInfo(name = "is_learned")
    val isLearned: Boolean,
    @ColumnInfo(name = "next_date")
    val nextDate: Date
)