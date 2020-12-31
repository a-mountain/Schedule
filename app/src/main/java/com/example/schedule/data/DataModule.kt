package com.example.schedule.data

import com.example.schedule.data.networking.Connectivity
import com.example.schedule.domain.ScheduleRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<ScheduleRepository> {
        KpiScheduleRepository(get(), get())
    }
    single { Connectivity(androidContext()) }
}