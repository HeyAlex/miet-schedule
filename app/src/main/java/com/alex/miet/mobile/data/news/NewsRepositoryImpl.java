package com.alex.miet.mobile.data.news;

import java.util.List;

import com.alex.miet.mobile.NewsModel;
import com.alex.miet.mobile.NewsModelDao;

/**
 * Specific {@link NewsRepository} implementation
 */
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsModelDao dao;

    public NewsRepositoryImpl(NewsModelDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(NewsModel entity) {
        dao.save(entity);
    }

    @Override
    public void saveAll(Iterable<NewsModel> entities) {
        dao.saveInTx(entities);
    }

    @Override
    public List<NewsModel> getAll() {
        return dao.loadAll();
    }

    @Override
    public void update(NewsModel entity) {
        dao.update(entity);
    }

    @Override
    public void updateAll(Iterable<NewsModel> entities) {
        dao.updateInTx(entities);
    }

    @Override
    public void delete(NewsModel entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
