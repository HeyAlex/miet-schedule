package heyalex.com.miet_schedule.schedule;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.addnewgroup.DaggerAddNewGroupComponent;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.ui.BaseNavigationActivity;

/**
 * Created by alexf on 07.04.2017.
 */

public class ScheduleActivity extends BaseNavigationActivity{

    @Inject
    SchedulePresenter presenter;

    private String groupName;

    @Override
    protected void setupNavListView() {
        ScheduleApp.get(this)
                .getScheduleComponent()
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupName = getIntent().getStringExtra("group");
    }
}
