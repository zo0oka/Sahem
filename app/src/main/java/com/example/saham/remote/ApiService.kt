package com.example.saham.remote

import com.example.saham.helpers.Constants.BEST_OFFERS_PATH
import com.example.saham.helpers.Constants.CATEGORIES_PATH
import com.example.saham.helpers.Constants.TOP_RATED_PATH
import com.example.saham.model.Category
import com.example.saham.model.DataResponse
import com.example.saham.model.Offer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(CATEGORIES_PATH)
    suspend fun getCategories(): DataResponse<Category>

    @GET(TOP_RATED_PATH)
    suspend fun getTopRated(
        @Query("latitude") latitude: Double, @Query("longitude") longitude: Double
    ): DataResponse<Offer>

    @GET(BEST_OFFERS_PATH)
    suspend fun getBestOffers(
        @Query("latitude") latitude: Double, @Query("longitude") longitude: Double
    ): DataResponse<Offer>
}