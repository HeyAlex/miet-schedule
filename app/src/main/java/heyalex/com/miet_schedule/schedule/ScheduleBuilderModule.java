package heyalex.com.miet_schedule.schedule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;

/**
 * Created by alexf on 14.05.2017.
 */

@Module
public class ScheduleBuilderModule{

    @Provides
    @ScheduleScope
    public ScheduleBuilderHelper provideScheduleBuilder(LessonsRepository lessonsRepository) {
        return new ScheduleBuilderHelperImpl(lessonsRepository);
    }
}