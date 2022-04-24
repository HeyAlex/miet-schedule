package com.alex.miet.base

import org.joda.time.format.DateTimeFormatter
import javax.inject.Inject

class ScheduleTimeFormatter @Inject constructor(private val formatter: DateTimeFormatter) {
    fun formatDateTime(date: String): String = formatter.parseDateTime(date).toString("HH:mm")
}