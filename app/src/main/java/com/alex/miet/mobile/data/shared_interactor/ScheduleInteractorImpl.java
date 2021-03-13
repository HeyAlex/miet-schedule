package com.alex.miet.mobile.data.shared_interactor;

import com.alex.miet.mobile.api.UniversityApiFactory;
import com.alex.miet.mobile.data.lessons.LessonsRepository;
import com.alex.miet.mobile.data.schedule.ScheduleRepository;
import com.alex.miet.mobile.data.shortcut.ShortcutPreference;
import com.alex.miet.mobile.entities.GroupItem;
import com.alex.miet.mobile.entities.LessonItem;
import com.alex.miet.mobile.model.schedule.CycleWeeksLessonModel;
import com.alex.miet.mobile.model.schedule.Data;
import com.alex.miet.mobile.model.schedule.SemesterData;
import com.alex.miet.mobile.schedule_builder.ScheduleBuilder;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ScheduleInteractorImpl implements ScheduleInteractor {

    private CompositeDisposable scheduleDisposbleSubscription = new CompositeDisposable();

    private ScheduleRepository groupsRepository;
    private LessonsRepository lessonsRepository;
    private ShortcutPreference shortcutPreference;

    private OnScheduleDownload callbackView;
    private Disposable scheduleDisposable;


    @Inject
    public ScheduleInteractorImpl(ScheduleRepository groupsRepository,
                                  LessonsRepository lessonsRepository,
                                  ShortcutPreference shortcutPreference) {
        this.groupsRepository = groupsRepository;
        this.lessonsRepository = lessonsRepository;
        this.shortcutPreference = shortcutPreference;
    }

    /*package*/
    static List<LessonItem> transformToDaoLessonModel(SemesterData data, String groupName) {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

        List<LessonItem> lessons = new ArrayList<>();
        for (Data model : data.getData()) {
            String toDate = formatter.parseDateTime(model.getTime().getTimeTo())
                    .toString("HH:mm");
            String fromDate = formatter.parseDateTime(model.getTime().getTimeFrom())
                    .toString("HH:mm");
            String disciplineName = model.getClassModel().getName();

            String type;
            if (disciplineName.contains("[Лаб]"))
                type = "Лабораторная работа";
            else if (disciplineName.contains("[Лек]")) type = "Лекция";
            else if (disciplineName.contains("[Пр]")) type = "Практика";
            else if (disciplineName.contains("Физ")) type = "Физ-ра";
            else type = "УВЦ";

            LessonItem dataLesson = new LessonItem(groupName, Integer.valueOf(model.getDayNumber()),
                    Integer.valueOf(model.getDay()), model.getRoom().getName(), Integer.valueOf(model.getTime().getCode()),
                    model.getClassModel().getTeacher(), toDate, fromDate,
                    model.getClassModel().getTeacherFull(), disciplineName, type, model.getTime().getCode());

            lessons.add(dataLesson);
        }
        return lessons;
    }

    /*package*/
    static GroupItem transformToDaoGroupItem(SemesterData data, String groupName) {
        return new GroupItem(groupName, "");
    }

    @Override
    public void attach(OnScheduleDownload callbackView) {
        this.callbackView = callbackView;
    }

    @Override
    public void detach() {
        this.callbackView = null;
    }

    @Override
    public void downloadGroup(final String groupName) {
        if (scheduleDisposable != null) {
            if (!scheduleDisposable.isDisposed()) {
                scheduleDisposable.dispose();
            }
        }
        scheduleDisposable = UniversityApiFactory.getUniversityApi()
                .getScheduleResponse(groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<SemesterData, List<LessonItem>>() {
                    @Override
                    public List<LessonItem> apply(SemesterData semesterData) throws Exception {
                        groupsRepository.replaceByGroupName(groupName, transformToDaoGroupItem(semesterData, groupName));
                        lessonsRepository.replaceAllByGroupName(groupName, transformToDaoLessonModel(semesterData, groupName));
                        return transformToDaoLessonModel(semesterData, groupName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseScheduleObserver(groupName));

        scheduleDisposbleSubscription.add(scheduleDisposable);
    }

    @Override
    public Maybe<CycleWeeksLessonModel> getCacheGroup(String groupName) {
        return groupsRepository.getGroupByName(groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<GroupItem, CycleWeeksLessonModel>() {
                    @Override
                    public CycleWeeksLessonModel apply(GroupItem groupItem) throws Exception {
                        if (groupItem == null) {
                            downloadGroup(groupItem.getGroup());
                            return null;
                        } else {
                            return ScheduleBuilder.buildSchedule(lessonsRepository.getLessonsForGroup(groupItem.getGroup()).blockingGet());
                        }
                    }
                });
    }

    @Override
    public void stopDownloading() {
        if (scheduleDisposable != null) {
            scheduleDisposable.dispose();
        }
    }

    @Override
    public boolean isDownloadingGroup() {
        return scheduleDisposbleSubscription.size() != 0;
    }

    @Override
    public void deleteGroup(String groupName) {
        shortcutPreference.deleteStaticShortcut(groupName);
        shortcutPreference.deleteDynamicShortcut(groupName);
        groupsRepository.deleteByGroupName(groupName);
        lessonsRepository.deleteAllByGroupName(groupName);
    }

    @Override
    public Maybe<List<GroupItem>> getDownloadedGroups() {
        return groupsRepository.getAll();
    }

    @Override
    public void addNewStaticShortcut(String groupName) {
        shortcutPreference.addStaticShortcut(groupName);
    }

    @Override
    public boolean isGroupInCache(String groupName) {
        return groupsRepository.isGroupCached(groupName);
    }

    @Override
    public void requestWidgetOnHomescreen(String groupName) {
        shortcutPreference.requestWidgetPin(groupName);
    }

    public class ResponseScheduleObserver extends DisposableSingleObserver<List<LessonItem>> {

        private String groupName;

        /*package*/ ResponseScheduleObserver(String groupName) {
            this.groupName = groupName;
        }


        @Override
        public void onSuccess(List<LessonItem> semestrResponse) {
            Timber.i("Schedule for '%s' have successfully recived.", groupName);
            shortcutPreference.addNewDynamicShortcut(groupName);
            if (callbackView != null) {
                callbackView.onGroupDownloaded(semestrResponse,
                        groupName);
            }
            scheduleDisposbleSubscription.delete(this);
        }

        @Override
        public void onError(Throwable t) {
            Timber.e(t, "An error occurred while trying to take shedule for group '%s,", groupName);
            if (callbackView != null) {
                callbackView.onErrorWhileDownloadingGroup(groupName);
            }
            scheduleDisposbleSubscription.delete(this);
        }
    }
}
