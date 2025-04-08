package com.silentdev.whetherkmm.data.local

import com.silentdev.whetherkmm.ForecastCache
import com.silentdev.whetherkmm.WeatherCache
import com.silentdev.whetherkmm.WeatherDatabase
import com.silentdev.whetherkmm.data.model.ForecastCacheEntity
import com.silentdev.whetherkmm.data.model.WeatherCacheEntity

class WeatherLocalDataSource(
    database: WeatherDatabase
) {

    private val weatherQueries = database.weatherCacheQueries

    fun saveWeather(weather: WeatherCacheEntity) {
        weatherQueries.clear()
        weatherQueries.insertWeather(
            temperature = weather.temperature,
            windSpeed = weather.windSpeed,
            windDirection = weather.windDirection,
            code = weather.code.toLong(),
            time = weather.time
        )
    }

    fun saveForecast(forecastList: List<ForecastCacheEntity>) {
        weatherQueries.clearForecast()
        forecastList.forEach {
            weatherQueries.insertForecast(
                date = it.date,
                tempMax = it.tempMax,
                tempMin = it.tempMin,
                code = it.code.toLong()
            )
        }
    }

    fun getLastWeather(): WeatherCache? =
        weatherQueries.selectLast().executeAsOneOrNull()

    fun getAllForecast(): List<ForecastCache> =
        weatherQueries.selectAllForecast().executeAsList()


}

