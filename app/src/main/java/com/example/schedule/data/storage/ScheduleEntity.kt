package com.example.schedule.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.schedule.data.networking.ScheduleData
import com.google.gson.Gson

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey val id: Int,
    val groupName: String,
    val content: String,
) {
    fun toSchedule(gson: Gson) = gson.fromJson(content, ScheduleData::class.java)!!
}

fun ScheduleData.toScheduleEntity(gson: Gson): ScheduleEntity {
    val json = gson.toJson(this)
    return ScheduleEntity(0, groupName, json)
}