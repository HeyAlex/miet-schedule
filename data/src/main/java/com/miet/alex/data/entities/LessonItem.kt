package com.miet.alex.data.entities

import androidx.room.*

@Entity(
    tableName = "lessons",
    indices = [
        Index(value = ["group_name"], unique = true)
    ],
    foreignKeys = [
        (ForeignKey(
            entity = GroupItem::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("group_name"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ))
    ]
)
data class LessonItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,
    @ColumnInfo(name = "group_name")
    val group_name: String,
    @ColumnInfo(name = "week")
    val week: Int,
    @ColumnInfo(name = "day")
    val day: Int,
    @ColumnInfo(name = "room")
    val room: String,
    @ColumnInfo(name = "time")
    val time: Int,
    @ColumnInfo(name = "timeTo")
    val timeTo: String,
    @ColumnInfo(name = "timeFrom")
    val timeFrom: String,
    @ColumnInfo(name = "teacher")
    val teacher: String,
    @ColumnInfo(name = "teacherFull")
    val teacherFull: String,
    @ColumnInfo(name = "disciplineName")
    val disciplineName: String
) : MietEntity {

    @Ignore
    var timeFull: String? = "$timeFrom - $timeTo ($time)"
}
