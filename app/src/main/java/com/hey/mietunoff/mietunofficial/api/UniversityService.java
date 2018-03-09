package com.hey.mietunoff.mietunofficial.api;

import java.util.List;

import com.hey.mietunoff.mietunofficial.model.news.Article;
import com.hey.mietunoff.mietunofficial.model.news.ArticleResponse;
import com.hey.mietunoff.mietunofficial.model.schedule.SemesterData;
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
    @GET("rss/news")
    Single<ArticleResponse> getNews();

    /**
     * Returns a list of available group names
     *
     * @return list of strings
     */
    @GET("schedule/groups")
    Single<List<String>> getGroupNames();

    /**
     * Returns a schedule response
     *
     * @param groupName associated with schedule
     * @return {@link SemesterData}
     */
    @GET("schedule/data")
    Single<SemesterData> getScheduleResponse(@Query("group") String groupName);
}
