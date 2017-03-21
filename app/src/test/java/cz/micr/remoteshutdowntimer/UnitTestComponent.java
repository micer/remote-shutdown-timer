package cz.micr.remoteshutdowntimer;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;
import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface UnitTestComponent extends ActivityComponent {

//    void inject(BaseUnitTest baseUnitTest);
}
