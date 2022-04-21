package com.miet.schedule_db.inject

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.alex.miet.mobile.ScheduleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context
    ): ScheduleDatabase {
        val builder =
            Room.databaseBuilder(context, ScheduleDatabase::class.java, "miet_schedule.db")
                .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@Module
object DatabaseDaoModule {
    @Provides
    fun provideLessonDao(db: ScheduleDatabase) = db.lessonDao()

    @Provides
    fun provideGroupDao(db: ScheduleDatabase) = db.groupDao()
}
