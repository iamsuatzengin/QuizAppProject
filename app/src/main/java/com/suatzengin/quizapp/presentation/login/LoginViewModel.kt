package com.suatzengin.quizapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.use_case.user_use_cases.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun login(mail: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(mail, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = LoginState(isLoggedIn = true, userId = result.data.userId)
                    }
                    is Resource.Error -> {
                        _state.value = LoginState(error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }
                }
            }
        }
    }
}