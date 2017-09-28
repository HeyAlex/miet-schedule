package heyalex.com.miet_schedule.schedule;

import dagger.Module;
import dagger.Provides;
import heyalex.com.miet_schedule.data.shared_interactor.ScheduleInteractor;

@Module
public class ScheduleModule {

    @Provides
    @ScheduleScope
    /*package*/ SchedulePresenter provideSchedulePresenter(ScheduleInteractor interactor) {
        return new SchedulePresenterImpl(interactor);
    }
}