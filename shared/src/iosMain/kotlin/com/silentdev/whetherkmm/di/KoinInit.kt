package com.silentdev.whetherkmm.di

import com.silentdev.whetherkmm.core.di.commonModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            iosModule, // iOS-specific module
            commonModule // Common module
        )
    }
}