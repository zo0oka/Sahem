package com.example.saham.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.saham.databinding.ItemBestRatingBinding
import com.example.saham.model.Offer

class TopRatedViewHolder(private val binding: ItemBestRatingBinding) : ViewHolder(binding.root) {
    fun bind(offer: Offer) {
        binding.offer = offer
    }
}