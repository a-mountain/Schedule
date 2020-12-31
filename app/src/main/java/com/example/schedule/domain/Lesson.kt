package com.example.schedule.domain

import java.time.LocalTime
data class Lesson(
    val subject: Subject,
    val lessonType: LessonType,
    val lessonNumber: Int,
    val room: String,
    val start: LocalTime,
    val end: LocalTime,
) {
    val teacher: Teacher get() = subject.getTeacher(lessonType)
}