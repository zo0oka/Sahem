package com.example.saham.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.saham.databinding.ItemOfferBinding
import com.example.saham.model.Offer

class OfferViewHolder(private val binding: ItemOfferBinding) : ViewHolder(binding.root) {
    fun bind(offer: Offer) {
        binding.offer = offer
    }
}