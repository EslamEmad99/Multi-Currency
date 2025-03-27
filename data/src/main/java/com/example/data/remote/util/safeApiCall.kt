package com.example.data.remote.util

import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
    return try {
        val response = apiCall()
        response.body()?.let {
            if (response.isSuccessful) Result.success(it)
            else Result.failure(ApiExceptions.fromCode(response.code()))
        } ?: Result.failure(ApiExceptions.EmptyResponseException)
    } catch (e: Exception) {
        Result.failure(ApiExceptions.fromException(e))
    }
}
