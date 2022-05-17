package com.suatzengin.quizapp.domain.use_case.quiz_use_case

import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionWithAnswersUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    operator fun invoke() = flow {
        val questionsWithAnswers = repository.getQuestionsWithAnswers()
        emit(questionsWithAnswers)
    }
}