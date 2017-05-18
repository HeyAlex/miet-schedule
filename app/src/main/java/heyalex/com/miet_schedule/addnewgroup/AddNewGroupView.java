package heyalex.com.miet_schedule.addnewgroup;

import java.util.List;
import java.util.Set;

/**
 * Created by mac on 09.05.17.
 */

public interface AddNewGroupView {
    void showAvailibleGroups(List<String> groups);
    void showErrorView(String errorName);
    void showDownloading(String groups);
    void hideDownloading();
}
