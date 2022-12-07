package com.example.sahem.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sahem.R
import com.example.sahem.databinding.FragmentHomeBinding
import com.example.sahem.enums.OfferType
import com.example.sahem.ui.adapters.CategoriesAdapter
import com.example.sahem.ui.adapters.OffersAdapter
import com.example.sahem.viewmodels.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesAdapter = CategoriesAdapter()
        binding.categoriesRecyclerView.adapter = categoriesAdapter

        val bestRatingAdapter = OffersAdapter(OfferType.TOP_RATED)
        binding.bestRatingRecyclerView.adapter = bestRatingAdapter

        val bestOffersAdapter = OffersAdapter(OfferType.BEST_OFFER)
        binding.bestOffersRecyclerView.adapter = bestOffersAdapter

        viewModel.categories.observe(viewLifecycleOwner){
            it.let {
                Log.e("Categories","Size -> ${it.size}")
                categoriesAdapter.setCategories(it)
            }
        }
        viewModel.topRated.observe(viewLifecycleOwner){
            it.let {
                Log.e("Top Rated","Size -> ${it.size}")
                bestRatingAdapter.setOffers(it)
            }
        }
        viewModel.bestOffers.observe(viewLifecycleOwner){
            it.let {
                Log.e("Best Offers","Size -> ${it.size}")
                bestOffersAdapter.setOffers(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}