package com.example.schedule.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.schedule.data.networking.RozkladKpiScheduleAPI
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RozkladKpiScheduleAPITest {

    @Test
    fun getSchedule() {
        val api = RozkladKpiScheduleAPI()
        val schedule = api.getSchedule("ІП-94")
        assertEquals("200", schedule.asJsonObject.get("statusCode").asString)
    }
}
