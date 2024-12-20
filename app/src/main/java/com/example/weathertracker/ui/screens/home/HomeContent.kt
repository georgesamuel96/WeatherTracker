package com.example.weathertracker.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.weathertracker.data.model.City
import com.example.weathertracker.ui.screens.search.CitiesList
import com.example.weathertracker.ui.screens.search.EmptyList
import com.example.weathertracker.ui.screens.search.ErrorView
import com.example.weathertracker.util.RequestState
import com.example.weathertracker.util.Utils.handleAPIError

@Composable
fun HomeContent(
    isEmptyData: Boolean,
    city: City?,
    searchList: RequestState<List<City>>,
    cityDetailsClicked: (City) -> Unit
) {
    if(isEmptyData) {
        EmptyHome()
    } else{

        // set city persisted data from data store, it the user open app
        // city will be null if the user started search on new city

        if (city != null) {
            CityDetailsScreen(city)
        } else {

            // if the started search
            
            when (searchList) {
                is RequestState.Idle -> {}
                is RequestState.Loading -> {
                    if (searchList.isLoading) {
                        LoadingIndicator()
                    }
                }

                is RequestState.Success -> {
                    if(searchList.data.isEmpty()){
                        EmptyList()
                    } else {
                        CitiesList(
                            data = searchList.data,
                            cityDetailsClicked = cityDetailsClicked
                        )
                    }
                }

                is RequestState.Error -> {
                    ErrorView(searchList.error.handleAPIError(LocalContext.current))
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}