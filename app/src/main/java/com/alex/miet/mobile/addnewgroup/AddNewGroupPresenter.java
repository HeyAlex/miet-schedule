package com.alex.miet.mobile.addnewgroup;

import com.alex.miet.mobile.util.BasePresenter;

/**
 * Created by mac on 09.05.17.
 */
/*package*/ interface AddNewGroupPresenter extends BasePresenter<AddNewGroupView> {

    void showGroups();

    void addNewGroup(String groupName);

    void onSearch(String searchQuery);

    void onSearchCanceled();

    void onCancelDownloadingGroup();
}
