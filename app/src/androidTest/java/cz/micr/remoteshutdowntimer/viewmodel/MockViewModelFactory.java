package cz.micr.remoteshutdowntimer.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;


public class MockViewModelFactory implements ViewModelFactory {


    private final Map<Class<? extends BaseViewModel>, SpyInitializer<? extends BaseViewModel>>
            spyInitializerMap;
    private MainViewModel mainViewModel;

    @Inject
    MockViewModelFactory() {
        spyInitializerMap = new HashMap<>();
    }

    public void clear() {
        spyInitializerMap.clear();
        mainViewModel = null;
    }

    public <ViewModelT extends BaseViewModel> void registerSpyInitializer(
            Class<ViewModelT> viewModelClass, SpyInitializer<ViewModelT> spyInitializer) {
        spyInitializerMap.put(viewModelClass, spyInitializer);
    }

    @SuppressWarnings("unchecked")
    private <ViewModelT extends BaseViewModel> void setupSpy(Class<ViewModelT> viewModelClass,
                                                             ViewModelT viewModel) {
        SpyInitializer spyInitializer = spyInitializerMap.get(viewModelClass);
        if (spyInitializer != null) {
            spyInitializer.setupSpy(viewModel);
        }
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    @NonNull
    @Override
    public MainViewModel createMainViewModel(@NonNull ActivityComponent activityComponent, @Nullable BaseViewModel.State savedViewModelState) {
//        mainViewModel = spy(new MainViewModel(activityComponent, savedViewModelState));
//        doNothingOnLifeCycle(mainViewModel);
//        setupSpy(MainViewModel.class, mainViewModel);
//        return mainViewModel;
        return null;
    }

    private void doNothingOnLifeCycle(BaseViewModel viewModel) {
//        doNothing().when(viewModel).onStart();
//        doNothing().when(viewModel).onStop();
    }

    public interface SpyInitializer<ViewModelT extends BaseViewModel> {
        void setupSpy(ViewModelT viewModel);
    }
}
