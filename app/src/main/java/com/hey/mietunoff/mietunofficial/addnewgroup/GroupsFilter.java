package com.hey.mietunoff.mietunofficial.addnewgroup;

import java.util.ArrayList;
import java.util.List;

import com.hey.mietunoff.mietunofficial.search.DataFilter;

/**
 * Created by alexf on 16.05.2017.
 */

/*package*/ public class GroupsFilter implements DataFilter<String> {
    @Override
    public List<String> filter(List<String> all, String query) {
        query = query.toUpperCase();
        List<String> filtered = new ArrayList<>();
        for (String groupName : all) {
            if (groupName.toUpperCase().contains(query)) {
                filtered.add(groupName);
            }
        }
        return filtered;
    }
}
