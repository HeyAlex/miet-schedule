package com.alex.miet.data.daos

import androidx.room.Dao
import com.alex.miet.data.entities.Group

@Dao
abstract class GroupsDao : EntityDao<Group>()