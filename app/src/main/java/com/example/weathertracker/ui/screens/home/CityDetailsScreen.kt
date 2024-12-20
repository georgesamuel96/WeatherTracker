package com.example.weathertracker.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathertracker.R
import com.example.weathertracker.data.model.City
import com.example.weathertracker.ui.screens.search.LoadImageURL
import com.example.weathertracker.ui.theme.CardColor
import com.example.weathertracker.ui.theme.DataColor
import com.example.weathertracker.ui.theme.TitleDataColor
import com.example.weathertracker.ui.theme.WeatherTrackerTheme

@Composable
fun CityDetailsScreen(city: City) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 74.dp
            )
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoadImageURL(
            modifier = Modifier
                .size(124.dp),
            imageURL = city.url
        )

        Row(
            modifier = Modifier.padding(top = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.name,
                style = MaterialTheme.typography.bodyLarge,
            )
            Image(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(R.drawable.ic_city),
                contentDescription = stringResource(R.string.icon_city)
            )
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text(
                text = "${city.temperature}",
                style = MaterialTheme.typography.titleLarge
            )

            Image(
                modifier = Modifier
                    .size(24.dp)
                    .padding(top = 16.dp),
                painter = painterResource(R.drawable.ic_temperature),
                contentDescription = stringResource(R.string.icon_temperature)
            )
        }

        Row(
            modifier = Modifier
                .background(
                    color = CardColor
                )
        ) {
            ItemData(
                key = stringResource(R.string.humidity),
                value = "${city.humidity}%"
            )

            ItemData(
                modifier = Modifier
                    .padding(start = 56.dp, end = 56.dp),
                key = stringResource(R.string.uv),
                value = "${city.uv}"
            )

            ItemData(
                key = stringResource(R.string.feels_like),
                value = "${city.feelsLike}",
                showTemperatureIcon = true
            )
        }
    }
}

@Composable
fun ItemData(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
    showTemperatureIcon: Boolean = false
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.labelLarge.copy(
                color = TitleDataColor,
                fontSize = 12.sp
            )
        )

        Row (
            modifier = Modifier.padding(top = 4.dp)
        ){
            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = DataColor
                )
            )

            if(showTemperatureIcon) {
                Image(
                    modifier = Modifier
                        .padding(top = 2.dp, start = 2.dp),
                    painter = painterResource(R.drawable.ic_temperature_grey),
                    contentDescription = stringResource(R.string.icon_temperature)
                )
            }
        }

    }
}

@Composable
@Preview
fun CityDetailsScreenPreview() {
    WeatherTrackerTheme {
        CityDetailsScreen(
            City(
                id = 0,
                name = "City",
                latitude = 0.0,
                longitude = 0.0,
                temperature = 12,
                url = "",
                humidity = 15,
                uv = 0.1,
                feelsLike = 45
            )
        )
    }
}
