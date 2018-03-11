package com.alex.miet.mobile.search;

import java.util.List;

/**
 * An interface for filters
 */
public interface DataFilter<T> {
    List<T> filter(List<T> all, String query);
}