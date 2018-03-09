package com.hey.mietunoff.mietunofficial.data.schedule;

import com.hey.mietunoff.mietunofficial.data.BaseRepository;

import com.hey.mietunoff.mietunofficial.ScheduleModel;

/**
 * An interface for schedule repository
 */
public interface ScheduleRepository extends BaseRepository<ScheduleModel> {

    /**
     * Get ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     * @return {@link ScheduleModel} that contains all information about schedule for {@param groupName}
     */
    ScheduleModel getGroupByName(String groupName);

    /**
     * Replace ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     * @param model     ScheduleModel to replace
     */
    void replaceByGroupName(String groupName, ScheduleModel model);

    /**
     * Delete ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     */
    void deleteByGroupName(String groupName);

    boolean isGroupCached(String groupName);
}
