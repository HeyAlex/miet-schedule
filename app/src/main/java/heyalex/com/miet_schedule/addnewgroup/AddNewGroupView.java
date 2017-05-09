package heyalex.com.miet_schedule.addnewgroup;

import java.util.Set;

/**
 * Created by mac on 09.05.17.
 */

public interface AddNewGroupView {
    void showAvailibleGroups(Set<String> groups);
    void showErrorView();
}
