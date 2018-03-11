package com.alex.miet.mobile.data.shortcut;

import android.content.pm.ShortcutInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Comparator;

/**
 * Comparator for Shortcuts.
 */
/*package*/ class ShortcutComparator implements Comparator<ShortcutInfo> {

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @Override
    public int compare(ShortcutInfo data, ShortcutInfo t1) {
        return data.getId().compareTo(t1.getId());
    }
}