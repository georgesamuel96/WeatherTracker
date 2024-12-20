package com.example.weathertracker.util

sealed class RequestState<out T> {
    object Idle: RequestState<Nothing>()
    data class Loading(val isLoading: Boolean): RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
    data class Error(val error: Throwable): RequestState<Nothing>()
}