package heyalex.com.miet_schedule.shortcut;

import android.content.Context;

/**
 * Created by mac on 29.05.17.
 */

public interface ShortcutPreference {

    void addNewDynamicShortcut(String groupName);

    void addStaticShortcut(String groupName);

    void deleteDynamicShortcut(String groupName);

    void deleteStaticShortcut(String groupName);


}
