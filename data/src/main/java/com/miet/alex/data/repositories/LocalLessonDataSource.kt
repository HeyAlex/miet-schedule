package com.miet.alex.data.repositories

import com.miet.alex.data.daos.LessonDao
import com.miet.alex.data.entities.LessonItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalLessonDataSource @Inject constructor(private val lessonDao: LessonDao) {
    suspend fun save(lessons: List<LessonItem>) = lessonDao.insertOrUpdate(lessons)

    suspend fun deleteAllByGroupName(groupName: String) = lessonDao.deleteByGroup(groupName)

    suspend fun getLessonsByWeekAndDay(groupName: String, week: Int, day: Int): List<LessonItem> =
        lessonDao.loadByWeekAndDay(groupName, week, day)

    suspend fun getLessonsForWeek(groupName: String, week: Int): List<LessonItem> =
        lessonDao.loadByWeek(groupName, week)

    suspend fun getLessonsForGroup(groupName: String): Flow<List<LessonItem>> =
        lessonDao.loadByGroupName(groupName)
}