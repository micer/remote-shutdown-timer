package cz.micr.remoteshutdowntimer.injection.modules;

import cz.micr.remoteshutdowntimer.injection.AttachedActivity;
import cz.micr.remoteshutdowntimer.injection.AttachedBaseActivity;
import cz.micr.remoteshutdowntimer.injection.PerActivity;
import cz.micr.remoteshutdowntimer.view.activity.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AttachedActivity provideAttachedActivity() {
        return new AttachedBaseActivity(activity);
    }
}
