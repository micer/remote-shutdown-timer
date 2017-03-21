package cz.micr.remoteshutdowntimer;

import android.app.Instrumentation;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.viewmodel.MockViewModelFactory;

public class BaseInstrTest {

    @Inject
    protected MockViewModelFactory viewModelFactory;

    @CallSuper
    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MainApplication application = (MainApplication) instrumentation
                .getTargetContext().getApplicationContext();
//        InstrTestComponent testComponent = DaggerTestComponent.builder()
//                .appContextModule(new AndroidModule(application))
//                .build();
//        application.setAppComponent(testComponent);
//        testComponent.inject(this);
        viewModelFactory.clear();
    }
}
