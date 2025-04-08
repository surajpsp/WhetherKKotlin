package com.silentdev.whetherkmm.domain.model

data class WeatherInfo(
    val current: CurrentWeatherInfo,
    val forecast: List<ForecastDay>
)

data class CurrentWeatherInfo(
    val temperatureCelsius: String,
    val temperatureFahrenheit: String,
    val location: String,
    val weatherInfo: String,
    val uv: String,
    val humidity: String,
    val precipitation: String
)

data class ForecastDay(
    val date: String,
    val temperatureCelsius: String,
    val temperatureFahrenheit: String,
    val weatherInfo: String
)
