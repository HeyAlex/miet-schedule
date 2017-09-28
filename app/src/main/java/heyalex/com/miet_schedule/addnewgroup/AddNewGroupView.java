package heyalex.com.miet_schedule.addnewgroup;

import java.util.List;

/*package*/ interface AddNewGroupView {

    void showAvailibleGroups(List<String> groups);

    void showErrorView(String errorName);

    void showDownloadingAvailibleGroups();

    void showDownloadingGroup(String groups);

    void hideDownloading();
}
