package com.suatzengin.quizapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question

data class QuestionWithAnswers(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "question_id",
        entityColumn = "question_id"
    )
    val answer: List<Answer>
)
