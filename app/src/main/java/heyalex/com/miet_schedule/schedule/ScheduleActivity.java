package heyalex.com.miet_schedule.schedule;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.addnewgroup.DaggerAddNewGroupComponent;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.ui.BaseNavigationActivity;
import timber.log.Timber;

/**
 * Created by alexf on 07.04.2017.
 */

public class ScheduleActivity extends AppCompatActivity implements ScheduleView{

    @BindView(R.id.schedule_viewpager)
    ViewPager pager;
    private ScheduleViewPagerAdapter pagerAdapter;

    @Inject
    SchedulePresenter presenter;

    private String groupName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupName = getIntent().getStringExtra("group");
        ScheduleApp.get(this)
                .getScheduleComponent()
                .inject(this);
        presenter.onViewAttached(this);
        pagerAdapter = new ScheduleViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Timber.i("onPageSelected, position = %s" , position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        presenter.getCachedScheduleForGroup(groupName);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        pager.clearOnPageChangeListeners();
        presenter.onViewDetached();
    }

    @Override
    public void showSchedule() {

    }
}
