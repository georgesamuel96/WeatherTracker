package com.example.weathertracker.data.model

import com.google.gson.annotations.SerializedName

data class CityDataResponse(
    val current: Current
)

data class Current(
    @SerializedName("temp_f")
    val temperature: Double,
    val condition: Condition,
    @SerializedName("feelslike_f")
    val feelsLike: Double,
    val humidity: Int,
    val uv: Double
)

data class Condition(
    val icon: String
)
