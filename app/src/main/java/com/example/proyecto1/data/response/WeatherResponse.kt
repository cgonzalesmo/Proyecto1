package com.example.proyecto1.data.response


import com.example.proyecto1.model.Current
import com.example.proyecto1.model.Forecast
import com.example.proyecto1.model.Location
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: Location
)