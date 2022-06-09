package com.example.proyecto1.screens.settings


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.local.Locations
import com.example.proyecto1.data.notes.Notes
import com.example.proyecto1.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _selectedLocation = mutableStateOf("")
    val selectedLocation: State<String> = _selectedLocation

    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    private val _textFieldDesValue = mutableStateOf("")
    val textFieldDesValue: State<String> = _textFieldDesValue

    private val _idCurrent = mutableStateOf(1)
    val idCurrent: State<Int> = _idCurrent

    val locations = repository.getAllLocations()

    init {
        viewModelScope.launch {
            repository.currentlySelectedLocation.collect { loc ->
                _selectedLocation.value = loc.toString()
            }
        }
    }

    fun savedToSharedPrefs(locationName: String) {
        repository.saveToSharedPrefs(locationName)
    }

    fun setTextFieldValue(value: String) {
        _textFieldValue.value = value
    }

    fun setTextFieldDesValue(value: String) {
        _textFieldDesValue.value = value
    }

    fun setIdCurrent(value: Int){
        _idCurrent.value = value
    }

    fun insertLocation() {
        viewModelScope.launch {
            if (textFieldValue.value.isBlank()) {
                return@launch
            }
            repository.insertLocation(Locations(textFieldValue.value,textFieldDesValue.value,"",""))
        }
    }

    fun editLocation() {
        viewModelScope.launch {
            repository.updateLocation(Locations(textFieldValue.value, textFieldDesValue.value, "", "", idCurrent.value))
        }
    }
    fun deleteLocation(){
        viewModelScope.launch {
            repository.deleteLocation(Locations("", "", "", "", idCurrent.value))
        }
    }
}