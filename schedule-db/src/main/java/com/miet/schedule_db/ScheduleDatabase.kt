package com.alex.miet.mobile

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miet.schedule_db.daos.GroupDao
import com.miet.schedule_db.daos.LessonDao
import com.miet.schedule_db.entities.GroupItem
import com.miet.schedule_db.entities.LessonItem

@Database(entities = [GroupItem::class, LessonItem::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun lessonDao(): LessonDao
}
