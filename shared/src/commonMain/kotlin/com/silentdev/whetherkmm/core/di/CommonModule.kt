package com.silentdev.whetherkmm.core.di

import org.koin.dsl.module

val commonModule = module {
    networkModule
    databaseModule
}