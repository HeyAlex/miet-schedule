package com.alex.miet.miet_api

import com.alex.miet.miet_api.network.MietApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class MietModule {
    @Provides
    @Singleton
    fun provideMietService(client: OkHttpClient) = MietApiService.create(client)
}