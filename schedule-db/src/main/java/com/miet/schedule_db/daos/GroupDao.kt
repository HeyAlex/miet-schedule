package com.miet.schedule_db.daos

import androidx.room.*
import com.miet.schedule_db.daos.BaseDao
import com.miet.schedule_db.entities.GroupItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GroupDao : BaseDao<GroupItem> {
    @Query("SELECT * FROM group_table WHERE group_name = :name")
    abstract fun getGroupByName(name: String): Flow<GroupItem>

    @Query("DELETE FROM group_table")
    abstract fun deleteAll()

    @Query("SELECT * FROM group_table")
    abstract fun loadAll(): Flow<List<GroupItem>>
}
