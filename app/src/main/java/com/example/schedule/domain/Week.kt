package com.example.schedule.domain

import java.time.DayOfWeek

data class Week(
    val weekNumber: Int,
    val schedule: Map<DayOfWeek, DaySchedule>
) {

    companion object {
        const val WEEK_LENGTH = 7
    }

    fun getDaySchedules(): List<DaySchedule> {
        val days = MutableList<DaySchedule?>(WEEK_LENGTH) { null }
        schedule.values.forEach {
            days[it.dayOfWeek.ordinal] = it
        }
        return days.mapIndexed { index, daySchedule ->
            daySchedule ?: DaySchedule(
                weekNumber,
                DayOfWeek.of(index + 1),
                emptyList(),
            )
        }
    }
}