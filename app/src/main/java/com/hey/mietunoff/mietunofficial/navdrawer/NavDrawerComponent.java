package com.hey.mietunoff.mietunofficial.navdrawer;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.ApplicationComponent;

@NavDrawerScope
@Component(dependencies = ApplicationComponent.class)
public interface NavDrawerComponent {
    void inject(NavDrawerActivity activity);
}
