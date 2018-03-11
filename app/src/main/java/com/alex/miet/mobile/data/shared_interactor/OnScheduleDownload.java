package com.alex.miet.mobile.data.shared_interactor;

import java.util.List;

import com.alex.miet.mobile.LessonModel;

public interface OnScheduleDownload {
    void onGroupDownloaded(List<LessonModel> lessons, String groupName);

    void onErrorWhileDownloadingGroup(String groupName);
}
