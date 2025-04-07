package com.silentdev.whetherkmm.core.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.silentdev.whetherkmm.WeatherDatabase
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            WeatherDatabase.Schema,
            get(), // Get the Android context
            "weather.db"
        )
    }
}
