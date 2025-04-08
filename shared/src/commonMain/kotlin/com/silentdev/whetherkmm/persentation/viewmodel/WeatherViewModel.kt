package com.silentdev.whetherkmm.persentation.viewmodel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.silentdev.whetherkmm.domain.repository.WeatherRepositoryImpl
import com.silentdev.whetherkmm.persentation.state.WeatherUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepositoryImpl
) {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)

    @NativeCoroutinesState
    val weatherState: StateFlow<WeatherUiState> get() = _uiState


    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            repository.getWeatherData(
                latitude = lat,
                longitude = lon
            ).collectLatest {
                _uiState.value = it
            }
        }
    }

    fun clearTask() {
        viewModelScope.cancel()
    }
}


