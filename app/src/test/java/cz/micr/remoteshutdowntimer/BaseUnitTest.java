package cz.micr.remoteshutdowntimer;

import android.content.Context;
import android.support.annotation.CallSuper;

import junit.framework.Assert;

import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import cz.micr.remoteshutdowntimer.injection.AttachedActivity;

public class BaseUnitTest extends Assert {

//    protected final UnitTestComponent unitTestComponent;

    @Inject
    @ApplicationContext
    protected Context appContext;

    @Inject
    protected AttachedActivity attachedActivity;

    public BaseUnitTest() {
//        unitTestComponent = DaggerUnitTestComponent.builder()
//                .testModule(new TestModule())
//                .build();
//        unitTestComponent.inject(this);
    }

    @CallSuper
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(appContext, attachedActivity);
    }
}
