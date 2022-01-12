package com.miet.alex.data.repositories

import com.miet.alex.data.entities.LessonItem

interface LessonDataSource {
    suspend fun deleteAllByGroupName(groupName: String)
    suspend fun getLessonsByWeekAndDay(groupName: String, week: Int, day: Int): List<LessonItem>
    suspend fun getLessonsForWeek(groupName: String, week: Int): List<LessonItem>
    suspend fun getLessonsForGroup(groupName: String): List<LessonItem>
}