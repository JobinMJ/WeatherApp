package com.jobin.weatherapp.domain.util

sealed class QueryResponse<T> {
    data class Success<T>(val data: T) : QueryResponse<T>()
    data class Error<T>(val error: Throwable) : QueryResponse<T>()
}