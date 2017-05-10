package heyalex.com.miet_schedule.schedule_item;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.schedule.SchedulePresenter;
import heyalex.com.miet_schedule.schedule.SchedulePresenterImpl;

/**
 * Created by alexf on 11.05.2017.
 */
@Module
public class ScheduleItemModule {
    @Provides
    @PerFragment
    public ScheduleFragmentPresenter provideScheduleItemPresenter(LessonsRepository lessonsRepository) {
        return new ScheduleFragmentPresenterImpl(lessonsRepository);
    }
}
