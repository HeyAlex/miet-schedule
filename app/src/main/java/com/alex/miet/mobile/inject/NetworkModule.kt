package com.alex.miet.mobile.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Scope
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
//        .apply { interceptors.forEach(::addInterceptor) }
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
}