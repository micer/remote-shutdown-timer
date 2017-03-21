package cz.micr.remoteshutdowntimer.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;

public interface ViewModelFactory {
    @NonNull
    MainViewModel createMainViewModel(@NonNull ActivityComponent activityComponent,
                                      @Nullable BaseViewModel.State savedViewModelState);
}
