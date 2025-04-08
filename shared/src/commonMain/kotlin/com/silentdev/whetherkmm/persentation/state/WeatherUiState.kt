package com.silentdev.whetherkmm.persentation.state

import com.silentdev.whetherkmm.domain.model.WeatherInfo
import kotlinx.serialization.Serializable

@Serializable
sealed class WeatherUiState {
    @Serializable
    data object Idle : WeatherUiState()

    @Serializable
    data object Loading : WeatherUiState()

    @Serializable
    data class Success(val weather: WeatherInfo) : WeatherUiState()

    @Serializable
    data class Error(val message: String) : WeatherUiState()
}
