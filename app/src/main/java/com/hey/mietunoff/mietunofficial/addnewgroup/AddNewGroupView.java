package com.hey.mietunoff.mietunofficial.addnewgroup;

import java.util.List;

/*package*/ interface AddNewGroupView {

    void showAvailibleGroups(List<String> groups);

    void showErrorView(String errorName);

    void showDownloadingAvailibleGroups();

    void showDownloadingGroup(String group);

    void hideDownloading();

    void finishPickingGroups();
}
