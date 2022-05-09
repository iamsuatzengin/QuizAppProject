package com.suatzengin.quizapp.domain.use_case.quiz_use_case

import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddAnswerUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    operator fun invoke(answer: Answer) = flow {
        emit(Resource.Loading)
        try {
            val addAnswer = repository.insertAnswer(answer)
            emit(Resource.Success(addAnswer))
        } catch (e: Exception) {
            emit(Resource.Error("Cevap ekleme işlemi başarısız"))
        }
    }
}