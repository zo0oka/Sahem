package com.example.sahem.model

import com.google.gson.annotations.SerializedName


data class Offer(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("hotline") val hotline: String?,
    @SerializedName("max_delivery_time") val maxDeliveryTime: Int?,
    @SerializedName("min_order_charge") val minOrderCharge: Int?,
    @SerializedName("logo") val logo: String?,
    @SerializedName("logo_thumb") val logoThumb: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("distance") val distance: Double?,
    @SerializedName("delivery_fee") val deliveryFee: Int?,
    @SerializedName("avg_rating") val avgRating: Double?,
    @SerializedName("cover") val cover: String?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("category") val category: Category?,
    @SerializedName("menu") val menu: List<Menu>?
)