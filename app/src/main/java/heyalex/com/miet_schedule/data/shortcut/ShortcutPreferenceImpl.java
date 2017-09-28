package heyalex.com.miet_schedule.data.shortcut;

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
import timber.log.Timber;

/**
 * Shortcut Manager
 */
public class ShortcutPreferenceImpl implements ShortcutPreference {

    @NonNull
    private Context context;

    /*package*/
    public ShortcutPreferenceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void addNewDynamicShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            List<ShortcutInfo> scInfo = shortcutManager.getDynamicShortcuts();
            int scSize = scInfo.size();
            Timber.i("Dynamic shortcut list has %d elements.", scSize);
            //if size of dynamic shortcut is 4, just delete last element
            if (scSize >= 4) {
                scInfo.remove(3);
            }

            Collections.sort(scInfo, new ShortcutComparator());

            Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context,
                    ScheduleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);


            ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, groupName)
                    .setShortLabel(groupName)
                    .setLongLabel(groupName)
                    .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                    .setIntent(intent.putExtra("group", groupName))
                    .build();

            //add shortcut on the top of dynamic shortcut list
            scInfo.add(scheduleShortcut);

            shortcutManager.setDynamicShortcuts(scInfo);
        }
    }

    @Override
    public void addStaticShortcut(String groupName) {
        Intent shortcutIntent = new Intent(context, ScheduleActivity.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shortcutIntent.putExtra("group", groupName);
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, groupName);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher));
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
        }
    }

    @Override
    public void deleteStaticShortcut(String groupName) {
        Intent shortcutIntent = new Intent(context, ScheduleActivity.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shortcutIntent.putExtra("group", groupName);
        Intent removeIntent = new Intent();
        removeIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        removeIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, groupName);
        removeIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        context.sendBroadcast(removeIntent);
    }
}
