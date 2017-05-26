package heyalex.com.miet_schedule.api;

import java.util.List;

import heyalex.com.miet_schedule.model.news.ArticleResponse;
import heyalex.com.miet_schedule.model.schedule.SemesterData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mac on 04.04.17.
 */

public interface UniversityService {

    @GET("rss/news")
    Observable<ArticleResponse> getNews();

    /**
     * Returns a list of group names heyalex.com.miet_schedule.schedule for is available via this service
     *
     * @return list of strings
     */
    @GET("schedule/groups")
    Observable<List<String>> getGroupNames();

    @GET("schedule/data")
    Observable<SemesterData> getScheduleResponse(@Query("group") String groupName);
}
