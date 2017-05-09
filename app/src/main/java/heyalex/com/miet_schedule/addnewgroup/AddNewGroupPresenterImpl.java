package heyalex.com.miet_schedule.addnewgroup;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.model.schedule.Data;
import heyalex.com.miet_schedule.model.schedule.SemestrData;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by mac on 09.05.17.
 */

public class AddNewGroupPresenterImpl implements AddNewGroupPresenter {

    private ScheduleRepository groupsRepository;
    private LessonsRepository lessonsRepository;
    private AddNewGroupView view;

    public AddNewGroupPresenterImpl(ScheduleRepository groupsRepository,
                                    LessonsRepository lessonsRepository) {
        this.groupsRepository = groupsRepository;
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void getAvailableGroups() {

    }

    @Override
    public void addNewGroup(String groupName) {

    }

    @Override
    public void onViewAttached(AddNewGroupView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }


    private class ResponseScheduleSubscriber extends DisposableObserver<SemestrData> {

        private String groupName;

        public ResponseScheduleSubscriber(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public void onNext(SemestrData semestrResponse) {
            lessonsRepository.replaceAllByGroupName(groupName,
                    transformToDaoLessonModel(semestrResponse, groupName));
            groupsRepository.replaceByGroupName(groupName,
                    transformToDaoScheduleModel(semestrResponse, groupName));
        }

        @Override
        public void onError(Throwable t) {

            if (view != null) {
                view.showErrorView();
            }
        }

        @Override
        public void onComplete() {

        }

        private List<LessonModel> transformToDaoLessonModel(SemestrData data, String groupName) {
            final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

            List<LessonModel> lessons = new ArrayList<>();
            for (Data model : data.getData()) {
                String toDate = formatter.parseDateTime(model.getTime().getTimeTo())
                        .toString("HH:mm");
                String fromDate = formatter.parseDateTime(model.getTime().getTimeFrom())
                        .toString("HH:mm");
                String disciplineName = model.getClassModel().getName();
                LessonModel dataLesson = new LessonModel();
                dataLesson.setWeek(Integer.valueOf(model.getDay()));
                dataLesson.setDay(Integer.valueOf(model.getDayNumber()));
                dataLesson.setGroupName(groupName);
                dataLesson.setRoom(model.getRoom().getName());
                dataLesson.setTimeFrom(fromDate);
                dataLesson.setTimeTo(toDate);
                dataLesson.setTime(fromDate + " - " + toDate + " (" + model.getTime().getTime() + ")");
                dataLesson.setDisciplineName(disciplineName);
                dataLesson.setTeacherFull(model.getClassModel().getTeacherFull());
                dataLesson.setTeacher(model.getClassModel().getTeacher());
                if (disciplineName.contains("[Лаб]")) dataLesson.setDisciplineType("Лабораторная работа");
                else if (disciplineName.contains("[Лек]")) dataLesson.setDisciplineType("Лекция");
                else if (disciplineName.contains("[Пр]")) dataLesson.setDisciplineType("Практика");
                else if (disciplineName.contains("Физ")) dataLesson.setDisciplineType("Физ-ра");
                else dataLesson.setDisciplineType("УВЦ");
                lessons.add(dataLesson);
            }
            return lessons;
        }

        private ScheduleModel transformToDaoScheduleModel(SemestrData data, String groupName) {
            return new ScheduleModel(groupName, data.getSemestr());
        }
    }
}
