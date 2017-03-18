package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextWatcher;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;

public class MainViewModel implements MainMVVM.ViewModel {

    private ObservableField<String> deviceName = new ObservableField<>("");
    private ObservableField<String> deviceIpAddress = new ObservableField<>("");
    private ObservableBoolean connectButtonEnabled = new ObservableBoolean(false);
    private TextWatcher ipAddressTextWatcher;
    private TextWatcher passwordTextWatcher;
    @Inject
    public MainViewModel() {
    }

    @Override
    public ObservableField<String> getDeviceName() {
        return deviceName;
    }

    @Override
    public ObservableField<String> getDeviceIpAddress() {
        return deviceIpAddress;
    }

    @Override
    public ObservableBoolean getConnectButtonEnabled() {
        return connectButtonEnabled;
    }

    @Override
    public TextWatcher getIpAddressTextWatcher() {
        return ipAddressTextWatcher;
    }

    @Override
    public void setIpAddressTextWatcher(TextWatcher textWatcher) {
        this.ipAddressTextWatcher = textWatcher;
    }

    @Override
    public TextWatcher getPasswordTextWatcher() {
        return passwordTextWatcher;
    }

    @Override
    public void setPasswordTextWatcher(TextWatcher textWatcher) {
        passwordTextWatcher = textWatcher;
    }
}
