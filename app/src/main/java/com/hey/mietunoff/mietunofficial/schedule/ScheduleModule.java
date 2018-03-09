package com.hey.mietunoff.mietunofficial.schedule;

import dagger.Module;
import dagger.Provides;
import com.hey.mietunoff.mietunofficial.data.shared_interactor.ScheduleInteractor;

@Module
public class ScheduleModule {

    @Provides
    @ScheduleScope
    /*package*/ SchedulePresenter provideSchedulePresenter(ScheduleInteractor interactor) {
        return new SchedulePresenterImpl(interactor);
    }
}