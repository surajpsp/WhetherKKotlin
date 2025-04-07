package com.silentdev.whetherkmm.data.mapper

import com.silentdev.whetherkmm.WeatherCache
import com.silentdev.whetherkmm.domain.model.CurrentWeatherInfo
import com.silentdev.whetherkmm.domain.model.WeatherInfo

fun WeatherCache.toDomain(): WeatherInfo {
    return WeatherInfo(
        current = CurrentWeatherInfo(
            temperature = temperature ?: 0.0,
            windSpeed = windSpeed ?: 0.0,
            windDirection = windDirection ?: 0.0,
            code = code ?: 0,
            time = time ?: ""
        ),
        forecast = emptyList() // No forecast cached here
    )
}
