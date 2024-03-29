package com.user.brayan.eltiempo.api

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

sealed class ApiResponse<T>() {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> = ApiErrorResponse(
             "t ${error.message}" ?: "unknown error"
        )
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if  (response.isSuccessful) {
                val body = response.body()

                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body
                    )
                }
            } else {
                val msg = response.errorBody().toString()
                val erroMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }

                ApiErrorResponse(erroMsg ?: "unknown error")
            }
        }
    }
}

class ApiEmptyResponse<T>: ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()
data class ApiErrorResponse<T>(val errorMessage: String): ApiResponse<T>()