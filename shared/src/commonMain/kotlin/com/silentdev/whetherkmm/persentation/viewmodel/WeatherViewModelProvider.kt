package com.silentdev.whetherkmm.persentation.viewmodel

import org.koin.core.component.KoinComponent

class WeatherViewModelProvider : KoinComponent {
    fun provideWeatherViewModel(): WeatherViewModel {
        return getKoin().get()
    }
}
