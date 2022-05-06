package com.suatzengin.quizapp.domain.use_case.user_use_cases.register

import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.model.User
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(var repo: QuizRepository) {
    operator fun invoke(user: User) = flow{
        emit(Resource.Loading)
        try {
            val register = repo.createUser(user)
            emit(Resource.Success(register))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}