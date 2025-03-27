package com.example.data.remote.util

sealed class ApiExceptions : Exception() {
    data object EmptyResponseException : ApiExceptions()
    data object UnauthorizedException : ApiExceptions()
    data object NotFoundException : ApiExceptions()
    data object ServerErrorException : ApiExceptions()
    data object UnknownErrorException : ApiExceptions()
    data class NetworkException(val error: Throwable) : ApiExceptions()

    companion object {
        fun fromCode(code: Int): ApiExceptions = when (code) {
            401 -> UnauthorizedException
            404 -> NotFoundException
            500 -> ServerErrorException
            else -> UnknownErrorException
        }

        fun fromException(e: Throwable): ApiExceptions =
            if (e is java.io.IOException) NetworkException(e) else UnknownErrorException
    }
}
