package com.example.weathertracker.ui.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weathertracker.data.model.City

@Composable
fun CitiesList(
    data: List<City>,
    cityDetailsClicked: (City) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = data,
            key = { city ->
                city.id
            }
        ) { city ->
            CityItem(
                city = city,
                cityDetailsClicked = cityDetailsClicked
            )
        }
    }
}
