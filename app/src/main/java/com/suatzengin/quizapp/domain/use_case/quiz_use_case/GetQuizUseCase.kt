package com.suatzengin.quizapp.domain.use_case.quiz_use_case

import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    operator fun invoke(id: Int) = flow {
        emit(Resource.Loading)
        try {
            val quiz = repository.getQuiz(id)
            emit(Resource.Success(quiz))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }
}