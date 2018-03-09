package com.hey.mietunoff.mietunofficial.news;

import dagger.Component;
import com.hey.mietunoff.mietunofficial.ApplicationComponent;

/**
 * Created by mac on 03.05.17.
 */

@NewsScope
@Component(modules = NewsModule.class, dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);
}
