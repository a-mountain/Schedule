package com.example.schedule.data

import com.example.schedule.data.networking.Connectivity
import com.example.schedule.data.networking.ScheduleBuilder
import com.example.schedule.data.networking.ScheduleServiceKPI
import com.example.schedule.data.storage.ScheduleDao
import com.example.schedule.data.storage.toScheduleEntity
import com.example.schedule.domain.Schedule
import com.example.schedule.domain.ScheduleRepository
import com.google.gson.*

class KpiScheduleRepository(
    private val scheduleDao: ScheduleDao,
    private val connectivity: Connectivity
) : ScheduleRepository {

    private val service = ScheduleServiceKPI()
    private val gson = GsonBuilder().create()

    override fun getCurrentSchedule(): Schedule? {
        val data = scheduleDao.obtainGroup()?.toSchedule(gson)
        return if (data == null) {
            null
        } else ScheduleBuilder.createSchedule(data)
    }

    override fun newSchedule(groupName: String) {
        if (!connectivity.hasNetworkAccess()) {
            throw ConnectionError()
        }
        scheduleDao.save(service.getSchedule(groupName).toScheduleEntity(gson))
    }
}
