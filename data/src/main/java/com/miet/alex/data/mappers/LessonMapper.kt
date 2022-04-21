package com.miet.alex.data.mappers

import com.alex.miet.base.ScheduleTimeFormatter
import com.alex.miet.miet_api.data.schedule.Schedule
import com.miet.alex.data.entities.LessonItem

class LessonMapper(val formatter: ScheduleTimeFormatter) :
    Mapper<Schedule, Pair<String, List<LessonItem>>> {
    override suspend fun map(from: Schedule): Pair<String, List<LessonItem>> {

        val lessons = from.data.map {
            val toDate: String = "formatter.formatDateTime(it.time.timeTo)"
            val fromDate: String = "formatter.formatDateTime(it.time.timeFrom)"
            val disciplineName: String = it.classRoom.name
            LessonItem(
                group_name = it.group.name,
                week = Integer.valueOf(it.dayNumber),
                day = Integer.valueOf(it.day),
                room = it.room.name,
                time = Integer.valueOf(it.time.code),
                timeTo = toDate,
                timeFrom = fromDate,
                teacher = it.classRoom.teacher,
                teacherFull = it.classRoom.teacherFull,
                disciplineName = disciplineName
            );
        }

        return Pair(from.semestr, lessons)
    }
}