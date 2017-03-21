package cz.micr.remoteshutdowntimer.injection.components;

import cz.micr.remoteshutdowntimer.injection.PerActivity;
import cz.micr.remoteshutdowntimer.injection.modules.ActivityModule;
import cz.micr.remoteshutdowntimer.viewmodel.BaseViewModel;
import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(BaseViewModel baseViewModel);
}
