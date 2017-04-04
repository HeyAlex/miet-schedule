package heyalex.com.miet_schedule.navdrawer;

import dagger.Component;
import dagger.Subcomponent;
import heyalex.com.miet_schedule.ApplicationComponent;
import heyalex.com.miet_schedule.mvp.BaseActivity;

@NavDrawerScope
@Component(dependencies = ApplicationComponent.class)
public interface NavDrawerComponent {

    void inject(BaseActivity activity);
}
