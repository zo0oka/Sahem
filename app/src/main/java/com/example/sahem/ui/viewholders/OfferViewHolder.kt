package com.example.sahem.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sahem.databinding.ItemOfferBinding
import com.example.sahem.model.Offer

class OfferViewHolder(private val binding: ItemOfferBinding) : ViewHolder(binding.root) {
    fun bind(offer: Offer) {
binding.offer = offer
    }
}