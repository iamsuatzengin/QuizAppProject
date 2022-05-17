package com.suatzengin.quizapp.presentation.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.databinding.QuizItemBinding

class QuizViewHolder(
    private val binding: QuizItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        questionWithAnswers: QuestionWithAnswers,
        onNextClick: (QuestionWithAnswers, Int) -> Unit,
        onFinishClick: (QuestionWithAnswers, Int) -> Unit,
        itemCount: Int
    ) {

        binding.tvQuestionText.text = questionWithAnswers.question.text
        binding.radioAnswerA.text = questionWithAnswers.answer[0].answerText
        binding.radioAnswerB.text = questionWithAnswers.answer[1].answerText
        binding.radioAnswerC.text = questionWithAnswers.answer[2].answerText
        binding.radioAnswerD.text = questionWithAnswers.answer[3].answerText

        println("adapter position : $adapterPosition")

        if(adapterPosition == itemCount - 1){
            binding.btnNext.apply {
                text = "Bitir"
                setOnClickListener {
                    onFinishClick(questionWithAnswers, indexOfAnswer())
                }
            }
        }else{
            binding.btnNext.setOnClickListener {
                onNextClick.invoke(questionWithAnswers,indexOfAnswer())
            }
        }
    }
    private fun indexOfAnswer(): Int {
        val checkedRbId = binding.rgAnswers.checkedRadioButtonId
        val radioButton = itemView.findViewById<RadioButton>(checkedRbId)
        return binding.rgAnswers.indexOfChild(radioButton)
    }

    companion object {
        fun from(parent: ViewGroup): QuizViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = QuizItemBinding.inflate(layoutInflater, parent, false)
            return QuizViewHolder(binding)
        }
    }

}