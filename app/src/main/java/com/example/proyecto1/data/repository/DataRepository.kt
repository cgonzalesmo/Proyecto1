package com.example.proyecto1.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.proyecto1.data.local.Locations
import com.example.proyecto1.data.local.LocationsDao
import com.example.proyecto1.data.remote.ApiService
import com.example.proyecto1.data.response.WeatherResponse
import com.example.proyecto1.util.Constants.WEATHER_LOCATION
import com.example.proyecto1.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class DataRepository(
    private val api: ApiService,
    private val dao: LocationsDao,
    private val sharedPreferences: SharedPreferences
) {

    val currentlySelectedLocation =
        MutableStateFlow(sharedPreferences.getString(WEATHER_LOCATION, "Arequipa"))

    suspend fun getWeatherData(location: String): Resource<WeatherResponse> {
        return try {
            Resource.Success(
                data = api.getWeatherData(location)
            )
        } catch (e: IOException) {
            return Resource.Error(
                message = "¡Ups! No se pudo conectar con el servidor, verifique su conexión a Internet."
            )
        } catch (e: HttpException) {
            return Resource.Error(
                message = "¡Ups! algo salió mal. Inténtalo de nuevo"
            )
        }
    }

    suspend fun insertLocation(locations: Locations) {
        dao.insertLocation(locations)
    }

    suspend fun updateLocation(locations: Locations) {
        dao.updateLocation(locations)
    }

    suspend fun deleteLocation(locations: Locations) {
        dao.deleteLocation(locations)
    }

    fun getAllLocations(): LiveData<List<Locations>> {
        return dao.getAllLocations()
    }

    fun saveToSharedPrefs(locationName: String) {
        sharedPreferences.edit().putString(WEATHER_LOCATION, locationName).apply()
        currentlySelectedLocation.value = locationName
    }
}