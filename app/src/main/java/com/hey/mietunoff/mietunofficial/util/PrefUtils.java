package com.hey.mietunoff.mietunofficial.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Shared preference util
 */
public class PrefUtils {
    /**
     * Called to save supplied value in shared preferences against given key.
     *
     * @param context Context of caller activity
     * @param key     Key of value to save against
     * @param value   Value to save
     */
    public static void saveToPrefs(Context context, String key, String value) {
        Preconditions.checkNotNull(key);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Called to retrieve required value from shared preferences, identified by given key.
     * Default value will be returned of no value found or error occurred.
     *
     * @param context      Context of caller activity
     * @param key          Key to find value against
     * @param defaultValue Value to return if no data found against given key
     * @return Return the value found against given key, default if not found or any error occurs
     */
    public static String getFromPrefs(Context context, String key, String defaultValue) {
        Preconditions.checkNotNull(key);
        try {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPrefs.getString(key, defaultValue);
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    /**
     * @param context Context of caller activity
     * @param key     Key to delete from SharedPreferences
     */
    public static void removeFromPrefs(Context context, String key) {
        Preconditions.checkNotNull(key);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
