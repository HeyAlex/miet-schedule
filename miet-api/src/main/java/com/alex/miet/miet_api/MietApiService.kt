package com.alex.miet.miet_api

import com.alex.miet.miet_api.data.schedule.Schedule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MietApiService {
    /**
     * Returns a list of available group names
     *
     * @return list of strings
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
        fun create(client: OkHttpClient): MietApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(MIET_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MietApiService::class.java)
        }
    }
}
