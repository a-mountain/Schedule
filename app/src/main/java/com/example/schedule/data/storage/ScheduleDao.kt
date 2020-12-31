package com.example.schedule.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule WHERE id LIKE 0")
    fun obtainGroup(): ScheduleEntity?
    @Insert
    fun save(scheduleEntity: ScheduleEntity)
    @Delete
    fun deleteByGroupName(scheduleEntity: ScheduleEntity)
}