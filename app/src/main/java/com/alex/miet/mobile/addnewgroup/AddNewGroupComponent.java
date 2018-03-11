package com.alex.miet.mobile.addnewgroup;

import dagger.Component;
import com.alex.miet.mobile.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */

@AddNewGroupScope
@Component(modules = AddNewGroupModule.class, dependencies = ApplicationComponent.class)
public interface AddNewGroupComponent {
    void inject(AddNewGroupActivity activity);
}