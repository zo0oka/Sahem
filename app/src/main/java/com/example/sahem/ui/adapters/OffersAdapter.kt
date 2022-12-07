package com.example.sahem.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sahem.databinding.ItemBestRatingBinding
import com.example.sahem.databinding.ItemCategoryBinding
import com.example.sahem.databinding.ItemOfferBinding
import com.example.sahem.enums.OfferType
import com.example.sahem.model.Category
import com.example.sahem.model.Offer
import com.example.sahem.ui.viewholders.CategoryViewHolder
import com.example.sahem.ui.viewholders.OfferViewHolder
import com.example.sahem.ui.viewholders.TopRatedViewHolder

class OffersAdapter(private val type: OfferType) : RecyclerView.Adapter<ViewHolder>() {
    private var offers = listOf<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (type) {
            OfferType.TOP_RATED -> {
                val binding = ItemBestRatingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TopRatedViewHolder(binding)
            }
            OfferType.BEST_OFFER -> {
                val binding =
                    ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OfferViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(type) {
            OfferType.TOP_RATED -> (holder as TopRatedViewHolder).bind(offers[position])
            OfferType.BEST_OFFER -> (holder as OfferViewHolder).bind(offers[position])
        }
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOffers(offers: List<Offer>) {
        this.offers = offers
        notifyDataSetChanged()
    }
}