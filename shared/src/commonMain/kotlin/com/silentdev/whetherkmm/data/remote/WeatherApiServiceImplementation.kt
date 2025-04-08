package com.silentdev.whetherkmm.data.remote

import com.silentdev.whetherkmm.data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApiServiceImpl(
    private val client: HttpClient
) : WeatherApiService {

    companion object {
        private const val WEATHER_API = "https://api.open-meteo.com/v1/forecast"
        private const val OTHER_KEY =
            "&current=temperature_2m,weathercode,uv_index,relative_humidity_2m,precipitation" +
                    "&daily=weathercode,temperature_2m_max,temperature_2m_min,uv_index_max,precipitation_sum" +
                    "&timezone=auto"

    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        return client.get(
            "$WEATHER_API?latitude=$latitude&longitude=$longitude$OTHER_KEY"
        ).body()
    }
}
