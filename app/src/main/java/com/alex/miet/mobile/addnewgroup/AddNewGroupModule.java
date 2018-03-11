package com.alex.miet.mobile.addnewgroup;

import dagger.Module;
import dagger.Provides;
import com.alex.miet.mobile.data.shared_interactor.ScheduleInteractor;

/**
 * Created by mac on 09.05.17.
 */

@Module
public class AddNewGroupModule {

    @Provides
    @AddNewGroupScope
    /*package*/ AddNewGroupPresenter provideAddNewGroupPresenter(ScheduleInteractor interactor) {
        return new AddNewGroupPresenterImpl(interactor);
    }
}