package com.silentdev.whetherkmm.core.di

import app.cash.sqldelight.db.SqlDriver
import com.silentdev.whetherkmm.WeatherDatabase
import com.silentdev.whetherkmm.core.remote.KtorApi
import com.silentdev.whetherkmm.data.local.WeatherLocalDataSource
import com.silentdev.whetherkmm.data.remote.WeatherApiServiceImpl
import com.silentdev.whetherkmm.data.repository.Repository
import com.silentdev.whetherkmm.domain.repository.WeatherRepositoryImpl
import com.silentdev.whetherkmm.persentation.viewmodel.WeatherViewModel
import org.koin.dsl.module

val commonModule = module {
    single { KtorApi().client }
    single<WeatherDatabase> {
        // Initialize the database driver based on the platform
        val driver: SqlDriver = get()
        WeatherDatabase(driver)
    }
    single { WeatherApiServiceImpl(get()) }
    single { WeatherLocalDataSource(get()) }
    single { Repository(get(), get()) }
    single { WeatherRepositoryImpl(get()) }
    factory { WeatherViewModel(get()) }
}



