package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableBoolean;
import android.text.TextWatcher;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;

public class MainViewModel implements MainMVVM.ViewModel {

    private ObservableBoolean showLoading = new ObservableBoolean(false);
    private ObservableBoolean connectButtonEnabled = new ObservableBoolean(false);
    private TextWatcher inputsTextWatcher;

    @Inject
    public MainViewModel() {
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
