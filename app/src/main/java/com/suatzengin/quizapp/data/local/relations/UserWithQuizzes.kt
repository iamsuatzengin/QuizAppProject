package com.suatzengin.quizapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.suatzengin.quizapp.domain.model.Quiz
import com.suatzengin.quizapp.domain.model.User

data class UserWithQuizzes(
    @Embedded val user: User,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val quizzes: List<Quiz>
)
