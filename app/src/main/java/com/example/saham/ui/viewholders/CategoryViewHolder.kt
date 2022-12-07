package com.example.saham.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.saham.databinding.ItemCategoryBinding
import com.example.saham.model.Category

class CategoryViewHolder(private var binding: ItemCategoryBinding) : ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.category = category
    }
}