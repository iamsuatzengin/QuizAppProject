package com.suatzengin.quizapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quiz_id")
    val quizId: Int,
    @ColumnInfo(name = "total_score")
    val totalScore: Int?,
    @ColumnInfo(name = "total_correct")
    val totalCorrect: Int?,
    @ColumnInfo(name = "total_wrong")
    val totalWrong: Int?,
    @ColumnInfo(name = "user_id")
    val userId: Int,
): Parcelable
