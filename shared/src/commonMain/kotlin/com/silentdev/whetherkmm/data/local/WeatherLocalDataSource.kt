package com.silentdev.whetherkmm.data.local

import com.silentdev.whetherkmm.WeatherDatabase
import com.silentdev.whetherkmm.data.mapper.toDomain
import com.silentdev.whetherkmm.domain.model.WeatherInfo

class WeatherLocalDataSource(
    private val database: WeatherDatabase
) {

    private val weatherQueries = database.weatherCacheQueries
    private val forecastQueries = database.weatherCacheQueries

    fun getLastWeather(): WeatherInfo? {
        val current = weatherQueries.selectLast().executeAsOneOrNull()?.toDomain()
        val forecast = forecastQueries.selectAllForecast()
            .executeAsList()
            .map { it.toDomain() }

        return current?.copy(forecast = forecast)
    }

    fun saveWeather(weather: WeatherInfo) {
        // Save current
        weatherQueries.clear()
        weatherQueries.insertWeather(
            temperature = weather.current.temperature,
            windSpeed = weather.current.windSpeed,
            windDirection = weather.current.windDirection,
            code = weather.current.code.toLong(),
            time = weather.current.time
        )

        // Save forecast
        forecastQueries.clearForecast()
        weather.forecast.forEach { day ->
            forecastQueries.insertForecast(
                date = day.date,
                tempMax = day.tempMax,
                tempMin = day.tempMin,
                code = day.code.toLong()
            )
        }
    }
}

