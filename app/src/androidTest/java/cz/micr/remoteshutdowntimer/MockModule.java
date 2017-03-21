package cz.micr.remoteshutdowntimer;


import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.viewmodel.MockViewModelFactory;
import cz.micr.remoteshutdowntimer.viewmodel.ViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class MockModule {

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(MockViewModelFactory mockViewModelFactory) {
        return mockViewModelFactory;
    }
}
