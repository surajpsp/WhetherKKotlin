package com.silentdev.whetherkmm.data.mapper

import com.silentdev.whetherkmm.data.model.WeatherCacheEntity
import com.silentdev.whetherkmm.data.model.WeatherResponse

fun WeatherResponse.toWeatherCacheEntity(): WeatherCacheEntity {
    return WeatherCacheEntity(
        temperature = currentWeather.temperature,
        windSpeed = 0.0, // If needed, fetch from the API
        windDirection = 0.0, // same here
        code = currentWeather.weatherCode,
        time = dailyForecast.time.firstOrNull() ?: ""
    )
}
