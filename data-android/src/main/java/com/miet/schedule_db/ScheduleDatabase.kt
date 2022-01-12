package com.alex.miet.mobile

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miet.alex.data.daos.GroupDao
import com.miet.alex.data.daos.LessonDao
import com.miet.alex.data.entities.GroupItem
import com.miet.alex.data.entities.LessonItem

@Database(entities = [GroupItem::class, LessonItem::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun lessonDao(): LessonDao
}
