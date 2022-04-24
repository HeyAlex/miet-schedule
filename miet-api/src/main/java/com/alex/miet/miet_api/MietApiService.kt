package com.alex.miet.miet_api

import com.alex.miet.miet_api.data.schedule.Schedule
import dagger.Lazy
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MietApiService {
    /**
     * Returns a list of available group names
     *
     * @return list of groups name
     */
    @GET("schedule/groups")
    suspend fun groups(): List<String>

    /**
     * Returns a schedule response
     *
     * @param groupName associated with schedule
     * @return [Schedule]
     */
    @GET("schedule/data")
    suspend fun schedule(@Query("group") groupName: String): Schedule

    companion object {
        private const val MIET_ENDPOINT = "https://miet.ru/"
        fun create(client: Lazy<OkHttpClient>): MietApiService {
            val retrofit = Retrofit.Builder()
                .callFactory {
                    client.get().newCall(it)
                }
                .baseUrl(MIET_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MietApiService::class.java)
        }
    }
}