package com.alex.miet.mobile.schedule;

import dagger.Module;
import dagger.Provides;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

@Module
public class ScheduleModule {

    @Provides
    @ScheduleScope
    /*package*/ SchedulePresenter provideSchedulePresenter(ScheduleInteractor interactor) {
        return new SchedulePresenterImpl(interactor);
    }
}