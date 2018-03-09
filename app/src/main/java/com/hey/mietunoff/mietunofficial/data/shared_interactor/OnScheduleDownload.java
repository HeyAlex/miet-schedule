package com.hey.mietunoff.mietunofficial.data.shared_interactor;

import java.util.List;

import com.hey.mietunoff.mietunofficial.LessonModel;

public interface OnScheduleDownload {
    void onGroupDownloaded(List<LessonModel> lessons, String groupName);

    void onErrorWhileDownloadingGroup(String groupName);
}
