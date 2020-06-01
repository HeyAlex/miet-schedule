package com.alex.miet.data.mappers

import com.alex.miet.data.daos.GroupsDao
import com.alex.miet.data.entities.Lesson
import com.alex.miet.miet_api.model.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonMapper @Inject constructor(private val groupsDao: GroupsDao) : Mapper<Pair<Long, Data>, Lesson> {
    override fun map(from: Pair<Long, Data>): Lesson {
        val fromData = from.second
        return Lesson(
            groupId = from.first,
            day = fromData.day,
            week = fromData.dayNumber,
            room = fromData.room.name,
            timeFrom = fromData.time.timeFrom,
            timeTo = fromData.time.timeTo,
            disciplineName = fromData.classRoom.name,
            teacherFull = fromData.classRoom.teacherFull,
            teacher = fromData.classRoom.teacher,
            code = fromData.time.code
        )
    }
}