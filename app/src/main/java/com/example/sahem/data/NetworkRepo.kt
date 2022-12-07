package com.example.sahem.data

import com.example.sahem.helpers.Constants.LATITUDE
import com.example.sahem.helpers.Constants.LONGITUDE
import com.example.sahem.remote.ApiClient
import com.example.sahem.remote.ApiService

class NetworkRepo {
    suspend fun getCategories() = ApiClient.getInstance.getCategories()
    suspend fun getTopRated() = ApiClient.getInstance.getTopRated(latitude = LATITUDE, longitude = LONGITUDE)
    suspend fun getBestOffers() = ApiClient.getInstance.getBestOffers(latitude = LATITUDE, longitude = LONGITUDE)
}