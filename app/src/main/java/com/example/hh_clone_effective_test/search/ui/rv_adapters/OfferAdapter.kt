package com.example.hh_clone_test.search.ui.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_clone_effective_test.R
import com.example.hh_clone_effective_test.databinding.OfferItemBinding
import com.example.hh_clone_test.search.data.entities.Offer

class OfferAdapter :
    ListAdapter<Offer, OfferAdapter.OfferViewHolder>(OfferDiffCallback()) {

    class OfferViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            if (offerIds.contains(offer.id)) {
                offerIds[offer.id]?.let { binding.icon.setImageResource(it) }
                if (offer.id == "near_vacancies")
                    binding.icon.setBackgroundResource(R.drawable.darker_blue_circle_shape)
            } else {
                binding.icon.visibility = View.INVISIBLE
            }
            if (offer.button == null) {
                binding.headerTitle.maxLines = 3
                binding.headerTitle.text = offer.title
                binding.actionText.visibility = View.INVISIBLE
            } else {
                binding.headerTitle.maxLines = 2
                binding.headerTitle.text = offer.title
                binding.actionText.text = offer.button.text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) =
        holder.bind(getItem(position))

    class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        val offerIds = hashMapOf(
            "temporary_job" to R.drawable.ic_green_list,
            "level_up_resume" to R.drawable.ic_green_star,
            //В макете я не нашёл нужную иконку поэтому использую эту
            "near_vacancies" to R.drawable.ic_blue_person
        )
    }

}