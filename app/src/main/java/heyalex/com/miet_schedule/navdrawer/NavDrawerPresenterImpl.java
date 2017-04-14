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
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onNavigationItemClicked(int position) {
        mCurrentPosition = position;
        switch (position){
            case 0:{
                view.showScheduleGroups();
                break;
            }
            case 1:{
                view.showOrioks();
                break;
            }
            case 2:{
                view.showNews();
                break;
            }
            case 3:{
                view.showSettings();
                break;
            }
        }
    }

    @Override
    public void showLastFrament() {
        view.showCurrentPosition(mCurrentPosition);
        onNavigationItemClicked(mCurrentPosition);

    }

}
