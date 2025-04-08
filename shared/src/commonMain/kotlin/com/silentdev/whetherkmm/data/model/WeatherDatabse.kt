package com.silentdev.whetherkmm.data.model

data class WeatherCacheEntity(
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val code: Int,
    val time: String
)

data class ForecastCacheEntity(
    val date: String,
    val tempMax: Double,
    val tempMin: Double,
    val code: Int
)