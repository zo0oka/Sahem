package com.example.sahem.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sahem.databinding.ItemCategoryBinding
import com.example.sahem.model.Category

class CategoryViewHolder(private var binding: ItemCategoryBinding) : ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.category = category
    }
}