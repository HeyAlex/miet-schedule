package com.miet.schedule_db.daos

import androidx.room.*
import com.miet.schedule_db.daos.BaseDao
import com.miet.schedule_db.entities.LessonItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LessonDao : BaseDao<LessonItem> {
    @Query("DELETE FROM lessons")
    abstract fun deleteAll()

    @Query("SELECT * FROM lessons")
    abstract fun loadAll(): Flow<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName")
    abstract fun loadByGroupName(groupName: String): Flow<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND week = :week")
    abstract fun loadByWeek(groupName: String, week: Int): Flow<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND day = :day AND week = :week ORDER BY code ASC")
    abstract fun loadByWeekAndDay(groupName: String, week: Int, day: Int): Flow<List<LessonItem>>
}