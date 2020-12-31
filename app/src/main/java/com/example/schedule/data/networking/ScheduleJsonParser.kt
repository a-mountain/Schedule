package com.example.schedule.data.networking

import com.example.schedule.domain.*
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.lang.Exception
import java.time.DayOfWeek
import java.time.LocalTime

object ScheduleJsonParser {

    fun fromJson(json: JsonElement): ScheduleData {
        val jsonObject = json.asJsonObject["data"].asJsonObject
        val weeks = jsonObject["weeks"].asJsonObject
        val groupName = jsonObject["group"].asJsonObject["group_full_name"].asString
        val firstWeek = parseWeekData(weeks.get("1").asJsonObject)
        val secondWeek = parseWeekData(weeks.get("2").asJsonObject)
        return ScheduleData(groupName, firstWeek, secondWeek)
    }

    private fun parseWeekData(jsonObject: JsonObject): WeekData {
        val weekNumber = jsonObject.get("week_number").asInt
        val keys = jsonObject.get("days").asJsonObject.keySet()
        val days =
            keys.map { parseDayData(jsonObject.get("days").asJsonObject.get(it).asJsonObject) }
        return WeekData(weekNumber, days)
    }

    private fun parseDayData(jsonObject: JsonObject): DayData {
        val dayName = jsonObject.get("day_name").asString
        val dayNumber = jsonObject.get("day_number").asInt
        val lessons = jsonObject.get("lessons").asJsonArray.map { parseLessonData(it.asJsonObject) }
        return DayData(dayName, dayNumber, lessons)
    }

    private fun parseLessonData(jsonObject: JsonObject): LessonData {
        val dayNumber = jsonObject.get("day_number").asInt
        val dayName = jsonObject.get("day_name").asString
        val lessonName = jsonObject.get("lesson_name").asString
        val lessonNumber = jsonObject.get("lesson_number").asInt
        val lessonType = jsonObject.get("lesson_type").asString
        val lessonRoom = jsonObject.get("lesson_room").asString
        val teacherName = jsonObject.get("teacher_name").asString
        val timeStart = jsonObject.get("time_start").asString
        val timeEnd = jsonObject.get("time_end").asString
        return LessonData(
            dayNumber,
            dayName,
            lessonName,
            lessonNumber,
            lessonType,
            lessonRoom,
            teacherName,
            timeStart,
            timeEnd
        )
    }

}

class ScheduleBuilder(
    private val groupName: String,
    private val firstWeek: WeekData,
    private val secondWeek: WeekData
) {

    companion object {
        fun createSchedule(schedule: ScheduleData): Schedule {
            return ScheduleBuilder(
                schedule.groupName,
                schedule.firstWeek,
                schedule.secondWeek
            ).build()
        }
    }

    private val weeks = listOf(firstWeek, secondWeek)

    private lateinit var teachers: List<Teacher>
    private lateinit var subjects: List<Subject>

    fun build(): Schedule {
        findAllTeachers()
        findAllSubjects()
        val firstWeek = createWeek(firstWeek)
        val secondWeek = createWeek(secondWeek)
        return Schedule.of(groupName, firstWeek, secondWeek)
    }

    private fun createWeek(weekData: WeekData): Week {
        val weekNumber = weekData.weekNumber
        val days = weekData.days.map { createDay(it, weekNumber) }.associateBy { it.dayOfWeek }
        return Week(weekNumber, days)
    }

    private fun createDay(dayData: DayData, weekNumber: Int): DaySchedule {
        val dayOfWeek = dayData.dayName.parseDayOfWeek()
        val lessons = dayData.lessons.map(::createLesson)
        return DaySchedule(weekNumber, dayOfWeek, lessons)
    }

    private fun createLesson(lessonData: LessonData): Lesson {
        val subject = getSubject(lessonData.lessonName)
        val lessonType = lessonData.lessonType.parseLessonType()
        val lessonNumber = lessonData.lessonNumber
        val room = lessonData.lessonRoom
        val start = lessonData.timeStart.parseLocalTime()
        val end = lessonData.timeEnd.parseLocalTime()
        return Lesson(subject, lessonType, lessonNumber, room, start, end)
    }

    private fun String.parseDayOfWeek() = when (this) {
        "Понеділок" -> DayOfWeek.MONDAY
        "Вівторок" -> DayOfWeek.TUESDAY
        "Середа" -> DayOfWeek.WEDNESDAY
        "Четвер" -> DayOfWeek.THURSDAY
        "П’ятниця" -> DayOfWeek.FRIDAY
        "Субота" -> DayOfWeek.SATURDAY
        else -> throw Exception("Unhandled dayOfWeek name $this")
    }

    private fun String.parseLessonType() = when (this) {
        "Лек" -> LessonType.Lecture
        "Лаб" -> LessonType.Lab
        "Прак" -> LessonType.Practise
        else -> LessonType.Unknown
    }

    private fun String.parseLocalTime(): LocalTime {
        val values = this.split(":")
        val hours = values[0].toInt()
        val minutes = values[1].toInt()
        return LocalTime.of(hours, minutes)
    }

    private fun findAllTeachers() {
        val teacherNames = HashSet<String>()
        weeks.forEach { week ->
            week.days.forEach { day ->
                day.lessons.forEach { lesson ->
                    teacherNames.add(lesson.teacherName)
                }
            }
        }
        teachers = teacherNames.map { Teacher(it) }
    }

    private fun findAllSubjects() {
        val subjects = HashMap<String, Subject>()
        weeks.forEach { week ->
            week.days.forEach { day ->
                day.lessons.forEach { lesson ->
                    val lessonName = lesson.lessonName
                    if (!subjects.containsKey(lesson.lessonName)) {
                        subjects[lessonName] = Subject(mutableMapOf(), lessonName)
                    }
                    val teachers = subjects[lessonName]!!.teachers
                    teachers[lesson.lessonType.parseLessonType()] = getTeacher(lesson.teacherName)
                }
            }
        }
        this.subjects = subjects.values.toList()
    }

    private fun getTeacher(name: String) = teachers.find { it.name == name }!!

    private fun getSubject(title: String) = subjects.find { it.title == title }!!
}

data class ScheduleData(
    val groupName: String,
    val firstWeek: WeekData,
    val secondWeek: WeekData,
)

data class WeekData(
    val weekNumber: Int,
    val days: List<DayData>,
)

data class DayData(
    val dayName: String,
    val dayNumber: Int,
    val lessons: List<LessonData>
)

data class LessonData(
    val dayNumber: Int,
    val dayName: String,
    val lessonName: String,
    val lessonNumber: Int,
    val lessonType: String,
    val lessonRoom: String,
    val teacherName: String,
    val timeStart: String,
    val timeEnd: String,
)