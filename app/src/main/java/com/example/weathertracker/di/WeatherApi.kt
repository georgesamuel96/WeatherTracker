package com.example.weathertracker.di

import com.example.weathertracker.data.model.City
import com.example.weathertracker.data.model.CityDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/search.json")
    suspend fun searchOn(
        @Query("q") cityName: String,
        @Query("lang") language: String,
        @Query("key") key: String
    ): List<City>

    @GET("v1/forecast.json")
    suspend fun cityData(
        @Query("q") location: String,
        @Query("key") key: String,
    ): CityDataResponse
}