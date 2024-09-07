package com.example.hh_clone_effective_test.search.ui.rv_adapters.delegate_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_clone_effective_test.databinding.ButtonItemBinding
import com.example.hh_clone_test.search.data.entities.ConfirmButton
import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapter
import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapterItem
import com.example.hh_clone_test.util.getPluralAddition

class ButtonAdapterDelegate(private val clickListener: OnItemClickListener,) : DelegateAdapter<ConfirmButton, ButtonAdapterDelegate.ButtonViewHolder>(
    ConfirmButton::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ButtonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding) {
            clickListener.click()
        }
    }

    override fun bindViewHolder(
        model: ConfirmButton,
        viewHolder: ButtonViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class ButtonViewHolder(private val binding: ButtonItemBinding,clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(confirmButton: ConfirmButton) {
            binding.root.text = "Еще ${confirmButton.listSize} "+confirmButton.listSize.getPluralAddition("вакансий","вакансии","вакансия")
        }
    }

    fun interface OnItemClickListener {
        fun click()
    }
}