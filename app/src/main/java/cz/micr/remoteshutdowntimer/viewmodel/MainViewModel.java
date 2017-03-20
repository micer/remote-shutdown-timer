package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextWatcher;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.callback.DeviceConnectionCallback;
import cz.micr.remoteshutdowntimer.model.HostInfo;
import cz.micr.remoteshutdowntimer.model.MainModel;

public class MainViewModel implements MainMVVM.ViewModel {

    @Inject
    MainModel model;

    private ObservableBoolean showLoading = new ObservableBoolean(false);
    private ObservableField<String> deviceName = new ObservableField<>("");
    private ObservableField<String> deviceIpAddress = new ObservableField<>("");
    private ObservableBoolean connectButtonEnabled = new ObservableBoolean(false);
    private TextWatcher ipAddressTextWatcher;
    private TextWatcher passwordTextWatcher;

    @Inject
    public MainViewModel() {
    }

    @Override
    public ObservableBoolean showLoading() {
        return showLoading;
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

    @Override
    public void connectToDevice(HostInfo hostInfo, DeviceConnectionCallback callback) {
        new Thread(() -> {
            model.connectToDevice(hostInfo, callback);
        }).start();
    }
}
