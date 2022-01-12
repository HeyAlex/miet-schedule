package com.miet.alex.data.repositories

import com.miet.alex.data.entities.GroupItem
import com.miet.alex.data.entities.LessonItem

interface LessonRemoteDataSource {
    suspend fun getGroupNames(): List<LessonItem>
}