package com.silentdev.whetherkmm.application

import android.app.Application
import com.silentdev.whetherkmm.core.di.androidDatabaseModule
import com.silentdev.whetherkmm.core.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                androidDatabaseModule,
                commonModule
            )
        }
    }
}