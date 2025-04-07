package com.silentdev.whetherkmm.persentation.viewmodel

import com.silentdev.whetherkmm.data.repository.WetherRepository
import com.silentdev.whetherkmm.persentation.state.WeatherUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeatherViewModel(
    private val repository: WetherRepository
) {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState(isLoading = true)

            try {
                val result = repository.getWeather(lat, lon)
                _uiState.value = WeatherUiState(weather = result)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState(error = e.message ?: "Unknown error")
            }
        }
    }
}
