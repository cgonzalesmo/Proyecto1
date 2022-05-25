package com.example.proyecto1.screens.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.repository.DataRepository
import com.example.proyecto1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private fun getNotifyFlow(): StateFlow<String?> = repository.currentlySelectedLocation

    init {
        viewModelScope.launch {
            getNotifyFlow().collect { location ->
                location?.let { getWeatherData(it) }
            }
        }
    }

    private fun getWeatherData(location: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            when (val result = repository.getWeatherData(location)) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        data = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
                else -> {}
            }
        }
    }
}