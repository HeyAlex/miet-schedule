package heyalex.com.miet_schedule.api;

import java.util.List;

import heyalex.com.miet_schedule.model.news.ArticleResponse;
import heyalex.com.miet_schedule.model.schedule.SemesterData;
import io.reactivex.Single;
import retrofit2.http.Query;

/**
 * Class used to get instance of {@link UniversityService}
 */
public class UniversityApiFactory {
    private static final UniversityService instance = new UniversityService() {

        private UniversityService scheduleService
                = UniversityServiceGenerator.createScheduleService(UniversityService.class);
        private UniversityService newsService
                = UniversityServiceGenerator.createNewsService(UniversityService.class);

        @Override
        public Single<ArticleResponse> getNews() {
            return newsService.getNews();
        }

        @Override
        public Single<List<String>> getGroupNames() {
            return scheduleService.getGroupNames();
        }

        @Override
        public Single<SemesterData> getScheduleResponse(@Query("group") String groupName) {
            return scheduleService.getScheduleResponse(groupName);
        }
    };

    public static UniversityService getUniversityApi() {
        return instance;
    }
}
