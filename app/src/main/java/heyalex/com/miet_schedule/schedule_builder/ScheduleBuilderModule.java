package heyalex.com.miet_schedule.schedule_builder;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.schedule.ScheduleScope;

/**
 * Created by alexf on 14.05.2017.
 */

@Module
public class ScheduleBuilderModule {

    @Provides
    @ScheduleScope
    /*package*/ ScheduleBuilderHelper provideScheduleBuilder() {
        return new ScheduleBuilderHelperImpl();
    }
}