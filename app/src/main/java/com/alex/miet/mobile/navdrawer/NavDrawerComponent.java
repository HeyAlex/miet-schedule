package com.alex.miet.mobile.navdrawer;

import dagger.Component;
import com.alex.miet.mobile.ApplicationComponent;

@NavDrawerScope
@Component(dependencies = ApplicationComponent.class)
public interface NavDrawerComponent {
    void inject(NavDrawerActivity activity);
}
