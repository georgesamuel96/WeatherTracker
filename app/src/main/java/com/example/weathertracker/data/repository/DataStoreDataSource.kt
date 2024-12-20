package com.example.weathertracker.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weathertracker.data.model.City
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_CITY_NAME
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_FEELS_LIKE
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_HUMIDITY
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_TEMPERATURE
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_TEMPERATURE_URL
import com.example.weathertracker.util.Constants.PREFERENCE_KEY_UV
import com.example.weathertracker.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreDataSource @Inject constructor(
    @ApplicationContext context: Context
) {

    private object PreferencesKey {
        val cityName = stringPreferencesKey(PREFERENCE_KEY_CITY_NAME)
        val temperatureUrl = stringPreferencesKey(PREFERENCE_KEY_TEMPERATURE_URL)
        val temperature = intPreferencesKey(PREFERENCE_KEY_TEMPERATURE)
        val humidity = intPreferencesKey(PREFERENCE_KEY_HUMIDITY)
        val uv = doublePreferencesKey(PREFERENCE_KEY_UV)
        val feelsLike = intPreferencesKey(PREFERENCE_KEY_FEELS_LIKE)
    }

    private val dataStore = context.dataStore

    suspend fun persistStoreCityName(value: String) {
        dataStore.edit { preference ->
            preference[PreferencesKey.cityName] = value
        }
    }

    suspend fun persistStoreTemperatureUrl(value: String) {
        dataStore.edit { preference ->
            preference[PreferencesKey.temperatureUrl] = value
        }
    }

    suspend fun persistStoreTemperature(value: Int) {
        dataStore.edit { preference ->
            preference[PreferencesKey.temperature] = value
        }
    }

    suspend fun persistStoreHumidity(value: Int) {
        dataStore.edit { preference ->
            preference[PreferencesKey.humidity] = value
        }
    }

    suspend fun persistStoreUV(value: Double) {
        dataStore.edit { preference ->
            preference[PreferencesKey.uv] = value
        }
    }

    suspend fun persistStoreFeelsLike(value: Int) {
        dataStore.edit { preference ->
            preference[PreferencesKey.feelsLike] = value
        }
    }

    val readCityData: Flow<City> = dataStore.data
        .catch { exception ->
            emit(emptyPreferences())
        }
        .map { preference ->
            val cityName = preference[PreferencesKey.cityName]?: ""
            val temperatureURL = preference[PreferencesKey.temperatureUrl]?: ""
            val temperature = preference[PreferencesKey.temperature]?: 0
            val humidity = preference[PreferencesKey.humidity]?: 0
            val uv = preference[PreferencesKey.uv]?: 0.0
            val feelsLike = preference[PreferencesKey.feelsLike]?: 0

            City(
                id = 0,
                name = cityName,
                latitude = 0.0,
                longitude = 0.0,
                temperature = temperature.toInt(),
                url = temperatureURL,
                humidity = humidity,
                uv = uv,
                feelsLike = feelsLike
            )
        }
}