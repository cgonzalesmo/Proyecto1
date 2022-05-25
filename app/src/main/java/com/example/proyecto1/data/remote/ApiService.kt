package com.example.proyecto1.data.remote

import com.example.proyecto1.data.response.WeatherResponse
import com.example.proyecto1.BuildConfig.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("q") searchString: String,
        @Query("days") days: Int = 7,
        @Query("key") key: String = API_KEY,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ): WeatherResponse
}