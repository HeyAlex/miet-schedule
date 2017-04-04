package heyalex.com.miet_schedule.api;

import java.util.Set;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.model.news.ArticleResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by mac on 04.04.17.
 */

public interface UniversityApi {


    Observable<Set<NewsModel>> getNews();


    /**
     * Returns Observable Set of {@link LessonModel} for provided groupName
     * @param groupName to get schedule items
     * @return observable
     */
    Observable<Set<LessonModel>> getSchedule(String groupName);

    /**
     * Returns Observable of set of string with group names schedule for is available
     * @return observable
     */
    Observable<Set<String>> getGroups();


}
