package heyalex.com.miet_schedule.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by mac on 28.04.17.
 */

public class NewsFragment extends Fragment implements NewsView, NewsAdapter.OnNewsClickedListener {

    private NewsAdapter newsAdapter = new NewsAdapter(this);

    @Inject
    NewsPresenter presenter;

    @BindView(R.id.news_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.news_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.news_progress_bar)
    ProgressBar progressBar;


    @Override
    public void showNews(List<NewsModel> news) {
        newsAdapter.setItems(news);
    }

    private void initViews() {
        int margin = (int)getResources().getDimension(R.dimen.dimen_16dp) / 2;
        recyclerView.addItemDecoration(new MarginItemDecorator(margin, margin));
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsAdapter);
        //recyclerView.setItemAnimator(new OvershootInLeftAnimator());
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        progressBar.setVisibility(refreshing ? View.VISIBLE : View.INVISIBLE);
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

    @Override
    public void onNewsClicked(NewsModel newsModel) {
        //TODO: STARTACTIVITY
    }
}
