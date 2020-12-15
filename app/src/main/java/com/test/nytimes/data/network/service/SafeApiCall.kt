package com.test.nytimes.data.network.service

sealed class SafeApiCall<out T : Any> {
    class Success<out T : Any>(val data: T) : SafeApiCall<T>()
    class Error(val exception: Throwable) : SafeApiCall<Nothing>()
}