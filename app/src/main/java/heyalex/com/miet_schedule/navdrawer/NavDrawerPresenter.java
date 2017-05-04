package heyalex.com.miet_schedule.navdrawer;

import heyalex.com.miet_schedule.mvp.BasePresenter;

public interface NavDrawerPresenter extends BasePresenter<NavDrawerView> {

    void onNavigationItemClicked(int position);
}
