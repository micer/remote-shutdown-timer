package cz.micr.remoteshutdowntimer;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.injection.components.ApplicationComponent;
import cz.micr.remoteshutdowntimer.injection.components.DaggerApplicationComponent;
import cz.micr.remoteshutdowntimer.injection.modules.AndroidModule;
import timber.log.Timber;


public class MainApplication extends Application {

    private static MainApplication instance;

    @Inject
    SharedPreferences sharedPreferences;

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Dagger dependency injection
        appComponent = DaggerApplicationComponent.builder().
                androidModule(new AndroidModule(this))
                .build();
        appComponent.inject(this);

        // initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority >= Log.INFO) {
                // TODO add crash reporting on production
            }
        }
    }
}
