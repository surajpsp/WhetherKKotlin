package com.silentdev.whetherkmm.domain.model

data class WeatherInfo(
    val current: CurrentWeatherInfo,
    val forecast: List<ForecastDay>
)

data class CurrentWeatherInfo(
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val code: Int,
    val time: String
)

data class ForecastDay(
    val date: String,
    val tempMax: Double,
    val tempMin: Double,
    val code: Int
)
