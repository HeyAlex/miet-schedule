package com.hey.mietunoff.mietunofficial.groups;

import com.hey.mietunoff.mietunofficial.data.shared_interactor.ScheduleInteractor;

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