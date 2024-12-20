package com.example.weathertracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weathertracker.R
import com.example.weathertracker.ui.theme.CardColor
import com.example.weathertracker.ui.theme.TitleDataColor
import com.example.weathertracker.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val emptySearchData by homeViewModel.emptySearchData.collectAsState()
    val cityData by homeViewModel.cityData.collectAsState()
    val searchList by homeViewModel.citiesList.collectAsState()

    homeViewModel.getLastSearchData()

    var searchValue: String by remember {
        mutableStateOf("")
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = Color.White)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                value = searchValue,
                onValueChange = {
                    searchValue = it
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.labelLarge,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        homeViewModel.searchOn(searchValue)
                    }
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        homeViewModel.searchOn(searchValue)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.search_icon)
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_location),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = TitleDataColor
                        )
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = CardColor,
                    unfocusedIndicatorColor = CardColor,
                    unfocusedContainerColor = CardColor,
                    focusedContainerColor = CardColor
                )
            )

            HomeContent(
                isEmptyData = emptySearchData,
                city = cityData,
                searchList = searchList,
                cityDetailsClicked = { city ->
                    homeViewModel.saveCityData(city)
                }
            )
        }
    }
}