package com.silentdev.whetherkmm.domain.repository

import com.silentdev.whetherkmm.data.repository.Repository
import com.silentdev.whetherkmm.domain.mapper.toCurrentWeatherInfo
import com.silentdev.whetherkmm.domain.mapper.toForecastDay
import com.silentdev.whetherkmm.domain.model.WeatherInfo
import com.silentdev.whetherkmm.persentation.state.WeatherUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class WeatherRepositoryImpl(
    private val repository: Repository
) : WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<WeatherUiState> = channelFlow {
        send(WeatherUiState.Loading)
        repository.getWeatherData(
            latitude, longitude
        ).collectLatest { data ->
            if (data == null) {
                send(WeatherUiState.Error("No data found"))
            } else {
                send(
                    WeatherUiState.Success(
                        WeatherInfo(
                    current = data.first.toCurrentWeatherInfo(),
                    forecast = data.second.map { it.toForecastDay() }
                )))
            }
        }
    }
}
