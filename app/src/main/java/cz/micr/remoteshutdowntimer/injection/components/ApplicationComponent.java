package cz.micr.remoteshutdowntimer.injection.components;

import android.content.Context;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import cz.micr.remoteshutdowntimer.injection.modules.AndroidModule;
import cz.micr.remoteshutdowntimer.injection.modules.ApplicationModule;
import cz.micr.remoteshutdowntimer.view.activity.BaseActivity;
import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class
})
public interface ApplicationComponent {

    @ApplicationContext
    Context appContext();

    void inject(BaseActivity baseActivity);
}
