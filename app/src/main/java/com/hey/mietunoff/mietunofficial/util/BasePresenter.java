package com.hey.mietunoff.mietunofficial.util;

/**
 * Base interface for presenters
 *
 * @param <V> view type
 */
public interface BasePresenter<V> {
    /**
     * Called when view attached to this presenter
     *
     * @param view to attach
     */
    void onViewAttached(V view);

    /**
     * Called when detached from this presenter
     */
    void onViewDetached();
}
