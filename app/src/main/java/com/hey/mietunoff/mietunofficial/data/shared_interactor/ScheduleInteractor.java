package com.hey.mietunoff.mietunofficial.data.shared_interactor;

import java.util.List;

import com.hey.mietunoff.mietunofficial.ScheduleModel;
import com.hey.mietunoff.mietunofficial.model.schedule.CycleWeeksLessonModel;

public interface ScheduleInteractor {

    void attach(OnScheduleDownload callbackView);

    void detach();

    void downloadGroup(String groupName);

    CycleWeeksLessonModel getCacheGroup(String groupName);

    void stopDownloading();

    boolean isDownloadingGroup();

    void deleteGroup(String groupName);

    List<ScheduleModel> getDownloadedGroups();

    void addNewStaticShortcut(String groupName);

    boolean isGroupInCache(String groupName);

    void requestWidgetOnHomescreen(String groupName);
}
