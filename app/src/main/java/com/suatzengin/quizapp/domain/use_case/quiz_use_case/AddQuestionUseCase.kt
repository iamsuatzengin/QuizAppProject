package com.suatzengin.quizapp.domain.use_case.quiz_use_case

import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddQuestionUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    operator fun invoke(question: Question) = flow {
        emit(Resource.Loading)
        try {
            val addQuestion = repository.insertQuestion(question)
            emit(Resource.Success(addQuestion))
        } catch (e: Exception) {
            emit(Resource.Error("Soru ekleme işlemi başarısız!"))
        }
    }
}