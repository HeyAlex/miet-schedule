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

/**
 * Created by mac on 29.05.17.
 */

public class ShortcutPreferenceImpl implements ShortcutPreference {

    @NonNull
    private Context context;

    @Override
    public void addNewDynamicShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            deleteDynamicShortcut(groupName);
            Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context,
                    ScheduleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);


            if (shortcutManager.getDynamicShortcuts().size() != 3) {
                ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, groupName)
                        .setShortLabel(groupName)
                        .setLongLabel(groupName)
                        .setRank(shortcutManager.getDynamicShortcuts().size() + 1)
                        .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                        .setIntent(intent.putExtra("group", groupName))
                        .build();
                shortcutManager.addDynamicShortcuts(Collections.singletonList(scheduleShortcut));
            }else {
                ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, groupName)
                        .setShortLabel(groupName)
                        .setLongLabel(groupName)
                        .setRank(1)
                        .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                        .setIntent(intent.putExtra("group", groupName))
                        .build();
                shortcutManager.addDynamicShortcuts(Collections.singletonList(scheduleShortcut));
            }
        }
    }

    @Override
    public void addStaticShortcut(String groupName) {

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
    public void deleteStaticShortcut(String groupname) {

    }
}
