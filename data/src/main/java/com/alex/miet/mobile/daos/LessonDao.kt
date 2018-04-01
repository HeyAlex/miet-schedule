package com.alex.miet.mobile.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alex.miet.mobile.entities.LessonItem
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class LessonDao : BaseDao<LessonItem> {
    @Query("DELETE FROM lessons")
    abstract fun deleteAll()

    @Query("SELECT * FROM lessons")
    abstract fun loadAll(): Maybe<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName")
    abstract fun loadByGroupName(groupName: String): Maybe<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND week = :week")
    abstract fun loadByWeek(groupName: String, week: Int): Maybe<List<LessonItem>>

    @Query("SELECT * FROM lessons WHERE group_name = :groupName AND day = :day AND week = :week ORDER BY code ASC")
    abstract fun loadByWeekAndDay(groupName: String, week: Int, day: Int): Maybe<List<LessonItem>>
}