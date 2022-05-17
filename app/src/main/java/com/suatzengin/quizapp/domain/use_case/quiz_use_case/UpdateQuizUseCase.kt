package com.suatzengin.quizapp.domain.use_case.quiz_use_case

import com.suatzengin.quizapp.domain.model.Quiz
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateQuizUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    operator fun invoke(quiz: Quiz) = flow {
        val updateQuiz = repository.updateQuiz(quiz)
        emit(updateQuiz)
    }
}