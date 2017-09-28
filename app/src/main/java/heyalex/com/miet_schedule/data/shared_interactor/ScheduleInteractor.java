package heyalex.com.miet_schedule.data.shared_interactor;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;

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
}
