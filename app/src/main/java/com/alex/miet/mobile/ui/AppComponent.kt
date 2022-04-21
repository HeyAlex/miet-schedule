package com.alex.miet.mobile.ui

import android.content.Context
import com.alex.miet.mobile.inject.MietModule
import com.alex.miet.mobile.inject.NetworkModule
import com.miet.schedule_db.inject.DatabaseDaoModule
import com.miet.schedule_db.inject.RoomDatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MietModule::class, DatabaseDaoModule::class, RoomDatabaseModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}