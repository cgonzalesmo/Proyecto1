package com.example.proyecto1.screens.search


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.repository.DataRepository
import com.example.proyecto1.screens.home.HomeState
import com.example.proyecto1.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }

    private val _searchResult = mutableStateOf(HomeState())
    val searchSearch: State<HomeState> = _searchResult

    fun searchAll(searchParam: String) {
        _searchTerm.value = searchParam
        viewModelScope.launch {
            _searchResult.value = searchSearch.value.copy(
                isLoading = true
            )
            when (val result = repository.getWeatherData(searchTerm.value)) {
                is Resource.Success -> {
                    _searchResult.value = searchSearch.value.copy(
                        data = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _searchResult.value = searchSearch.value.copy(
                        isLoading = false
                    )
                }
                else -> {}
            }
        }
    }

}