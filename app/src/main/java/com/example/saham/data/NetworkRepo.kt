package com.example.saham.data

import com.example.saham.remote.ApiClient

class NetworkRepo {
    suspend fun getCategories() = ApiClient.getInstance.getCategories()
    suspend fun getTopRated(latitude: Double, longitude: Double) =
        ApiClient.getInstance.getTopRated(latitude, longitude)

    suspend fun getBestOffers(latitude: Double, longitude: Double) =
        ApiClient.getInstance.getBestOffers(latitude, longitude)
}