package com.suatzengin.quizapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.use_case.quiz_use_case.GetQuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state


    fun getQuiz(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            getQuizUseCase(id).collect{ result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = HomeState(quizzes = result.data)
                    }
                    is Resource.Loading -> {
                        _state.value = HomeState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = HomeState(error = "Başarısız")
                    }
                }
            }
        }
    }

}