package heyalex.com.miet_schedule.navdrawer;

import javax.inject.Inject;

/**
 * Created by mac on 11.03.17.
 */

public class NavDrawerPresenterImpl implements NavDrawerPresenter {

    private static int mCurrentPosition = 0;
    private NavDrawerView view;

    @Inject
    public NavDrawerPresenterImpl() {

    }

    @Override
    public void onViewAttached(NavDrawerView view) {
        this.view = view;
        view.showCurrentPosition(mCurrentPosition);
        onNavigationItemClicked(mCurrentPosition);
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onNavigationItemClicked(int position) {
        mCurrentPosition = position;
        switch (position) {
            case 0: {
                mCurrentPosition = position;
                view.navigateToScheduleGroups();
                break;
            }
            case 1: {
                mCurrentPosition = position;
                view.navigateToOrioks();
                break;
            }
            case 2: {
                mCurrentPosition = position;
                view.navigateToNews();
                break;
            }
            case 3: {
                mCurrentPosition = position;
                view.navigateToSettings();
                break;
            }
        }
    }

}
