package heyalex.com.miet_schedule.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

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

public class NewsFragment extends Fragment implements NewsView, OnRefreshListener, CommonRecyclerAdapter.ItemClickListener  {

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeToLoadLayout mSwipeRefresh;

    @Inject
    NewsPresenter presenter;

    private NewsAdapter mAdapter;


    @Override
    public void onClickDetailNews(NewsModel news) {

    }

    @Override
    public void showNews(List<NewsModel> news) {
        mAdapter.setData(news);
    }

    private void initNewsRecycler(){
        mAdapter = new NewsAdapter(getActivity());
        mAdapter.setItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void showErrorView() {

    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(refreshing);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_swipe_to_load, container, false);
        ButterKnife.bind(this, root);
        mSwipeRefresh.setOnRefreshListener(this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            // inject navigation presenter
            DaggerNewsComponent.builder()
                    .applicationComponent(ScheduleApp.get(getActivity()).getApplicationComponent())
                    .build()
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

    @Override
    public void onRefresh() {
        presenter.onRefreshRequest();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}
