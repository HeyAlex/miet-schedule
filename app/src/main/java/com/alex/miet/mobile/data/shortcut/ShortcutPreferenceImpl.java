package com.alex.miet.mobile.data.shortcut;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import com.alex.miet.mobile.R;
import com.alex.miet.mobile.schedule.ScheduleActivity;
import com.alex.miet.mobile.schedule_widget.ScheduleAppWidget;
import com.alex.miet.mobile.schedule_widget.ScheduleUpdateService;
import com.alex.miet.mobile.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Shortcut Manager
 */
public class ShortcutPreferenceImpl implements ShortcutPreference {

    private static final String LAST_SAVED_ID = "last_saved_id";

    @NonNull
    private Context context;

    /*package*/
    public ShortcutPreferenceImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void addNewDynamicShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            String id = PrefUtils.getFromPrefs(context, LAST_SAVED_ID, "0");
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();
            int scSize = dynamicShortcuts.size();
            Timber.i("Dynamic shortcut list has %d elements.", scSize);

            Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context,
                    ScheduleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            int nextId = Integer.parseInt(id) + 1;
            if (nextId > 4) {
                nextId = 0;
            }
            String newId = String.valueOf(nextId);

            for (ShortcutInfo shortcut : dynamicShortcuts) {
                if (shortcut.getId().equals(newId)) {
                    dynamicShortcuts.remove(shortcut);
                }
            }

            PrefUtils.saveToPrefs(context, LAST_SAVED_ID, newId);
            ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, newId)
                    .setShortLabel(groupName)
                    .setLongLabel(groupName)
                    .setIcon(Icon.createWithResource(context, R.drawable.calendar_clock_dynamic_icon))
                    .setIntent(intent.putExtra("group", groupName))
                    .build();

            //add shortcut on the top of dynamic shortcut list
            dynamicShortcuts.add(scheduleShortcut);

            shortcutManager.setDynamicShortcuts(dynamicShortcuts);
        }
    }

    @Override
    public void addStaticShortcut(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager mShortcutManager =
                    context.getSystemService(ShortcutManager.class);

            if (mShortcutManager.isRequestPinShortcutSupported()) {

                Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context,
                        ScheduleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);


                ShortcutInfo scheduleShortcut = new ShortcutInfo.Builder(context, groupName)
                        .setShortLabel(groupName)
                        .setLongLabel(groupName)
                        .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                        .setIntent(intent.putExtra("group", groupName))
                        .build();

                Intent pinnedShortcutCallbackIntent =
                        mShortcutManager.createShortcutResultIntent(scheduleShortcut);

                // Configure the intent so that your app's broadcast receiver gets
                // the callback successfully.
                PendingIntent successCallback = PendingIntent.getBroadcast(context, 0,
                        pinnedShortcutCallbackIntent, 0);

                mShortcutManager.requestPinShortcut(scheduleShortcut,
                        successCallback.getIntentSender());
            }
        } else {
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

    @Override
    public void requestWidgetPin(String groupName) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AppWidgetManager mAppWidgetManager =
                    context.getSystemService(AppWidgetManager.class);
            ComponentName myProvider =
                    new ComponentName(context, ScheduleAppWidget.class);

            if (mAppWidgetManager.isRequestPinAppWidgetSupported()) {
                mAppWidgetManager.requestPinAppWidget(myProvider, null,
                        ScheduleUpdateService.getScheduleConfigurationPendingIntentForPinning(context, groupName));
            }
        }
    }
}
