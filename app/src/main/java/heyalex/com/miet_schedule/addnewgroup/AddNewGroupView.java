package heyalex.com.miet_schedule.addnewgroup;

import java.util.List;

/**
 * Created by mac on 09.05.17.
 */

/*package*/ interface AddNewGroupView {

    void showAvailibleGroups(List<String> groups);

    void showErrorView(String errorName);

    void showDownloading(String groups);

    void hideDownloading();
}
