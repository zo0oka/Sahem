package com.example.saham.model

import com.google.gson.annotations.SerializedName


data class Menu(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("product") val product: Product?
)

