package heyalex.com.miet_schedule.addnewgroup;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.api.UniversityApiFactory;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.model.schedule.Data;
import heyalex.com.miet_schedule.model.schedule.SemestrData;
import heyalex.com.miet_schedule.search.DataFilter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mac on 09.05.17.
 */

public class AddNewGroupPresenterImpl implements AddNewGroupPresenter {

    private ScheduleRepository groupsRepository;
    private LessonsRepository lessonsRepository;
    private AddNewGroupView view;
    private String searchQuery = "";
    private List<String> cachedGroups;
    private DataFilter<String> groupFilter = new GroupsFilter();
    private final CompositeDisposable scheduleResponseSubscription = new CompositeDisposable();

    @Inject
    public AddNewGroupPresenterImpl(ScheduleRepository groupsRepository,
                                    LessonsRepository lessonsRepository) {
        this.groupsRepository = groupsRepository;
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void getAvailableGroups() {
        view.showDownloading("доступные группы");
        scheduleResponseSubscription.add(UniversityApiFactory.getUniversityApi().getGroupNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseAvailableGroups()));
    }

    @Override
    public void addNewGroup(String groupName) {
        view.showDownloading(groupName);
        scheduleResponseSubscription.add(UniversityApiFactory.getUniversityApi()
                .getScheduleResponse(groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseScheduleObserver(groupName)));
    }

    @Override
    public void onSearch(String searchQuery) {
        this.searchQuery = searchQuery;
        showGroups();
    }

    @Override
    public void onSearchCanceled() {
        searchQuery = "";
        showGroups();
    }

    private boolean isSearching() {
        return searchQuery != null && !searchQuery.isEmpty();
    }

    private void showGroups() {
        if (cachedGroups != null) {
            if (isSearching()) {
                view.showAvailibleGroups(groupFilter.filter(cachedGroups, searchQuery));
            } else {
                view.showAvailibleGroups(cachedGroups);
            }
        } else {
            getAvailableGroups();
        }
    }
    @Override
    public void onViewAttached(AddNewGroupView view) {
        this.view = view;
        getAvailableGroups();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }


    private class ResponseScheduleObserver extends DisposableObserver<SemestrData> {

        private String groupName;

        public ResponseScheduleObserver(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public void onNext(SemestrData semestrResponse) {
            Timber.i("Schedule for '%s' have successfully recived.", groupName);
            lessonsRepository.replaceAllByGroupName(groupName,
                    transformToDaoLessonModel(semestrResponse, groupName));
            groupsRepository.replaceByGroupName(groupName,
                    transformToDaoScheduleModel(semestrResponse, groupName));
            view.hideDownloading();
        }

        @Override
        public void onError(Throwable t) {
            Timber.e(t, "An error occurred while trying to take shedule for group '%s,", groupName);
            if (view != null) {
                view.hideDownloading();
                view.showErrorView(groupName);
            }
        }

        @Override
        public void onComplete() {

        }
    }

    public static List<LessonModel> transformToDaoLessonModel(SemestrData data, String groupName) {
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
            if (disciplineName.contains("[Лаб]")) dataLesson.setDisciplineType("Лабораторная работа");
            else if (disciplineName.contains("[Лек]")) dataLesson.setDisciplineType("Лекция");
            else if (disciplineName.contains("[Пр]")) dataLesson.setDisciplineType("Практика");
            else if (disciplineName.contains("Физ")) dataLesson.setDisciplineType("Физ-ра");
            else dataLesson.setDisciplineType("УВЦ");

            lessons.add(dataLesson);
        }
        return lessons;
    }

    public static ScheduleModel transformToDaoScheduleModel(SemestrData data, String groupName) {
        return new ScheduleModel(groupName, data.getSemestr());
    }

    private class ResponseAvailableGroups extends DisposableObserver<List<String>>{

        @Override
        public void onNext(List<String> value) {
            Timber.i("Set of groups have successfully recived.");
            if (view != null) {
                cachedGroups = value;
                view.showAvailibleGroups(value);
                view.hideDownloading();
            }
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e, "An error occurred while trying to take groups");
            view.hideDownloading();
            view.showErrorView("при скачивании доступных групп.");
        }

        @Override
        public void onComplete() {

        }
    }
}
