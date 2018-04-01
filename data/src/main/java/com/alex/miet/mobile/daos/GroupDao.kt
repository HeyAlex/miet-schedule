package com.alex.miet.mobile.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alex.miet.mobile.entities.GroupItem
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class GroupDao : BaseDao<GroupItem> {
    @Query("SELECT * FROM group_table WHERE group_name = :name")
    abstract fun getGroupByName(name: String): Maybe<GroupItem>

    @Query("DELETE FROM group_table")
    abstract fun deleteAll()

    @Query("SELECT * FROM group_table")
    abstract fun loadAll(): Maybe<List<GroupItem>>
}
