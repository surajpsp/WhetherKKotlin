package com.silentdev.whetherkmm.data.remote

import com.silentdev.whetherkmm.data.model.WeatherResponse

interface WeatherApiService {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse
}
