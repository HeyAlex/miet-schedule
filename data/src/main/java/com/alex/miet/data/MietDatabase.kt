package com.alex.miet.data

import com.alex.miet.data.daos.GroupsDao
import com.alex.miet.data.daos.LessonsDao

interface MietDatabase {
    fun lessonDao() : LessonsDao
    fun groupDao() : GroupsDao
}