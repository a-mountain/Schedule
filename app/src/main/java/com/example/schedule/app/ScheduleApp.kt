package com.example.schedule.app

import android.app.Application
import com.example.schedule.app.di.module
import com.example.schedule.data.dataModule
import com.example.schedule.data.storage.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScheduleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScheduleApp)
            modules(module, storageModule, dataModule)
        }
    }
}