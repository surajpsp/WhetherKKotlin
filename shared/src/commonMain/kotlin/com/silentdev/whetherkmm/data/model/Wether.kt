package com.silentdev.whetherkmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("current")
    val currentWeather: CurrentWeather,
    @SerialName("daily")
    val dailyForecast: DailyForecast
)

@Serializable
data class CurrentWeather(
    @SerialName("temperature_2m") val temperature: Double,
    @SerialName("weathercode") val weatherCode: Int,
    @SerialName("uv_index") val uvIndex: Double,
    @SerialName("relative_humidity_2m") val humidity: Double,
    @SerialName("precipitation") val precipitation: Double
)

@Serializable
data class DailyForecast(
    val time: List<String>,
    val weathercode: List<Int>,
    @SerialName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min") val temperatureMin: List<Double>,
    @SerialName("uv_index_max") val uvIndexMax: List<Double>,
    @SerialName("precipitation_sum") val precipitationSum: List<Double>
)
