package com.silentdev.whetherkmm.data.repository

import com.silentdev.whetherkmm.ForecastCache
import com.silentdev.whetherkmm.WeatherCache
import com.silentdev.whetherkmm.data.local.WeatherLocalDataSource
import com.silentdev.whetherkmm.data.mapper.toForecastCacheList
import com.silentdev.whetherkmm.data.mapper.toWeatherCacheEntity
import com.silentdev.whetherkmm.data.remote.WeatherApiServiceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val remoteDataSource: WeatherApiServiceImpl,
    private val localDataSource: WeatherLocalDataSource
) {

    fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<Pair<WeatherCache?, List<ForecastCache>>?> {
        return flow {
            try {
                emit(Pair(localDataSource.getLastWeather(), localDataSource.getAllForecast()))
                val weather = remoteDataSource.getCurrentWeather(latitude, longitude)
                localDataSource.saveWeather(weather.toWeatherCacheEntity())
                localDataSource.saveForecast(weather.toForecastCacheList())
                emit(Pair(localDataSource.getLastWeather(), localDataSource.getAllForecast()))
            } catch (e: Exception) {
                println("Error fetching remote weather: ${e.message}")
            }
        }
    }
}
