package com.suatzengin.quizapp.presentation.add_question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.quizapp.common.Resource
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import com.suatzengin.quizapp.domain.use_case.quiz_use_case.AddAnswerUseCase
import com.suatzengin.quizapp.domain.use_case.quiz_use_case.AddQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor(
    private val addQuestionUseCase: AddQuestionUseCase,
    private val addAnswerUseCase: AddAnswerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddQuestionState())
    val state: StateFlow<AddQuestionState> = _state


    fun addQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO) {
            addQuestionUseCase(question).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AddQuestionState(isAdded = true, questionId = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = AddQuestionState(error = "Ekleme başarısız!")
                    }
                    is Resource.Loading -> {
                        _state.value = AddQuestionState(isLoading = true)
                    }
                }
            }
        }
    }

    fun addAnswer(answer: Answer) {
        viewModelScope.launch(Dispatchers.IO) {
            addAnswerUseCase(answer).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AddQuestionState(isAdded = true)
                    }
                    is Resource.Error -> {
                        _state.value = AddQuestionState(error = "Ekleme başarısız!")
                    }
                    is Resource.Loading -> {
                        _state.value = AddQuestionState(isLoading = true)
                    }
                }
            }
        }
    }
}