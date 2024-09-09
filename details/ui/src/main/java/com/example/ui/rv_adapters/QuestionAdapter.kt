package com.example.ui.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.ui.R
import com.example.data.entities.Vacancy
import com.example.ui.databinding.QuestionItemBinding

class QuestionAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<String, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    inner class QuestionViewHolder(private val binding: QuestionItemBinding,clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
        fun bind(question: String) {
           binding.root.text = question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding) {
            clickListener.click(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(getItem(position))

    class QuestionDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    fun interface OnItemClickListener {
        fun click(reply: String)
    }
}