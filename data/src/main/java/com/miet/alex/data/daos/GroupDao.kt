package com.miet.alex.data.daos

import androidx.room.*
import com.miet.alex.data.entities.GroupItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GroupDao : BaseDao<GroupItem>() {
    @Query("SELECT * FROM group_table WHERE group_name = :name")
    abstract suspend fun getGroupByName(name: String): GroupItem?

    @Query("DELETE FROM group_table")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM group_table")
    abstract suspend fun loadAll(): Flow<List<GroupItem>>
}
