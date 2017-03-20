package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableBoolean;
import android.text.TextWatcher;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.callback.TestConnectionCallback;
import cz.micr.remoteshutdowntimer.model.ConnectionInfo;
import cz.micr.remoteshutdowntimer.model.MainModel;

public class MainViewModel implements MainMVVM.ViewModel {

    @Inject
    MainModel model;

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

    @Override
    public void testConnection(ConnectionInfo connectionInfo, TestConnectionCallback callback) {
        new Thread(() -> {
            model.testConnection(connectionInfo, callback);
        }).start();
    }
}
