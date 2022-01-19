package com.alex.miet.base

import org.joda.time.format.DateTimeFormatter

class ScheduleTimeFormatter(val formatter: DateTimeFormatter) {
//    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")

    fun formatDateTime(date: String): String = formatter.parseDateTime(date)
        .toString("HH:mm")

}