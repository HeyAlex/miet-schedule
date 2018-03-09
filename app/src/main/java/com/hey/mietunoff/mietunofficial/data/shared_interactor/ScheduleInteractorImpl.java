package com.hey.mietunoff.mietunofficial.data.shared_interactor;

import com.hey.mietunoff.mietunofficial.api.UniversityApiFactory;
import com.hey.mietunoff.mietunofficial.data.lessons.LessonsRepository;
import com.hey.mietunoff.mietunofficial.data.schedule.ScheduleRepository;
import com.hey.mietunoff.mietunofficial.model.schedule.CycleWeeksLessonModel;
import com.hey.mietunoff.mietunofficial.model.schedule.Data;
import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilder;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.ScheduleModel;

import com.hey.mietunoff.mietunofficial.data.shortcut.ShortcutPreference;
import com.hey.mietunoff.mietunofficial.model.schedule.SemesterData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
    static List<LessonModel> transformToDaoLessonModel(SemesterData data, String groupName) {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

        List<LessonModel> lessons = new ArrayList<>();
        for (Data model : data.getData()) {
            String toDate = formatter.parseDateTime(model.getTime().getTimeTo())
                    .toString("HH:mm");
            String fromDate = formatter.parseDateTime(model.getTime().getTimeFrom())
                    .toString("HH:mm");
            String disciplineName = model.getClassModel().getName();
            LessonModel dataLesson = new LessonModel();
            dataLesson.setWeek(Integer.valueOf(model.getDayNumber()));
            dataLesson.setDay(Integer.valueOf(model.getDay()));
            dataLesson.setGroupName(groupName);
            dataLesson.setRoom(model.getRoom().getName());
            dataLesson.setTimeFrom(fromDate);
            dataLesson.setTimeTo(toDate);
            dataLesson.setTime(Integer.valueOf(model.getTime().getCode()));
            dataLesson.setTimeFull(fromDate + " - " + toDate + " (" + model.getTime().getTime() + ")");
            dataLesson.setDisciplineName(disciplineName);
            dataLesson.setTeacherFull(model.getClassModel().getTeacherFull());
            dataLesson.setTeacher(model.getClassModel().getTeacher());
            dataLesson.setCode(model.getTime().getCode());
            if (disciplineName.contains("[Лаб]"))
                dataLesson.setDisciplineType("Лабораторная работа");
            else if (disciplineName.contains("[Лек]")) dataLesson.setDisciplineType("Лекция");
            else if (disciplineName.contains("[Пр]")) dataLesson.setDisciplineType("Практика");
            else if (disciplineName.contains("Физ")) dataLesson.setDisciplineType("Физ-ра");
            else dataLesson.setDisciplineType("УВЦ");

            lessons.add(dataLesson);
        }
        return lessons;
    }

    /*package*/
    static ScheduleModel transformToDaoScheduleModel(SemesterData data, String groupName) {
        return new ScheduleModel(groupName, data.getSemester());
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
    public void downloadGroup(String groupName) {
        if (scheduleDisposable != null) {
            if (!scheduleDisposable.isDisposed()) {
                scheduleDisposable.dispose();
            }
        }
        scheduleDisposable = UniversityApiFactory.getUniversityApi()
                .getScheduleResponse(groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseScheduleObserver(groupName));

        scheduleDisposbleSubscription.add(scheduleDisposable);
    }

    @Override
    public CycleWeeksLessonModel getCacheGroup(String groupName) {
        try {
            return ScheduleBuilder.buildSchedule(groupsRepository.getGroupByName(groupName).getLessons());
        } catch (CloneNotSupportedException e) {
            return null; // never happens
        }
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
    public List<ScheduleModel> getDownloadedGroups() {
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

    public class ResponseScheduleObserver extends DisposableSingleObserver<SemesterData> {

        private String groupName;

        /*package*/ ResponseScheduleObserver(String groupName) {
            this.groupName = groupName;
        }


        @Override
        public void onSuccess(SemesterData semestrResponse) {
            Timber.i("Schedule for '%s' have successfully recived.", groupName);
            lessonsRepository.replaceAllByGroupName(groupName,
                    transformToDaoLessonModel(semestrResponse, groupName));
            groupsRepository.replaceByGroupName(groupName,
                    transformToDaoScheduleModel(semestrResponse, groupName));
            shortcutPreference.addNewDynamicShortcut(groupName);
            if (callbackView != null) {
                callbackView.onGroupDownloaded(transformToDaoLessonModel(semestrResponse, groupName),
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
