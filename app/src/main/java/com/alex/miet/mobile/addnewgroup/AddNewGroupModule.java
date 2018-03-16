package com.alex.miet.mobile.addnewgroup;

import dagger.Module;
import dagger.Provides;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

@Module
public class AddNewGroupModule {

    @Provides
    @AddNewGroupScope
    /*package*/ AddNewGroupPresenter provideAddNewGroupPresenter(ScheduleInteractor interactor) {
        return new AddNewGroupPresenterImpl(interactor);
    }
}