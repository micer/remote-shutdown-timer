package cz.micr.remoteshutdowntimer;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.injection.components.ApplicationComponent;
import cz.micr.remoteshutdowntimer.injection.modules.AndroidModule;
import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        MockModule.class
})
public interface InstrTestComponent extends ApplicationComponent {

    void inject(BaseInstrTest baseInstrTest);
}
