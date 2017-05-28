package heyalex.com.miet_schedule.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.groups.GroupsFragment;
import heyalex.com.miet_schedule.news.NewsFragment;
import heyalex.com.miet_schedule.ui.BaseNavigationActivity;
import heyalex.com.miet_schedule.ui.BaseWebFragement;
import heyalex.com.miet_schedule.util.NavigationUtil;

/**
 * Created by alexf on 04.04.2017.
 */
public class NavDrawerActivity extends BaseNavigationActivity implements NavDrawerView,
        NavAdapter.OnNavClickedListener {

    private static final String FRAGMENT_TAG_GROUPS = "FRAGMENT_TAG_GROUPS";
    private static final String FRAGMENT_TAG_SETTINGS = "FRAGMENT_TAG_SETTINGS";
    private static final String FRAGMENT_TAG_ORIOKS = "FRAGMENT_TAG_ORIOKS";
    private static final String FRAGMENT_TAG_NEWS = "FRAGMENT_TAG_NEWS";
    private static final String ORIOKS_URL = "https://orioks.miet.ru/student/student";

    private NavAdapter navAdapter;

    @Inject
    NavDrawerPresenter navDrawerPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdrawer_main);
        navDrawerPresenter.onViewAttached(this);
    }

    @Override
    protected void setupNavListView() {
        navAdapter = new NavAdapter(this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(navAdapter);

        DaggerNavDrawerComponent.builder()
                .applicationComponent(ScheduleApp.get(this).getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    public void onNavClicked(int position) {
        navDrawerPresenter.onNavigationItemClicked(position);
        if (toolbar != null) {
            toolbar.setTitle(NavigationUtil.drawerList[position]);
        }
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void navigateToNews() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_NEWS);
        if (fragment == null) {
            fragment = new NewsFragment();
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, fragment, FRAGMENT_TAG_NEWS)
                .commitNow();
    }

    @Override
    public void navigateToScheduleGroups() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_GROUPS);
        if (fragment == null) {
            fragment = new GroupsFragment();
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, fragment, FRAGMENT_TAG_GROUPS)
                .commitNow();

    }

    @Override
    public void navigateToOrioks() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_ORIOKS);
        if (fragment == null) {
            fragment = BaseWebFragement.newInstance(ORIOKS_URL);
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, fragment, FRAGMENT_TAG_ORIOKS)
                .commitNow();

    }

    @Override
    public void navigateToSettings() {
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_SETTINGS);
//        if (fragment == null) {
//        //    fragment = new SettingsFragment();
//            fragment.setRetainInstance(true);
//        }
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_content, fragment, FRAGMENT_TAG_SETTINGS)
//                .commitNow();

    }

    @Override
    public void showCurrentPosition(int position) {
        navAdapter.setCurrentPos(position);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(NavigationUtil.drawerList[position]);
        }
    }

    @Override
    protected void onDestroy() {
        if (navDrawerPresenter != null) {
            navDrawerPresenter.onViewDetached();
        }
        super.onDestroy();
    }
}
