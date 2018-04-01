package com.alex.miet.mobile.data.schedule;

import com.alex.miet.mobile.data.BaseRepository;

import com.alex.miet.mobile.entities.GroupItem;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * An interface for schedule repository
 */
public interface ScheduleRepository extends BaseRepository<GroupItem> {

    /**
     * Get GroupItem by group name
     *
     * @param groupName that associated with schedule
     * @return {@link GroupItem} that contains all information about schedule for {@param groupName}
     */
    Maybe<GroupItem> getGroupByName(String groupName);

    /**
     * Replace GroupItem by group name
     *
     * @param groupName that associated with schedule
     * @param model     GroupItem to replace
     */
    void replaceByGroupName(String groupName, GroupItem model);

    /**
     * Delete GroupItem by group name
     *
     * @param groupName that associated with schedule
     */
    void deleteByGroupName(String groupName);

    boolean isGroupCached(String groupName);
}
