package com.alex.miet.mobile.network

import com.alex.miet.miet_api.data.schedule.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRemoteDataSource {
    fun getGroupNames(): Flow<Result<List<String>>>
    fun getScheduleResponse(groupName: String): Flow<Result<Schedule>>
}