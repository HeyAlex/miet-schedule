package com.alex.miet.mobile.news;

import dagger.Component;
import com.alex.miet.mobile.ApplicationComponent;

/**
 * Created by mac on 03.05.17.
 */

@NewsScope
@Component(modules = NewsModule.class, dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);
}
