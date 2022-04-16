package com.miet.schedule_db.inject

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.alex.miet.mobile.ScheduleDatabase
import com.miet.alex.data.daos.LessonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ScheduleDatabase {
        val builder = Room.databaseBuilder(context, ScheduleDatabase::class.java, "miet_schedule.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {
    @Provides
    fun provideLessonDao(db: ScheduleDatabase) = db.lessonDao()

    @Provides
    fun provideGroupDao(db: ScheduleDatabase) = db.groupDao()
}
