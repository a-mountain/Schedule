package com.example.schedule.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScheduleEntity::class], version = 2, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao
}