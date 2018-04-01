package com.alex.miet.mobile.data.shared_interactor;

import java.util.List;

import com.alex.miet.mobile.entities.GroupItem;
import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;

public interface ScheduleInteractor {

    void attach(OnScheduleDownload callbackView);

    void detach();

    void downloadGroup(String groupName);

    CycleWeeksLessonModel getCacheGroup(String groupName);

    void stopDownloading();

    boolean isDownloadingGroup();

    void deleteGroup(String groupName);

    List<GroupItem> getDownloadedGroups();

    void addNewStaticShortcut(String groupName);

    boolean isGroupInCache(String groupName);

    void requestWidgetOnHomescreen(String groupName);
}
