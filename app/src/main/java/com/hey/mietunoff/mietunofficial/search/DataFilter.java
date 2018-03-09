package com.hey.mietunoff.mietunofficial.search;

import java.util.List;

/**
 * An interface for filters
 */
public interface DataFilter<T> {
    List<T> filter(List<T> all, String query);
}