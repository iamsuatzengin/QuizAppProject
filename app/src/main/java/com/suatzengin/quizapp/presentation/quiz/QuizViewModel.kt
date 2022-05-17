package com.suatzengin.quizapp.presentation.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.domain.model.Quiz
import com.suatzengin.quizapp.domain.use_case.quiz_use_case.AddQuizUseCase
import com.suatzengin.quizapp.domain.use_case.quiz_use_case.GetQuestionWithAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuestionWithAnswersUseCase: GetQuestionWithAnswersUseCase,
    private val addQuizUseCase: AddQuizUseCase
) : ViewModel() {

    private val _questionWithAnswers = MutableLiveData<List<QuestionWithAnswers>>()
    val questionWithAnswers: LiveData<List<QuestionWithAnswers>>
        get() = _questionWithAnswers

    private var _totalScore = 0
    val totalScore: Int get() = _totalScore

    private var _totalCorrect = 0
    val totalCorrect: Int get() = _totalCorrect

    private var _totalWrong = 0
    val totalWrong: Int get() = _totalWrong


    init {
        getAllQuestionWithAnswers()
    }

    private fun getAllQuestionWithAnswers() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuestionWithAnswersUseCase().collect { list ->
                _questionWithAnswers.postValue(list)
            }
        }
    }

    fun addQuiz(quiz: Quiz) {
        viewModelScope.launch(Dispatchers.IO) {
            addQuizUseCase(quiz).collect{

            }
        }
    }

    fun verifyAnswer(questionWithAnswer: QuestionWithAnswers, index: Int) {
        val answer = questionWithAnswer.answer
        if (answer[index].isCorrect) {
            println("${answer[index].answerText} cevabi doğru")
            _totalScore += 10
            _totalCorrect++
        } else {
            _totalWrong++
            println("${answer[index].answerText} cevabi yanlış")
        }

    }

}