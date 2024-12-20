package com.example.weathertracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathertracker.R
import com.example.weathertracker.ui.theme.TextColor
import com.example.weathertracker.ui.theme.WeatherTrackerTheme

@Composable
fun EmptyHome() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            style = MaterialTheme.typography.bodyLarge
        )
        VerticalDivider(
            modifier = Modifier
                .height(16.dp),
            color = Color.Transparent
        )
        Text(
            text = stringResource(R.string.please_search_for_city),
            style = MaterialTheme.typography.labelLarge
        )

    }
}

@Composable
@Preview
fun EmptyHomePreview() {
    WeatherTrackerTheme {
        EmptyHome()
    }
}