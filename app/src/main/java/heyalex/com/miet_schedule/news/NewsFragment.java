package heyalex.com.miet_schedule.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;

/**
 * Created by mac on 28.04.17.
 */

public class NewsFragment extends Fragment implements NewsView {



    @Inject
    NewsPresenter presenter;


    @Override
    public void onClickDetailNews(NewsModel news) {

    }

    @Override
    public void showNews(List<NewsModel> news) {

    }

    private void initNewsRecycler(){

    }
    @Override
    public void showErrorView() {

    }

    @Override
    public void setRefreshing(final boolean refreshing) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_navdrawer_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            ScheduleApp.get(getContext())
                    .getScheduleComponent()
                    .inject(this);
        }
        presenter.onViewAttached(this);

        presenter.onRefreshRequest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

}
