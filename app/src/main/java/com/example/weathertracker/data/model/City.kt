package com.example.weathertracker.data.model

import com.google.gson.annotations.SerializedName

data class City(
    val id: Int,
    val name: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    var temperature: Int,
    var url: String,
    var humidity: Int,
    var uv: Double,
    var feelsLike: Int
)
