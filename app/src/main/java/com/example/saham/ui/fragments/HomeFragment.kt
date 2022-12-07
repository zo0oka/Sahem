package com.example.saham.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.saham.databinding.FragmentHomeBinding
import com.example.saham.enums.OfferType
import com.example.saham.ui.adapters.CategoriesAdapter
import com.example.saham.ui.adapters.OffersAdapter
import com.example.saham.viewmodels.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val categoriesAdapter = CategoriesAdapter()
        binding.categoriesRecyclerView.adapter = categoriesAdapter

        val bestRatingAdapter = OffersAdapter(OfferType.TOP_RATED)
        binding.bestRatingRecyclerView.adapter = bestRatingAdapter

        val bestOffersAdapter = OffersAdapter(OfferType.BEST_OFFER)
        binding.bestOffersRecyclerView.adapter = bestOffersAdapter

        viewModel.categories.observe(viewLifecycleOwner) {
            it.let {
                Log.e("Categories", "Size -> ${it.size}")
                categoriesAdapter.setCategories(it)
            }
        }
        viewModel.topRated.observe(viewLifecycleOwner) {
            it.let {
                Log.e("Top Rated", "Size -> ${it.size}")
                bestRatingAdapter.setOffers(it)
            }
        }
        viewModel.bestOffers.observe(viewLifecycleOwner) {
            it.let {
                Log.e("Best Offers", "Size -> ${it.size}")
                bestOffersAdapter.setOffers(it)
            }
        }
    }
}