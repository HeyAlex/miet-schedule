package heyalex.com.miet_schedule.shortcut;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 01.06.17.
 */

@Module
public class ShortcutPreferenceModule  {

    @Provides
    @Singleton
    public ShortcutPreference provideLearningPreferences(Context context) {
        return new ShortcutPreferenceImpl(context);
    }

}
