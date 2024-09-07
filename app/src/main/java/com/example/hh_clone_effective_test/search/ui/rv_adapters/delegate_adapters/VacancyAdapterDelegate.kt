package com.example.hh_clone_effective_test.search.ui.rv_adapters.delegate_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_clone_effective_test.databinding.VacancyItemBinding
import com.example.hh_clone_test.search.data.entities.Vacancy
import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapter
import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapterItem
import com.example.hh_clone_test.util.getPluralAddition

class VacancyAdapterDelegate : DelegateAdapter<Vacancy, VacancyAdapterDelegate.VacancyViewHolder>(Vacancy::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun bindViewHolder(
        model: Vacancy,
        viewHolder: VacancyViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class VacancyViewHolder(private val binding: VacancyItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.jobTitle.text = vacancy.title
            binding.cityName.text = vacancy.address.town
            binding.nowLooking.text = buildString {
                append("Сейчас просматривает ")
                append(vacancy.appliedNumber.toString())
                append(" "+vacancy.appliedNumber.getPluralAddition("человек", "человека","человек"))
            }
            binding.workExperience.text = vacancy.experience.previewText
            binding.publishingDate.text = vacancy.publishedDate
            binding.companyName.text = vacancy.company
        }
    }
}