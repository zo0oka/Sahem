package com.example.sahem.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahem.data.NetworkRepo
import com.example.sahem.enums.ApiStatus
import com.example.sahem.model.Category
import com.example.sahem.model.Offer
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _topRated = MutableLiveData<List<Offer>>()
    private val _bestOffers = MutableLiveData<List<Offer>>()
    private val _apiStatus = MutableLiveData<ApiStatus>()
    private val repo = NetworkRepo()

    val categories: LiveData<List<Category>> = _categories
    val topRated: LiveData<List<Offer>> = _topRated
    val bestOffers: LiveData<List<Offer>> = _bestOffers
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    init {
        getCategories()
        getTopRated()
        getBestOffers()
    }

    private fun getCategories() {
        _apiStatus.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                val categoriesResponse = repo.getCategories()
                _categories.value = categoriesResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
            }
        }
    }

    private fun getTopRated() {
        _apiStatus.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                val topRatedResponse = repo.getTopRated()
                _topRated.value = topRatedResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
            }
        }
    }

    private fun getBestOffers() {
        _apiStatus.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                val bestOffersResponse = repo.getBestOffers()
                _bestOffers.value = bestOffersResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
            }
        }
    }
}