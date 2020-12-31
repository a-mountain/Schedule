package com.example.schedule.domain

data class Subject(
    val teachers: MutableMap<LessonType, Teacher>,
    val title: String,
) {
    fun getTeacher(lessonType: LessonType) = teachers[lessonType]!!
}
