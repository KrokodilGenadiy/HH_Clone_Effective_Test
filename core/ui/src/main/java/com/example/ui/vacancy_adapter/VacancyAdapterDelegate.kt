package com.example.ui.vacancy_adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.core.ui.R
import com.core.ui.databinding.VacancyItemBinding
import com.example.data.adapter_interface.DelegateAdapterItem
import com.example.data.entities.Vacancy
import com.example.ui.base_delegate_adapter.DelegateAdapter
import com.example.utils.getMonthName
import com.example.utils.getPluralAddition

class VacancyAdapterDelegate(
    private val clickListener: OnItemClickListener,
    private val favoritesClickListener: OnItemClickListener
) : DelegateAdapter<Vacancy, VacancyAdapterDelegate.VacancyViewHolder>(
    Vacancy::class.java
) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding,
            { clickListener.click(it) },
            { favoritesClickListener.click(it) })
    }

    override fun bindViewHolder(
        model: Vacancy,
        viewHolder: VacancyViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class VacancyViewHolder(
        private val binding: VacancyItemBinding,
        private val clickAtPosition: (Vacancy) -> Unit,
        private val favoritesClickListener: (Vacancy) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancy: Vacancy) {
            binding.root.setOnClickListener {
                clickAtPosition(vacancy)
            }
            binding.favoritesIcon.setOnClickListener {
                favoritesClickListener(vacancy)
                if (vacancy.isFavorite)
                    binding.favoritesIcon.setImageResource(R.drawable.ic_heart_filled)
                else
                    binding.favoritesIcon.setImageResource(R.drawable.ic_empty_heart)
            }
            if (vacancy.isFavorite)
                binding.favoritesIcon.setImageResource(R.drawable.ic_heart_filled)
            else
                binding.favoritesIcon.setImageResource(R.drawable.ic_empty_heart)
            binding.jobTitle.text = vacancy.title
            binding.cityName.text = vacancy.address.town
            if (vacancy.appliedNumber != null) {
                binding.nowLooking.text = buildString {
                    append(NOW_LOOKING)
                    append(vacancy.appliedNumber.toString())
                    append(
                        " " + vacancy.appliedNumber?.getPluralAddition(
                            PPCWORD,
                            PCWORD,
                            NCWORD
                        )
                    )
                }
            } else {
                binding.nowLooking.visibility = GONE
            }
            binding.workExperience.text = vacancy.experience.previewText
            val date = vacancy.publishedDate.split("-")
            binding.publishingDate.text = buildString {
                append(PUBLISHED)
                append(date[2].toInt())
                append(
                    " " + date[1].toInt().getMonthName()
                )
            }
            binding.companyName.text = vacancy.company
        }
    }

    fun interface OnItemClickListener {
        fun click(vacancy: Vacancy)
    }

    companion object {
        private const val PUBLISHED = "Опубликовано "
        private const val NOW_LOOKING = "Сейчас просматривает "
        private const val PPCWORD = "человек"
        private const val PCWORD = "человека"
        private const val NCWORD = "человек"
    }
}