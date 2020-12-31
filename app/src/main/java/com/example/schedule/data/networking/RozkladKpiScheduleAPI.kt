package com.example.schedule.data.networking

import com.example.schedule.data.GroupNotFound
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.coroutines.withContext
import java.net.URL

class RozkladKpiScheduleAPI {

    private companion object {
        const val URL = "http://api.rozklad.org.ua/v2/groups/{group_name}/timetable"
    }

    fun getSchedule(groupName: String): JsonElement {
        try {
            val url = URL(getUrlForGroup(groupName)).openConnection()
            val redirectUrl = url.getHeaderField("Location")
            val text = URL(redirectUrl).readText()
            return JsonParser.parseString(text)
        } catch (e: Exception) {
            throw GroupNotFound(groupName)
        }

    }

    private fun getUrlForGroup(groupName: String) = URL.replace("{group_name}", groupName)
}
