package com.silentdev.whetherkmm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.silentdev.whetherkmm.WeatherDatabase
import com.silentdev.whetherkmm.viewmodel.WeatherViewModel
import org.koin.dsl.module
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController

val iosModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            WeatherDatabase.Schema,
            "weather.db",
        ) // Provide the database driver for iOS
    }
    single<UIViewController> {
        UIApplication.sharedApplication.keyWindow?.rootViewController
            ?: error("No rootViewController found")
    }

    single { WeatherViewModel(get()) }
}