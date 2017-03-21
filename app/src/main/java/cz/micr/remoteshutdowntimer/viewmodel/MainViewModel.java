package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextWatcher;

import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;

public class MainViewModel extends BaseViewModel implements MainMVVM.ViewModel {

    private ObservableBoolean showLoading = new ObservableBoolean(false);
    private ObservableBoolean connectButtonEnabled = new ObservableBoolean(false);
    private TextWatcher inputsTextWatcher;

    MainViewModel(@NonNull ActivityComponent activityComponent,
                  @Nullable State savedInstanceState) {
        super(activityComponent, savedInstanceState);
    }

    @Override
    public ObservableBoolean showLoading() {
        return showLoading;
    }

    @Override
    public ObservableBoolean getSaveButtonEnabled() {
        return connectButtonEnabled;
    }

    @Override
    public TextWatcher getInputsTextWatcher() {
        return inputsTextWatcher;
    }

    @Override
    public void setInputsTextWatcher(TextWatcher textWatcher) {
        inputsTextWatcher = textWatcher;
    }

}
