package heyalex.com.miet_schedule.addnewgroup;

import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
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

        public ResponseScheduleSubscriber(){
        }

        @Override
        public void onNext(SemestrData articleResponse) {
            lessonsRepository.saveAll();
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

    }
}
