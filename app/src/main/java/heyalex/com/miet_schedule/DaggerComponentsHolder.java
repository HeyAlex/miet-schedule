package heyalex.com.miet_schedule;

import android.content.Context;


import heyalex.com.miet_schedule.navdrawer.NavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerModule;
import timber.log.Timber;

public class DaggerComponentsHolder {
  private static final DaggerComponentsHolder INSTANCE = new DaggerComponentsHolder();
  private ApplicationComponent applicationComponent;

  private NavDrawerComponent navDrawerComponent;

  public static DaggerComponentsHolder getInstance() {
    return INSTANCE;
  }

  private DaggerComponentsHolder(){
  }

  public void initApplicationComponent(Context context) {
    if (applicationComponent != null) {
      Timber.w("Application component is already initialized");
    }
    applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(context))
            .build();
  }

  public NavDrawerComponent getNavDrawerComponent() {
    if (navDrawerComponent == null) {
      applicationComponent.plus(new NavDrawerModule());
    }
    return navDrawerComponent;
  }

  public void releaseNavDrawerComponent() {
    navDrawerComponent = null;
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
