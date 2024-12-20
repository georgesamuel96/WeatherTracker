package com.example.weathertracker.util

import android.content.Context
import com.example.weathertracker.R
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

object Utils {

    fun Throwable.handleAPIError(context: Context): String {
        val errorMessage = when (this) {
            is IOException -> context.getString(R.string.no_internet_connection)
            is TimeoutException -> context.getString(R.string.timeout_error)
            is HttpException -> context.getString(
                R.string.server_error,
                this.code(),
                this.message() ?: context.getString(R.string.error)
            )

            else -> context.getString(
                R.string.unexpected_error,
                this.message ?: context.getString(R.string.error)
            )
        }

        return errorMessage
    }
}