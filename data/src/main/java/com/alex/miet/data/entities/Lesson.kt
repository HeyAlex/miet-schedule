package com.alex.miet.data.entities

import androidx.room.*

@Entity(
    tableName = "lessons",
    indices = [
        Index(value = ["group_id"])
    ],
    foreignKeys = [
        ForeignKey(entity = Group::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("group_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    ]
)
data class Lesson(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "group_id") val groupId: Long,
    @ColumnInfo(name = "day") val day: Int,
    @ColumnInfo(name = "week") val week: Int,
    @ColumnInfo(name = "room") val room: String? = null,
    @ColumnInfo(name = "time_from") val timeFrom: String? = null,
    @ColumnInfo(name = "time_to") val timeTo: String? = null,
    @ColumnInfo(name = "discipline_name") val disciplineName: String? = null,
    @ColumnInfo(name = "teacher_fullname") val teacherFull: String? = null,
    @ColumnInfo(name = "teacher") val teacher: String? = null,
    @ColumnInfo(name = "code") val code: Int
)