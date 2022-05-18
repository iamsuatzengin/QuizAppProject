package com.suatzengin.quizapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.quizapp.databinding.QuizHomeRvItemBinding
import com.suatzengin.quizapp.domain.model.Quiz


class HomeViewHolder(
    private val binding: QuizHomeRvItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(quiz: Quiz) {
        binding.apply {
            tvQuizId.text = quiz.quizId.toString()
            textViewTotalScore.text = quiz.totalScore.toString()
            textViewTotalCorrect.text = quiz.totalCorrect.toString()
            textViewTotalWrong.text = quiz.totalWrong.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup): HomeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = QuizHomeRvItemBinding.inflate(layoutInflater, parent, false)
            return HomeViewHolder(binding)
        }
    }
}