package com.alex.miet.mobile;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger application module.
 * Provides {@link Context}
 */
@Module
/*package*/ final class ApplicationModule {
    private final Context context;

    /*package*/ ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    /*package*/ Context provideContext() {
        return context;
    }
}
