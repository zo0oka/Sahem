package com.example.sahem.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sahem.databinding.ItemBestRatingBinding
import com.example.sahem.model.Offer

class TopRatedViewHolder(private val binding: ItemBestRatingBinding) : ViewHolder(binding.root) {
    fun bind(offer: Offer) {
binding.offer = offer
    }
}