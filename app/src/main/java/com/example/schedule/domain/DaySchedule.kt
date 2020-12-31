package com.example.schedule.domain

import java.time.DayOfWeek

data class DaySchedule(
    val weekNumber: Int,
    val dayOfWeek: DayOfWeek,
    val lessons: List<Lesson>,
) {

    val isDayOff: Boolean get() = lessons.isEmpty()
}