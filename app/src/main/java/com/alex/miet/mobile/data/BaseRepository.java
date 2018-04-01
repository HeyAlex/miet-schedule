package com.alex.miet.mobile.data;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Generic interface for managing dao entities
 *
 * @param <T> dao entity
 */
public interface BaseRepository<T> {

    void save(T entity);

    void saveAll(List<T> entities);

    Maybe<List<T>> getAll();

    void update(T entity);

    void updateAll(List<T> entities);

    void delete(T entity);

    void deleteAll();
}
