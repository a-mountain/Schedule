package com.example.schedule.domain

import java.time.LocalDate

data class Day(
    val date: LocalDate,
    val schedule: DaySchedule,
)