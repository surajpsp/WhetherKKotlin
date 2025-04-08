package com.silentdev.whetherkmm.persentation.state

import com.silentdev.whetherkmm.domain.model.WeatherInfo

sealed class WeatherUiState {
    data object Idle : WeatherUiState()
    data object Loading : WeatherUiState()
    data class Success(val weather: WeatherInfo) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
