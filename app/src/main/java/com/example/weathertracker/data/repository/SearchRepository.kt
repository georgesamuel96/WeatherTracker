package com.example.weathertracker.data.repository

import com.example.weathertracker.data.model.City
import com.example.weathertracker.data.model.CityDataResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class SearchRepository @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun getLastCitySearch(): Flow<City> {
        return dataStoreDataSource.readCityData
    }

    suspend fun saveCityData(city: City) {
        dataStoreDataSource.persistStoreCityName(city.name)
        dataStoreDataSource.persistStoreTemperatureUrl(city.url)
        dataStoreDataSource.persistStoreTemperature(city.temperature)
        dataStoreDataSource.persistStoreHumidity(city.humidity)
        dataStoreDataSource.persistStoreUV(city.uv)
        dataStoreDataSource.persistStoreFeelsLike(city.feelsLike)
    }

    fun searchOn(cityName: String): Flow<List<City>> {
        return remoteDataSource.searchOn(cityName)
    }

    suspend fun getCityData(latitude: Double, longitude: Double): CityDataResponse {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getCityData(
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}