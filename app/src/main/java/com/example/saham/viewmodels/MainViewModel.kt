package com.example.saham.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saham.data.NetworkRepo
import com.example.saham.enums.ApiStatus
import com.example.saham.helpers.Constants.LATITUDE
import com.example.saham.helpers.Constants.LONGITUDE
import com.example.saham.model.Category
import com.example.saham.model.Offer
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _topRated = MutableLiveData<List<Offer>>()
    private val _bestOffers = MutableLiveData<List<Offer>>()
    private val _apiStatus = MutableLiveData<ApiStatus>()
    private val repo = NetworkRepo()
    private val _currentLatLng = MutableLiveData<LatLng>()
    private val _selectedAddress = MutableLiveData<Int>()
    private val ksaLocation: LatLng = LatLng(LATITUDE, LONGITUDE)
    private val _loadingCategories = MutableLiveData<Boolean>()
    private val _loadingTopRated = MutableLiveData<Boolean>()
    private val _loadingBestOffers = MutableLiveData<Boolean>()

    val categories: LiveData<List<Category>> = _categories
    val topRated: LiveData<List<Offer>> = _topRated
    val bestOffers: LiveData<List<Offer>> = _bestOffers
    val apiStatus: LiveData<ApiStatus> = _apiStatus
    val currentLatLng: LiveData<LatLng> = _currentLatLng
    val selectedAddress: LiveData<Int> = _selectedAddress
    val loadingCategories: LiveData<Boolean> = _loadingCategories
    val loadingTopRated: LiveData<Boolean> = _loadingTopRated
    val loadingBestOffers: LiveData<Boolean> = _loadingBestOffers

    fun loadData() {
        if (_categories.value?.isEmpty() != false) {
            getCategories()
        }
        getTopRated()
        getBestOffers()
    }

    fun setCurrentLatLng(latLng: LatLng) {
        _currentLatLng.value = latLng
    }

    fun setSelectedAddress(index: Int) {
        _selectedAddress.value = index
        Log.e("Selected Address", index.toString())
        _currentLatLng.value = ksaLocation
        getBestOffers()
        getTopRated()
    }

    private fun getCategories() {
        _apiStatus.value = ApiStatus.LOADING
        _loadingCategories.value = true
        viewModelScope.launch {
            try {
                val categoriesResponse = repo.getCategories()
                _categories.value = categoriesResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
                _loadingCategories.value = false
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _loadingCategories.value = false
            }
        }
    }

    private fun getTopRated() {
        _apiStatus.value = ApiStatus.LOADING
        _loadingTopRated.value = true
        viewModelScope.launch {
            try {
                val topRatedResponse = repo.getTopRated(
                    _currentLatLng.value!!.latitude, _currentLatLng.value!!.longitude
                )
                _topRated.value = topRatedResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
                _loadingTopRated.value = false
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _loadingTopRated.value = false
            }
        }
    }

    private fun getBestOffers() {
        _apiStatus.value = ApiStatus.LOADING
        _loadingBestOffers.value = true
        viewModelScope.launch {
            try {
                val bestOffersResponse = repo.getBestOffers(
                    _currentLatLng.value!!.latitude, _currentLatLng.value!!.longitude
                )
                _bestOffers.value = bestOffersResponse.data ?: listOf()
                _apiStatus.value = ApiStatus.SUCCESS
                _loadingBestOffers.value = false
            } catch (e: java.lang.Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _loadingBestOffers.value = false
            }
        }
    }
}