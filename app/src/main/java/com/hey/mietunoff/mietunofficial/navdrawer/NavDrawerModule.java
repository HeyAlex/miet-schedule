package com.hey.mietunoff.mietunofficial.navdrawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 11.03.17.
 */

@Module
public class NavDrawerModule {

    @Singleton
    @Provides
    /*package*/ NavDrawerPresenter provideNavDrawerPresenter() {
        return new NavDrawerPresenterImpl();
    }
}

