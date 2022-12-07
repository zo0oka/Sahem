package com.example.saham.model

import com.google.gson.annotations.SerializedName


data class DataResponse<T>(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: List<T>?
)