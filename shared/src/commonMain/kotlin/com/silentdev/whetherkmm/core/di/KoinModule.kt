package com.silentdev.whetherkmm.core.di

import com.silentdev.whetherkmm.core.remote.KtorApi
import com.silentdev.whetherkmm.data.local.WeatherLocalDataSource
import com.silentdev.whetherkmm.data.remote.WeatherApiService
import com.silentdev.whetherkmm.data.remote.WeatherApiServiceImpl
import org.koin.dsl.module

val networkModule = module {
    single { KtorApi().client }
    single<WeatherApiService> { WeatherApiServiceImpl(get()) }
}

val databaseModule = module {
    single { WeatherLocalDataSource(get()) }
}


