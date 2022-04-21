package com.alex.miet.mobile

import android.app.Application
import com.alex.miet.mobile.ui.AppComponent
import com.alex.miet.mobile.ui.DaggerAppComponent


open class MietApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}