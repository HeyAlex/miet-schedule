package com.alex.miet.mobile.news;

import com.alex.miet.mobile.data.news.NewsRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 28.04.17.
 */

@Module
public class NewsModule {

    @Provides
    @NewsScope
        /*package*/ NewsPresenter provideNewsPresenter(NewsRepository newsRepository) {
        return new NewsPresenterImpl(newsRepository);
    }
}
