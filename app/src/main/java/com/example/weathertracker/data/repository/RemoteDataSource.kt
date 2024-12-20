package com.example.weathertracker.data.repository

import com.example.weathertracker.data.model.City
import com.example.weathertracker.data.model.CityDataResponse
import com.example.weathertracker.di.WeatherApi
import com.example.weathertracker.util.Constants.API_KEY
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class RemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {

    fun searchOn(cityName: String): Flow<List<City>> {
        return flow {
            emit(
                weatherApi.searchOn(
                    cityName = cityName,
                    language = "en",
                    key = API_KEY
                )
            )
        }
    }

    suspend fun getCityData(latitude: Double, longitude: Double): CityDataResponse {
        return weatherApi.cityData(
            location = "$latitude,$longitude",
            key = API_KEY
        )
    }
}