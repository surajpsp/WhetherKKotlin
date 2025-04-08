package com.silentdev.whetherkmm.domain.mapper

import com.silentdev.whetherkmm.ForecastCache
import com.silentdev.whetherkmm.WeatherCache
import com.silentdev.whetherkmm.core.util.toFormattedDate
import com.silentdev.whetherkmm.domain.model.CurrentWeatherInfo
import com.silentdev.whetherkmm.domain.model.ForecastDay
import com.silentdev.whetherkmm.domain.model.WeatherInfo
import kotlin.math.roundToInt

fun Pair<WeatherCache?, List<ForecastCache>>.toDomainModel(): WeatherInfo {
    val (weatherCache, forecastCacheList) = this

    return WeatherInfo(
        current = weatherCache.toCurrentWeatherInfo(),
        forecast = forecastCacheList.map { it.toForecastDay() }
    )
}

fun WeatherCache?.toCurrentWeatherInfo(): CurrentWeatherInfo {
    return CurrentWeatherInfo(
        temperatureCelsius = "${this?.temperature?.toInt() ?: "--"}°C",
        temperatureFahrenheit = this?.temperature?.let { "${(it * 9 / 5 + 32).toInt()}°F" }
            ?: "--°F",
        location = "New Delhi", // You can enhance this by adding reverse geocoding
        weatherInfo = getWeatherDescription(this?.code?.toInt()),
        uv = "--", // You can extend WeatherCache to store UV if needed
        humidity = "--", // Extend WeatherCache if needed
        precipitation = "--" // Extend WeatherCache if needed
    )
}

fun ForecastCache.toForecastDay(): ForecastDay {
    return ForecastDay(
        date = this.date.toFormattedDate(),
        temperatureCelsius = "${this.tempMax?.toInt()}°/${this.tempMin?.toInt()}°C",
        temperatureFahrenheit = "${((this.tempMax?.roundToInt() ?: 0) * 9 / 5 + 32).toInt()}°/${((this.tempMin?.roundToInt() ?: 0) * 9 / 5 + 32).toInt()}°F",
        weatherInfo = getWeatherDescription(this.code?.toInt())
    )
}

fun getWeatherDescription(code: Int?): String {
    return when (code) {
        0 -> "Clear sky"
        1, 2, 3 -> "Partly cloudy"
        45, 48 -> "Foggy"
        51, 53, 55 -> "Drizzle"
        61, 63, 65 -> "Rainy"
        66, 67 -> "Freezing rain"
        71, 73, 75 -> "Snowfall"
        80, 81, 82 -> "Rain showers"
        95 -> "Thunderstorm"
        else -> "Unknown"
    }
}

