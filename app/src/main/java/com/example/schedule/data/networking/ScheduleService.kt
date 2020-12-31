package com.example.schedule.data.networking

import com.example.schedule.domain.Schedule

class ScheduleServiceKPI {

    private val api = RozkladKpiScheduleAPI()
    private val parser = ScheduleJsonParser

    fun getSchedule(groupName: String): ScheduleData {
        return parser.fromJson(api.getSchedule(groupName))
    }
}