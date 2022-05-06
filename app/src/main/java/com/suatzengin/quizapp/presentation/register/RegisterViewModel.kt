package com.suatzengin.quizapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.model.User
import com.suatzengin.quizapp.domain.use_case.user_use_cases.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state


    fun register(user: User){
        viewModelScope.launch(Dispatchers.IO){
            registerUseCase(user).collect{ result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = RegisterState(isRegister = true)
                    }
                    is Resource.Error -> {
                        _state.value = RegisterState(error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = RegisterState(isLoading = true)
                    }
                }

            }
        }
    }
}