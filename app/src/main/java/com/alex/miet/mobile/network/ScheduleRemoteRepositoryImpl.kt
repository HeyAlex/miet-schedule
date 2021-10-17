package com.alex.miet.mobile.network

import com.alex.miet.miet_api.MietApiService
import com.alex.miet.miet_api.data.schedule.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ScheduleRemoteRepositoryImpl(val mietApi: MietApiService) : ScheduleRemoteRepository {
    override fun getGroupNames(): Flow<Result<List<String>>> {
        return flow {
            try {
                emit(Result.success(mietApi.groups()))
            } catch (ex: Exception) {
                emit(Result.failure<List<String>>(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getScheduleResponse(groupName: String): Flow<Result<Schedule>> {
        return flow {
            try {
                emit(Result.success(mietApi.schedule(groupName)))
            } catch (ex: Exception) {
                emit(Result.failure<Schedule>(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}