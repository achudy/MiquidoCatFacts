package com.chudy.miquidocatfacts.networking

/**
 * Class for handling responses - successful or ones where there was an exception
 * (or an HttpException in particular).
 * Its functions return a Resource object with successful data obtained or a message
 * when there was an exception.
 */
open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(code: Int): Resource<T> {
        return Resource.error(getErrorMessage(code), null)
    }

    fun <T : Any> handleException(exception: Exception): Resource<T> {
        return Resource.error(
            "Something went wrong.\nYou might want to check your connection and refresh.",
            null
        )
    }

    private fun getErrorMessage(httpCode: Int): String {
        return when (httpCode) {
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}