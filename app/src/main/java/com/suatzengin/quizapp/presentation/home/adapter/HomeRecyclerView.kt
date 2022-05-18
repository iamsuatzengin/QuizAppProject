package com.suatzengin.quizapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.suatzengin.quizapp.domain.model.Quiz

class HomeRecyclerView : ListAdapter<Quiz, HomeViewHolder>(DiffCallBackHome) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val quiz = getItem(position)
        holder.bind(quiz)
    }

    companion object DiffCallBackHome : DiffUtil.ItemCallback<Quiz>() {
        override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.quizId == newItem.quizId
        }
    }

}