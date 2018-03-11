package com.alex.miet.mobile.groups;

import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class GroupsModule {

    @Provides
    @GroupsScope
    /*package*/ GroupsPresenter provideGroupsPresenter(ScheduleInteractor interactor) {
        return new GroupsPresenterImpl(interactor);
    }
}