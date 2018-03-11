package com.alex.miet.mobile.api;

import java.util.List;

import com.alex.miet.mobile.api.convertor.Json;
import com.alex.miet.mobile.api.convertor.Xml;
import com.alex.miet.mobile.model.news.Article;
import com.alex.miet.mobile.model.news.ArticleResponse;
import com.alex.miet.mobile.model.schedule.SemesterData;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface
 */
public interface UniversityService {

    /**
     * Returns last 20 rss article {@link Article}
     *
     * @return {@link ArticleResponse}
     */
    @GET("/rss/news")  @Xml
    Single<ArticleResponse> getNews();

    /**
     * Returns a list of available group names
     *
     * @return list of strings
     */
    @GET("schedule/groups") @Json
    Single<List<String>> getGroupNames();

    /**
     * Returns a schedule response
     *
     * @param groupName associated with schedule
     * @return {@link SemesterData}
     */
    @GET("schedule/data") @Json
    Single<SemesterData> getScheduleResponse(@Query("group") String groupName);
}
