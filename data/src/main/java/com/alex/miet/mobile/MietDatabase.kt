package com.alex.miet.mobile

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alex.miet.mobile.daos.GroupDao
import com.alex.miet.mobile.daos.LessonDao
import com.alex.miet.mobile.daos.NewsDao
import com.alex.miet.mobile.entities.GroupItem
import com.alex.miet.mobile.entities.LessonItem
import com.alex.miet.mobile.entities.NewsItem

@Database(entities = arrayOf(
        GroupItem::class,
        LessonItem::class,
        NewsItem::class),
        version = 1)
abstract class MietDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun lessonDao(): LessonDao
    abstract fun newsDao(): NewsDao
}
