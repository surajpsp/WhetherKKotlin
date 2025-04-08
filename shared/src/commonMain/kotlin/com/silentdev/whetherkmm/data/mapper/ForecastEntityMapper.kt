package com.silentdev.whetherkmm.data.mapper


import com.silentdev.whetherkmm.data.model.ForecastCacheEntity
import com.silentdev.whetherkmm.data.model.WeatherResponse

fun WeatherResponse.toForecastCacheList(): List<ForecastCacheEntity> {
    return dailyForecast.time.mapIndexed { index, date ->
        ForecastCacheEntity(
            date = date,
            tempMax = dailyForecast.temperatureMax.getOrNull(index) ?: 0.0,
            tempMin = dailyForecast.temperatureMin.getOrNull(index) ?: 0.0,
            code = dailyForecast.weathercode.getOrNull(index) ?: 0
        )
    }
}
