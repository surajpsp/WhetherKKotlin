package com.silentdev.whetherkmm.persentation.state

import com.silentdev.whetherkmm.domain.model.WeatherInfo

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: WeatherInfo? = null,
    val error: String? = null
)
