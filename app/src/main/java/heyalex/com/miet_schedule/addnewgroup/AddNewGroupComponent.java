package heyalex.com.miet_schedule.addnewgroup;

import dagger.Component;
import heyalex.com.miet_schedule.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */

@AddNewGroupScope
@Component(modules = AddNewGroupModule.class, dependencies = ApplicationComponent.class)
public interface AddNewGroupComponent {
    void inject(AddNewGroupActivity activity);
}