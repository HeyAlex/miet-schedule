package com.alex.miet.data

import android.content.Context
import android.os.Debug
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        RoomDatabaseModule::class,
        DatabaseDaoModule::class
    ]
)
class StorageModule

@Module
class RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): MietDatabase {
        val builder = Room.databaseBuilder(context, MietRoomDatabase::class.java, "miet.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@Module
class DatabaseDaoModule {

    @Provides
    fun provideLessonDao(db: MietDatabase) = db.lessonDao()

    @Provides
    fun provideGroupDao(db: MietDatabase) = db.groupDao()
}