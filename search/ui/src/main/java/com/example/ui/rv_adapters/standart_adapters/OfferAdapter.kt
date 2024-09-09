package com.example.ui.rv_adapters.standart_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entities.Offer
import com.core.ui.R as coreR
import com.example.ui.databinding.OfferItemBinding

class OfferAdapter :
    ListAdapter<Offer, OfferAdapter.OfferViewHolder>(OfferDiffCallback()) {

    class OfferViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            if (offerIds.contains(offer.id)) {
                offerIds[offer.id]?.let { binding.offerIcon.setImageResource(it) }
                if (offer.id == "near_vacancies")
                    binding.offerIcon.setBackgroundResource(coreR.drawable.darker_blue_circle_shape)
            } else {
                binding.offerIcon.visibility = View.INVISIBLE
            }
            if (offer.button == null) {
                binding.offerHeaderTitle.maxLines = 3
                binding.offerHeaderTitle.text = offer.title
                binding.offerActionText.visibility = View.INVISIBLE
            } else {
                binding.offerHeaderTitle.maxLines = 2
                binding.offerHeaderTitle.text = offer.title
                binding.offerActionText.text = offer.button!!.text
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
            "temporary_job" to coreR.drawable.ic_green_list,
            "level_up_resume" to coreR.drawable.ic_green_star,
            //В макете я не нашёл нужную иконку поэтому использую эту
            "near_vacancies" to coreR.drawable.ic_blue_person
        )
    }

}