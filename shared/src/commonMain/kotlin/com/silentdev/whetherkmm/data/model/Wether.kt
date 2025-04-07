package com.silentdev.whetherkmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("current_weather")
    val currentWeather: CurrentWeather,
    @SerialName("daily")
    val dailyForecast: DailyForecast
)

@Serializable
data class CurrentWeather(
    @SerialName("temperature") val temperature: Double,
    @SerialName("windspeed") val windSpeed: Double,
    @SerialName("winddirection") val windDirection: Double,
    @SerialName("weathercode") val weatherCode: Int,
    @SerialName("time") val time: String
)

@Serializable
data class DailyForecast(
    val time: List<String>,
    @SerialName("temperature_2m_max") val tempMax: List<Double>,
    @SerialName("temperature_2m_min") val tempMin: List<Double>,
    @SerialName("weathercode") val weatherCode: List<Int>
)
