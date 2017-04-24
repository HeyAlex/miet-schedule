package heyalex.com.miet_schedule.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UniversityApiModule {

    @Provides
    @Singleton
    public UniversityService provideScheduleApi() {
        return UniversityApiFactory.getUniversityApi();
    }
}
