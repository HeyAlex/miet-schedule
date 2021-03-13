package com.alex.miet.mobile.data.shared_interactor;

import java.util.List;

import com.alex.miet.mobile.entities.GroupItem;
import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;

import io.reactivex.Maybe;

public interface ScheduleInteractor {

    void attach(OnScheduleDownload callbackView);

    void detach();

    void downloadGroup(String groupName);

    Maybe<CycleWeeksLessonModel> getCacheGroup(String groupName);

    void stopDownloading();

    boolean isDownloadingGroup();

    void deleteGroup(String groupName);

    Maybe<List<GroupItem>> getDownloadedGroups();

    void addNewStaticShortcut(String groupName);

    boolean isGroupInCache(String groupName);

    void requestWidgetOnHomescreen(String groupName);
}
