package com.silentdev.whetherkmm.data.mapper

import com.silentdev.whetherkmm.data.model.WeatherResponse
import com.silentdev.whetherkmm.domain.model.CurrentWeatherInfo
import com.silentdev.whetherkmm.domain.model.ForecastDay
import com.silentdev.whetherkmm.domain.model.WeatherInfo

fun WeatherResponse.toDomain(): WeatherInfo {
    val forecast = dailyForecast.time.indices.map { index ->
        ForecastDay(
            date = dailyForecast.time[index],
            tempMax = dailyForecast.tempMax[index],
            tempMin = dailyForecast.tempMin[index],
            code = dailyForecast.weatherCode[index]
        )
    }

    return WeatherInfo(
        current = CurrentWeatherInfo(
            temperature = currentWeather.temperature,
            windSpeed = currentWeather.windSpeed,
            windDirection = currentWeather.windDirection,
            code = currentWeather.weatherCode,
            time = currentWeather.time
        ),
        forecast = forecast
    )
}
