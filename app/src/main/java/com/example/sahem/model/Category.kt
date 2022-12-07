package com.example.sahem.model

import android.graphics.Color
import android.provider.SyncStateContract
import androidx.core.graphics.toColorInt
import com.example.sahem.R
import com.example.sahem.helpers.Constants
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("color_code") val colorCode: String?,
    @SerializedName("icon") val icon: String?
) {
    fun getColor() : Int {
        return Color.parseColor(colorCode)
    }
}

