package com.hey.mietunoff.mietunofficial.addnewgroup;

import com.hey.mietunoff.mietunofficial.util.BasePresenter;

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
