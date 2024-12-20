package com.example.weathertracker.ui.viewmodels

import android.net.http.NetworkException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertracker.R
import com.example.weathertracker.data.model.City
import com.example.weathertracker.data.repository.SearchRepository
import com.example.weathertracker.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _emptySearchData: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val emptySearchData: StateFlow<Boolean> = _emptySearchData

    private val _cityData: MutableStateFlow<City?> = MutableStateFlow(null)
    val cityData: StateFlow<City?> = _cityData

    private val _citiesList: MutableStateFlow<RequestState<List<City>>> = MutableStateFlow(RequestState.Idle)
    val citiesList: StateFlow<RequestState<List<City>>> = _citiesList

    fun getLastSearchData() {
        viewModelScope.launch {
            searchRepository.getLastCitySearch()
                .collect { city ->
                    _emptySearchData.value = city.name.isEmpty()
                    _cityData.value = city
                }
        }
    }

    fun saveCityData(city: City) {
        viewModelScope.launch {
            searchRepository.saveCityData(city)
            _cityData.value = city
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun searchOn(cityName: String) {
        if(cityName.isEmpty()) return
        
        _citiesList.value = RequestState.Loading(true)
        _emptySearchData.value = false
        _cityData.value = null

        viewModelScope.launch {
            searchRepository.searchOn(cityName)
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    _citiesList.value = RequestState.Loading(false)

                    _citiesList.value = RequestState.Error(exception)
                }
                .flatMapConcat { list ->
                    flow {
                        val updatedCity = list.map { city ->
                            val data = searchRepository.getCityData(
                                latitude = city.latitude,
                                longitude = city.longitude
                            )
                            city.temperature = data.current.temperature.toInt()
                            city.humidity = data.current.humidity
                            city.uv = data.current.uv
                            city.url = "https:${data.current.condition.icon}"
                            city.feelsLike = data.current.feelsLike.toInt()

                            city
                        }
                        emit(updatedCity)
                    }
                }
                .collect { list ->
                    _citiesList.value = RequestState.Loading(false)

                    _citiesList.value = RequestState.Success(list)
                }
        }
    }
}