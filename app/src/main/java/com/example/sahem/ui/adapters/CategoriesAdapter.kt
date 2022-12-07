package com.example.sahem.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sahem.databinding.ItemCategoryBinding
import com.example.sahem.model.Category
import com.example.sahem.ui.viewholders.CategoryViewHolder

class CategoriesAdapter : RecyclerView.Adapter<CategoryViewHolder>() {
    private var categories = listOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }
}