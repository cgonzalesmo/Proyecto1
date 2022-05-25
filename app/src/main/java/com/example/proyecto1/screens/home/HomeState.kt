package com.example.proyecto1.screens.home

import com.example.proyecto1.data.response.WeatherResponse

data class HomeState(
    val data: WeatherResponse? = null,
    val isLoading: Boolean = false
)