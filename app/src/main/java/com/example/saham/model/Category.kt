package com.example.saham.model

import android.graphics.Color
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("color_code") val colorCode: String?,
    @SerializedName("icon") val icon: String?
) {
    fun getColor(): Int {
        return Color.parseColor(colorCode)
    }
}

