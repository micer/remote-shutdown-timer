package cz.micr.remoteshutdowntimer;

import android.content.Context;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import cz.micr.remoteshutdowntimer.injection.AttachedActivity;
import dagger.Module;
import dagger.Provides;

@Module
public final class TestModule {

    @Provides
    @Singleton
    @ApplicationContext
    Context provideAppContext() {
//        return mock(Context.class);
        return null;
    }

    @Provides
    @Singleton
    AttachedActivity provideAttachedActivity() {
//        return mock(AttachedActivity.class);
        return null;
    }
}
