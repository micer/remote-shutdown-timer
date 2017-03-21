package cz.micr.remoteshutdowntimer.injection.modules;

import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.viewmodel.AppViewModelFactory;
import cz.micr.remoteshutdowntimer.viewmodel.ViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(AppViewModelFactory appViewModelFactory) {
        return appViewModelFactory;
    }
}
