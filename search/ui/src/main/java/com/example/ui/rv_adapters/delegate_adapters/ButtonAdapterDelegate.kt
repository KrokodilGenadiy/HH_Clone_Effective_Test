package com.example.ui.rv_adapters.delegate_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.base_delegate_adapter.DelegateAdapter
import com.example.data.adapter_interface.DelegateAdapterItem
import com.example.data.entities.ConfirmButton
import com.example.ui.databinding.ButtonItemBinding
import com.example.utils.getPluralAddition

class ButtonAdapterDelegate(private val clickListener: OnItemClickListener) : DelegateAdapter<ConfirmButton, ButtonAdapterDelegate.ButtonViewHolder>(
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
            binding.root.text = "Еще ${confirmButton.listSize} "+confirmButton.listSize.getPluralAddition(PPCWORD_VACANCY,PCWORD_VACANCY,NCWORD_VACANCY)
        }
    }

    fun interface OnItemClickListener {
        fun click()
    }

    companion object {
        private const val PPCWORD_VACANCY = "вакансий"
        private const val PCWORD_VACANCY = "вакансии"
        private const val NCWORD_VACANCY = "вакансия"

    }
}