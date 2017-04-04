package heyalex.com.miet_schedule.api;

import java.util.List;

import heyalex.com.miet_schedule.model.news.ArticleResponse;
import heyalex.com.miet_schedule.model.schedule.SemestrData;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mac on 04.04.17.
 */

public interface UniversityService {

    @GET("rss/news")
    Call<ArticleResponse> getNews();
    /**
     * Returns a list of group names schedule for is available via this service
     * @return list of strings
     */
    @GET("groups")
    Call<List<String>> getGroupNames();

    @GET("data")
    Call<SemestrData> getScheduleResponse(@Query("group") String groupName);
}
