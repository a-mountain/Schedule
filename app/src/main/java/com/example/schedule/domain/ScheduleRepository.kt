package com.example.schedule.domain

interface ScheduleRepository {
    fun getCurrentSchedule(): Schedule?
    fun newSchedule(groupName: String)
}