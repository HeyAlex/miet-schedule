package com.hey.mietunoff.mietunofficial.addnewgroup;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.ApplicationComponent;

/**
 * Created by mac on 09.05.17.
 */

@AddNewGroupScope
@Component(modules = AddNewGroupModule.class, dependencies = ApplicationComponent.class)
public interface AddNewGroupComponent {
    void inject(AddNewGroupActivity activity);
}