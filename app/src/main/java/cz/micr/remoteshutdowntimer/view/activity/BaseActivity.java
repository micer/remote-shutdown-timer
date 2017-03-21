package cz.micr.remoteshutdowntimer.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainApplication;
import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;
import cz.micr.remoteshutdowntimer.injection.components.ApplicationComponent;
import cz.micr.remoteshutdowntimer.injection.components.DaggerActivityComponent;
import cz.micr.remoteshutdowntimer.injection.modules.ActivityModule;
import cz.micr.remoteshutdowntimer.viewmodel.BaseViewModel;
import cz.micr.remoteshutdowntimer.viewmodel.ViewModelFactory;

/**
 * Common base activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String EXTRA_VIEW_MODEL_STATE = "viewModelState";
    @Inject
    protected ViewModelFactory viewModelFactory;
    private ActivityComponent activityComponent;
    private BaseViewModel baseViewModel;
    private InputMethodManager imm;

    protected void inject(ApplicationComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponent appComponent = ((MainApplication) getApplication()).getAppComponent();
        inject(appComponent);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();

        BaseViewModel.State savedViewModelState = null;
        if (savedInstanceState != null) {
            savedViewModelState = savedInstanceState.getParcelable(EXTRA_VIEW_MODEL_STATE);
        }
        baseViewModel = createViewModel(savedViewModelState);

        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    @Nullable
    protected BaseViewModel createViewModel(@Nullable BaseViewModel.State savedViewModelState) {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (baseViewModel != null) {
            baseViewModel.onStart();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (baseViewModel != null) {
            outState.putParcelable(EXTRA_VIEW_MODEL_STATE, baseViewModel.getInstanceState());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (baseViewModel != null) {
            baseViewModel.onStop();
        }
    }

    public final ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected void hideSoftwareKeyboard() {
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
