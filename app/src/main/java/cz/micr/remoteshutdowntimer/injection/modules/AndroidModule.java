package cz.micr.remoteshutdowntimer.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.MainApplication;
import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AndroidModule {
    private final MainApplication application;

    public AndroidModule(MainApplication application) {
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ApplicationContext @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    SharedPreferences provideDefaultSharedPreferences(@ApplicationContext final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
