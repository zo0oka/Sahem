package com.example.sahem.model

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("thumb") val thumb: String?
)

