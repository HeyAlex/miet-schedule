package com.alex.miet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.miet.data.entities.Group
import com.alex.miet.data.entities.Lesson

@Database(
    entities = [
        Group::class,
        Lesson::class
    ],
    version = 1
)
abstract class MietRoomDatabase : RoomDatabase(), MietDatabase