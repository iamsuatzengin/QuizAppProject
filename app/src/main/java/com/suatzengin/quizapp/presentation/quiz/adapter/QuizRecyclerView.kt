package com.suatzengin.quizapp.presentation.quiz.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers

class QuizRecyclerView(
    private val onNextClick: (QuestionWithAnswers ,Int) -> Unit,
    private val onFinishClick: (QuestionWithAnswers, Int) -> Unit
): ListAdapter<QuestionWithAnswers, QuizViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val questionWithAnswers = getItem(position)

        holder.bind(questionWithAnswers, onNextClick, onFinishClick, itemCount)
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<QuestionWithAnswers>() {
        override fun areItemsTheSame(
            oldItem: QuestionWithAnswers,
            newItem: QuestionWithAnswers
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: QuestionWithAnswers,
            newItem: QuestionWithAnswers
        ): Boolean {
            return oldItem.question.questionId == newItem.question.questionId
        }

    }
}