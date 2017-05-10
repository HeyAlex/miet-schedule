package heyalex.com.miet_schedule.schedule;

import dagger.Component;
import heyalex.com.miet_schedule.ApplicationComponent;
import heyalex.com.miet_schedule.ScheduleModel;

/**
 * Created by mac on 10.05.17.
 */

@ScheduleScope
@Component(modules = ScheduleModule.class, dependencies = ApplicationComponent.class)
public interface ScheduleComponent {
    void inject(ScheduleActivity activity);
}

