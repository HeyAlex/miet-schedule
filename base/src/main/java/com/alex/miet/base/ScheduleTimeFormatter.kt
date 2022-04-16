package com.alex.miet.base

import org.joda.time.format.DateTimeFormatter

class ScheduleTimeFormatter(val formatter: DateTimeFormatter) {

    fun formatDateTime(date: String): String = formatter.parseDateTime(date)
        .toString("HH:mm")

}