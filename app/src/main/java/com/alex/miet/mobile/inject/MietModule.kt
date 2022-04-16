package com.alex.miet.mobile.inject

import com.alex.miet.miet_api.MietApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
object MietModule {
    @Singleton
    @Provides
    fun provideMietApi(
        client: OkHttpClient,
    ): MietApiService = MietApiService.create(client)
}