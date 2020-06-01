package com.alex.miet.data.repositories

import com.alex.miet.data.daos.LessonsDao
import com.alex.miet.data.mappers.LessonMapper
import com.alex.miet.miet_api.model.Data
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonsDao: LessonsDao,
    private val mapper: LessonMapper
) {
    suspend fun saveLessons(groupId: Long, data: List<Data>) {
        val lessons = data.map {
            mapper.map(Pair(groupId, it))
        }
        lessonsDao.insertAll(lessons)
    }

    suspend fun getLessonsByDayAndWeek(day: Int, week: Int) {

    }
}