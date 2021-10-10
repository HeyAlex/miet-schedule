package com.alex.miet.miet_api

import com.alex.miet.miet_api.convertor.Json
import com.alex.miet.miet_api.convertor.MietConverter
import com.alex.miet.miet_api.convertor.Xml
import com.alex.miet.miet_api.data.news.ArticleResponse
import com.alex.miet.miet_api.data.schedule.Semestr
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface
 */
interface MietService {
    /**
     * Returns last 20 rss article [Article]
     *
     * @return [ArticleResponse]
     */
    @Xml
    @GET("/rss/news")
    suspend fun getNews() : Response<ArticleResponse>

    /**
     * Returns a list of available group names
     *
     * @return list of strings
     */
    @Json
    @GET("schedule/groups")
    suspend fun getGroupNames() : Response<List<String>>

    /**
     * Returns a schedule response
     *
     * @param groupName associated with schedule
     * @return [Semestr]
     */
    @Json
    @GET("schedule/data")
    suspend fun getScheduleResponse(@Query("group") groupName: String): Response<Semestr>


    companion object {
        private val mietEndpoint = "https://miet.ru"

        var retrofitService: MietService? = null
        fun getInstance() : MietService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(mietEndpoint)
                    .addConverterFactory(MietConverter())
                    .build()
                retrofitService = retrofit.create(MietService::class.java)
            }
            return retrofitService!!
        }

    }
}