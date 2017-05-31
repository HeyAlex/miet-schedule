package heyalex.com.miet_schedule.shortcut;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;
import heyalex.com.miet_schedule.util.PrefUtils;

/**
 * Shortcut Manager
 */
public class ShortcutPreferenceImpl implements ShortcutPreference {

    /**
     * Shortcut Preference Key for counter
     */
    private static final String SHORTCUT_PREF = "shortcut_counter";

    @NonNull
    private Context context;

    @Override
    public void addNewDynamicShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            deleteDynamicShortcut(groupName);
            Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context,
                    ScheduleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            int dynamicShortcutCounter = Integer.valueOf(PrefUtils.getFromPrefs(context,
                    SHORTCUT_PREF, "0"));

            if (dynamicShortcutCounter == 4) {
                dynamicShortcutCounter = 0;
                PrefUtils.saveToPrefs(context, SHORTCUT_PREF, String
                        .valueOf(dynamicShortcutCounter));
            }


            ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, groupName)
                    .setShortLabel(groupName)
                    .setLongLabel(groupName)
                    .setRank(dynamicShortcutCounter)
                    .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                    .setIntent(intent.putExtra("group", groupName))
                    .build();
            shortcutManager.addDynamicShortcuts(Collections.singletonList(scheduleShortcut));

            dynamicShortcutCounter++;
            PrefUtils.saveToPrefs(context, SHORTCUT_PREF, String
                    .valueOf(dynamicShortcutCounter));
        }
    }

    @Override
    public void addStaticShortcut(String groupName) {
        Intent shortcutIntent = new Intent(context, ScheduleActivity.class);
        shortcutIntent.putExtra("group", groupName);
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, groupName);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, R.drawable.calendar));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(addIntent);
    }

    @Override
    public void deleteDynamicShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            List<String> shortcutArray = new ArrayList<>();
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            for (ShortcutInfo shortcutInfo : shortcutManager.getDynamicShortcuts()) {
                if (shortcutInfo.getShortLabel().equals(groupName)) {
                    shortcutArray.add(shortcutInfo.getId());
                    shortcutManager.removeDynamicShortcuts(shortcutArray);
                }

            }

            int dynamicShortcutCounter = Integer.valueOf(PrefUtils.getFromPrefs(context,
                    SHORTCUT_PREF, "0"));
            dynamicShortcutCounter--;
            PrefUtils.saveToPrefs(context, SHORTCUT_PREF, String
                    .valueOf(dynamicShortcutCounter));
        }
    }

    @Override
    public void deleteStaticShortcut(String groupName) {
        Intent shortcutIntent = new Intent(context, ScheduleActivity.class);
        shortcutIntent.putExtra("group", groupName);
        Intent removeIntent = new Intent();
        removeIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        removeIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, groupName);
        removeIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        context.sendBroadcast(removeIntent);
    }
}
