package com.silentdev.whetherkmm.data.remote

import com.silentdev.whetherkmm.data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApiServiceImpl(
    private val client: HttpClient
) : WeatherApiService {

    companion object {
        private const val WEATHER_API = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=40.71&" +
                "longitude=-74.01&" +
                "current_weather=true&" +
                "daily=temperature_2m_max,temperature_2m_min,weathercode&" +
                "timezone=auto"
    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        return client.get(WEATHER_API).body()
    }
}
