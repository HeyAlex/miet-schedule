package com.alex.miet.data.daos

import androidx.room.Dao
import com.alex.miet.data.entities.Lesson

@Dao
abstract class LessonsDao : EntityDao<Lesson>()