package com.example.sahem.remote

import com.example.sahem.helpers.Constants.BEST_OFFERS_PATH
import com.example.sahem.helpers.Constants.CATEGORIES_PATH
import com.example.sahem.helpers.Constants.TOP_RATED_PATH
import com.example.sahem.model.Category
import com.example.sahem.model.DataResponse
import com.example.sahem.model.Offer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(CATEGORIES_PATH)
    suspend fun getCategories(): DataResponse<Category>

    @GET(TOP_RATED_PATH)
    suspend fun getTopRated(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): DataResponse<Offer>

    @GET(BEST_OFFERS_PATH)
    suspend fun getBestOffers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): DataResponse<Offer>
}