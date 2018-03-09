package com.hey.mietunoff.mietunofficial.data.shared_interactor;

import java.util.List;

import heyalex.com.miet_schedule.LessonModel;

public interface OnScheduleDownload {
    void onGroupDownloaded(List<LessonModel> lessons, String groupName);

    void onErrorWhileDownloadingGroup(String groupName);
}
