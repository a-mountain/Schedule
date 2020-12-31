package com.example.schedule.data

import com.example.schedule.data.networking.ScheduleJsonParser
import com.example.schedule.domain.Schedule
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.junit.Test

import java.io.File

class ScheduleJsonParserTest {

    @Test
    fun fromJson() {
        val jsonText = File("src\\test\\java\\com\\example\\schedule\\data\\json").readText()
        val jsonElement = JsonParser.parseString(jsonText)
        val parser = ScheduleJsonParser.fromJson(jsonElement)
        val a = GsonBuilder().create().toJson(parser)
        val b = GsonBuilder().create().fromJson<Schedule>(a, Schedule::class.java)
        println(b)
    }
}