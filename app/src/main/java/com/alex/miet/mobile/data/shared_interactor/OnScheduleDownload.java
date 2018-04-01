package com.alex.miet.mobile.data.shared_interactor;

import java.util.List;

import com.alex.miet.mobile.entities.LessonItem;

public interface OnScheduleDownload {
    void onGroupDownloaded(List<LessonItem> lessons, String groupName);

    void onErrorWhileDownloadingGroup(String groupName);
}
