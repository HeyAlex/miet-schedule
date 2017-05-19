package heyalex.com.miet_schedule.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.NewsModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.util.MarginItemDecorator;
import timber.log.Timber;

/**
 * Created by mac on 28.04.17.
 */

public class NewsFragment extends Fragment implements NewsView, NewsAdapter.OnNewsClickedListener,
        SwipeRefreshLayout.OnRefreshListener{

    private NewsAdapter newsAdapter = new NewsAdapter(this);

    @Inject
    NewsPresenter presenter;

    @BindView(R.id.news_root)
    View root;

    @BindView(R.id.news_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.news_recycleview)
    RecyclerView recyclerView;


    @Override
    public void showNews(List<NewsModel> news) {
        newsAdapter.setItems(news);
    }

    private void initViews() {
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showErrorView() {
        Snackbar.make(root, R.string.error_news_downloading, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_fragment, container, false);
        ButterKnife.bind(this, root);
        initViews();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            ScheduleApp.get(getContext())
                    .getNewsComponent()
                    .inject(this);
            presenter.onRefreshRequest();
        }
        presenter.onViewAttached(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy");
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    @Override
    public void onNewsClicked(NewsModel newsModel) {
        //TODO: STARTACTIVITY
    }

    @Override
    public void onRefresh() {
        presenter.onRefreshRequest();
    }
}
