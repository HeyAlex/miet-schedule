package com.alex.miet.mobile.daos

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(obj: List<T>)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun update(obj: List<T>)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(obj: T)

    @Delete
    @JvmSuppressWildcards
    fun delete(obj: List<T>)
}