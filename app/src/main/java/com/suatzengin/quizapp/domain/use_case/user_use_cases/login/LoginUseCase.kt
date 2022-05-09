package com.suatzengin.quizapp.domain.use_case.user_use_cases.login

import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: QuizRepository
) {
    suspend operator fun invoke(mail: String, password: String) = flow{
        emit(Resource.Loading)
        try {
            val login = repo.login(mail, password)
            if(login != null){
                emit(Resource.Success(login))
            }else{
                emit(Resource.Error("Giriş Başarısız"))
            }

        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }

    }
}