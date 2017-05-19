package heyalex.com.miet_schedule.addnewgroup;

import heyalex.com.miet_schedule.mvp.BasePresenter;

/**
 * Created by mac on 09.05.17.
 */

/*package*/ interface AddNewGroupPresenter extends BasePresenter<AddNewGroupView> {
    void getAvailableGroups();

    void addNewGroup(String groupName);

    void onSearch(String searchQuery);

    void onSearchCanceled();
}
