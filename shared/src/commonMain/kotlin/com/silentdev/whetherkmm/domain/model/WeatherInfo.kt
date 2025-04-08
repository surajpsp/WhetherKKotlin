package com.silentdev.whetherkmm.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val current: CurrentWeatherInfo,
    val forecast: List<ForecastDay>
)

@Serializable
data class CurrentWeatherInfo(
    val temperatureCelsius: String,
    val temperatureFahrenheit: String,
    val location: String,
    val weatherInfo: String,
    val uv: String,
    val humidity: String,
    val precipitation: String
)

@Serializable
data class ForecastDay(
    val date: String,
    val temperatureCelsius: String,
    val temperatureFahrenheit: String,
    val weatherInfo: String
)
