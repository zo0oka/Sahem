package com.example.sahem.remote

import com.example.sahem.helpers.Constants.BASE_API_URL
import com.example.sahem.helpers.Constants.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
//        private val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()

        private val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(0, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("x-api-key", "3oIQuVPK1Y7Hap4feCid7la1lkbMidZB")
                    .addHeader("Accept-Language", "en")
                    .build()
                it.proceed(request)
            }
            .writeTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .callTimeout(0, TimeUnit.SECONDS)
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_API_URL)
            .client(client)
            .build()

        val getInstance: ApiService by lazy { retrofit.create(ApiService::class.java) }
    }
}
