package com.alex.miet.miet_api

import com.alex.miet.miet_api.convertor.MietConverter
import okhttp3.OkHttpClient
import retrofit2.Retrofit


class MietApi {

    private val mietEndpoint = "https://miet.ru"
    private val httpClient = OkHttpClient.Builder()
    private val retrofitUniversityBuilder = Retrofit.Builder()
        .baseUrl(mietEndpoint)
        .addConverterFactory(MietConverter())

    fun <S> createMietApi(serviceClass: Class<S>?): S {
        val retrofit = retrofitUniversityBuilder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}