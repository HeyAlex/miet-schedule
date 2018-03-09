package com.hey.mietunoff.mietunofficial.news;

import com.hey.mietunoff.mietunofficial.data.news.NewsRepository;

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
