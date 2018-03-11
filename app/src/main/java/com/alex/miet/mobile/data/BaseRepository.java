package com.alex.miet.mobile.data;

import java.util.List;

/**
 * Generic interface for managing dao entities
 *
 * @param <T> dao entity
 */
public interface BaseRepository<T> {

    void save(T entity);

    void saveAll(Iterable<T> entities);

    List<T> getAll();

    void update(T entity);

    void updateAll(Iterable<T> entities);

    void delete(T entity);

    void deleteAll();
}
