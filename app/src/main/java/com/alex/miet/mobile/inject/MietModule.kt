package com.alex.miet.mobile.inject

import com.alex.miet.base.ScheduleTimeFormatter
import com.alex.miet.miet_api.MietApiService
import com.miet.alex.data.mappers.GroupNameMapper
import com.miet.alex.data.mappers.LessonMapper
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
object MietModule {

    @Provides
    @Singleton
    fun provideMietApi(
        client: Lazy<OkHttpClient>,
    ): MietApiService = MietApiService.create(client)

    @Provides
    @Singleton
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return  DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")
    }
}