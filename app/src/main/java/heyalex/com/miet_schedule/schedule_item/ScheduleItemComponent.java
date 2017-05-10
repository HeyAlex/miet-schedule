package heyalex.com.miet_schedule.schedule_item;

import dagger.Component;
import heyalex.com.miet_schedule.ApplicationComponent;

/**
 * Created by alexf on 11.05.2017.
 */
@PerFragment
@Component( modules = {ScheduleItemModule.class}, dependencies = {ApplicationComponent.class} )
public interface ScheduleItemComponent {
    void inject(ScheduleFragment fragment);
}
