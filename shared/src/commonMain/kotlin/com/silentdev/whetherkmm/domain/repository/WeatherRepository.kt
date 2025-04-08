package com.silentdev.whetherkmm.domain.repository

import com.silentdev.whetherkmm.persentation.state.WeatherUiState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<WeatherUiState>
}
