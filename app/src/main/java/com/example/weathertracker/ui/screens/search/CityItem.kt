package com.example.weathertracker.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weathertracker.R
import com.example.weathertracker.data.model.City
import com.example.weathertracker.ui.theme.CardColor
import com.example.weathertracker.ui.theme.WeatherTrackerTheme

@Composable
fun CityItem(
    city: City,
    cityDetailsClicked: (City) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 24.dp, end = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        ),
        onClick = {
            cityDetailsClicked(city)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = city.name,
                    style = MaterialTheme.typography.labelLarge,
                )
                Row {
                    Text(
                        text = "${city.temperature}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Image(
                        modifier = Modifier
                            .padding(top = 12.dp),
                        painter = painterResource(R.drawable.ic_temperature),
                        contentDescription = stringResource(R.string.icon_temperature)
                    )
                }

            }

            LoadImageURL(
                modifier = Modifier.size(64.dp),
                imageURL = city.url
            )
        }
    }
}

@Composable
@Preview
fun CityItemPreview() {
    WeatherTrackerTheme {
        CityItem(
            City(
                id = 0,
                name = "title",
                latitude = 0.0,
                longitude = 0.0,
                temperature = 12,
                url = "",
                humidity = 15,
                uv = 0.1,
                feelsLike = 45
            ),
            cityDetailsClicked = {}
        )
    }
}