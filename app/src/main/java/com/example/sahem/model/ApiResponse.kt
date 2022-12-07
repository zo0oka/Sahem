package com.example.sahem.model

import com.example.sahem.enums.ApiStatus

data class ApiResponse<out T>(val status: ApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(status = ApiStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResponse<T> =
            ApiResponse(status = ApiStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiResponse<T> =
            ApiResponse(status = ApiStatus.LOADING, data = data, message = null)
    }
}