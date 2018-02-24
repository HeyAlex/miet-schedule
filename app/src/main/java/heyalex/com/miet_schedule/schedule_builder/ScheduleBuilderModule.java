package heyalex.com.miet_schedule.schedule_builder;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.schedule.ScheduleScope;

@Module
public class ScheduleBuilderModule {

    @Provides
    @ScheduleScope
    /*package*/ ScheduleBuilderHelper provideScheduleBuilder() {
        return new ScheduleBuilderHelperImpl();
    }
}