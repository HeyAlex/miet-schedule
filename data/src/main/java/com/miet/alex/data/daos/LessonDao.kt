package com.miet.alex.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.miet.alex.data.entities.LessonItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LessonDao : BaseDao<LessonItem>() {
    @Query("DELETE FROM lessons")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM lessons WHERE group_name = :groupName")
    abstract fun loadByGroupName(groupName: String): Flow<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND week = :week")
    abstract suspend fun loadByWeek(groupName: String, week: Int): List<LessonItem>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND day = :day AND week = :week")// ORDER BY code ASC")
    abstract suspend fun loadByWeekAndDay(groupName: String, week: Int, day: Int): List<LessonItem>

    @Query("DELETE FROM lessons WHERE group_name = :groupName")
    abstract suspend fun deleteByGroup(groupName: String)
}