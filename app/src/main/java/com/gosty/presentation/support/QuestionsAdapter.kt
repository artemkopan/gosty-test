package com.gosty.presentation.support

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gosty.R
import com.gosty.extensions.inflate
import com.gosty.model.Question
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.question_item.*

class QuestionsAdapter(
    private val itemClick: (Question) -> Unit
) : ListAdapter<Question, QuestionsAdapter.QuestionHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        return QuestionHolder(parent.inflate(R.layout.question_item))
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.questionTitle.text = getItem(position).title
        holder.containerView.setOnClickListener {
            itemClick(getItem(holder.bindingAdapterPosition))
        }
    }

    class QuestionHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

}

private val diff = object : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }

}