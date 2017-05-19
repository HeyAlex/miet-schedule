package heyalex.com.miet_schedule.addnewgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import heyalex.com.miet_schedule.search.DataFilter;

/**
 * Created by alexf on 16.05.2017.
 */

/*package*/ class GroupsFilter implements DataFilter<String>{
    @Override
    public List<String> filter(List<String> all, String query) {
        query = query.toUpperCase();
        List<String> filtered = new ArrayList<>();
        for (String groupName : all) {
            if(groupName.toUpperCase().contains(query)){
                filtered.add(groupName);
            }
        }
        return filtered;
    }
}
