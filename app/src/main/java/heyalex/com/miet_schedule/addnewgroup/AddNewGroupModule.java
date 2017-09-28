package heyalex.com.miet_schedule.addnewgroup;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractor;

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