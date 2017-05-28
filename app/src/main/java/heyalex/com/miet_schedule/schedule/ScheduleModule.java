package heyalex.com.miet_schedule.schedule;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;

@Module
public class ScheduleModule {

    @Provides
    @ScheduleScope
    /*package*/ SchedulePresenter provideSchedulePresenter(ScheduleRepository scheduleRepository,
                                                           LessonsRepository lessonsRepository) {
        return new SchedulePresenterImpl(scheduleRepository, lessonsRepository);
    }
}