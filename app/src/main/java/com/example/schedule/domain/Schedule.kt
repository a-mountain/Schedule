package com.example.schedule.domain

import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.math.abs

data class Schedule(
    val groupName: String,
    val firstWeek: Week,
    val secondWeek: Week,
) {

    companion object {
        val MONDAY_OF_FIRST_WEEK: LocalDate = LocalDate.of(2020, 9, 7)
        fun of(groupName: String, firstWeek: Week, secondWeek: Week): Schedule {
            return Schedule(groupName, firstWeek, secondWeek)
        }
    }

    private val daySchedules = firstWeek.getDaySchedules() + secondWeek.getDaySchedules()

    fun today() = Day(LocalDate.now(), getScheduleForDay(LocalDate.now()))

    fun getScheduleForTwoWeeks(date: LocalDate): List<Day> {
        val monday = getMondayOfThisDay(date)
        return getNextDays(monday, 14).filterNot { it.schedule.isDayOff }
    }

    private fun getNextDays(start: LocalDate, daysNum: Int): List<Day> {
        return List(daysNum) {
            val date = start.plusDays(it.toLong())
            Day(date, getScheduleForDay(date))
        }
    }

    private fun getScheduleForDay(date: LocalDate): DaySchedule {
        val difference = abs(date.toEpochDay() - MONDAY_OF_FIRST_WEEK.toEpochDay())
        return daySchedules[(difference % 14L).toInt()]
    }

    private fun getMondayOfThisDay(date: LocalDate): LocalDate {
        if (date.dayOfWeek == DayOfWeek.MONDAY) {
            return date
        }
        return date.minusDays(date.dayOfWeek.ordinal.toLong())
    }

    private fun dateNow() = LocalDate.now()

}