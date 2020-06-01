package com.alex.miet.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.alex.miet.data.entities.Group

@Dao
abstract class GroupsDao : EntityDao<Group>() {

    @Query("SELECT * FROM groups")
    abstract fun getAllGroups(name: String): Group
}