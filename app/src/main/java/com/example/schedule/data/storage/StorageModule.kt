package com.example.schedule.data.storage

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SCHEDULE_DB = "schedule-database"

val storageModule = module {
    single {
        Room.databaseBuilder(androidContext(), ScheduleDatabase::class.java, SCHEDULE_DB).build()
    }
    factory { get<ScheduleDatabase>().scheduleDao() }
}