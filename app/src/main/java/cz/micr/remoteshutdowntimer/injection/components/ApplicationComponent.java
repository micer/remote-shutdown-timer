package cz.micr.remoteshutdowntimer.injection.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.MainApplication;
import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import cz.micr.remoteshutdowntimer.injection.modules.AndroidModule;
import cz.micr.remoteshutdowntimer.view.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = AndroidModule.class)
public interface ApplicationComponent {
    void inject(MainApplication application);

    void inject(MainActivity mainActivity);

    @ApplicationContext
    Context context();

    Application application();
}
