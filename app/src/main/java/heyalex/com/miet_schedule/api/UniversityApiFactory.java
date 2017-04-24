package heyalex.com.miet_schedule.api;

import java.util.Set;

import heyalex.com.miet_schedule.model.news.ArticleResponse;
import heyalex.com.miet_schedule.model.schedule.SemestrData;
import io.reactivex.Observable;
import retrofit2.http.Query;


public class UniversityApiFactory {
    private static final UniversityService instance = new UniversityService() {

        private UniversityService scheduleService
                = UniversityServiceGenerator.createScheduleService(UniversityService.class);
        private UniversityService newsService
                = UniversityServiceGenerator.createNewsService(UniversityService.class);

        @Override
        public Observable<ArticleResponse> getNews() {
            return newsService.getNews();
        }

        @Override
        public Observable<Set<String>> getGroupNames() {
            return scheduleService.getGroupNames();
        }

        @Override
        public Observable<SemestrData> getScheduleResponse(@Query("group") String groupName) {
            return scheduleService.getScheduleResponse(groupName);
        }
    };

    public static UniversityService getUniversityApi() {
        return instance;
    }
}
