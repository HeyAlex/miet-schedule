package heyalex.com.miet_schedule.groups;

import dagger.Component;
import heyalex.com.miet_schedule.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */

@GroupsScope
@Component(modules = GroupsModule.class, dependencies = ApplicationComponent.class)
public interface GroupsComponent {
    void inject(GroupsFragment fragment);
}