package com.miet.schedule_db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupItem(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "group_name")
        val group: String,
        @ColumnInfo(name = "semester")
        val semester: String? = null)