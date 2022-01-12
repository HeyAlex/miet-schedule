package com.miet.alex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,
    @ColumnInfo(name = "group_name")
    val group: String,
    @ColumnInfo(name = "semester")
    val semester: String? = null
) : MietEntity