package cz.micr.remoteshutdowntimer.viewmodel;

import android.databinding.ObservableField;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;

public class MainViewModel implements MainMVVM.ViewModel {

    @Inject
    public MainViewModel() {
    }

    private ObservableField<String> deviceName = new ObservableField<>("");
    private ObservableField<String> deviceIpAddress = new ObservableField<>("");

    public ObservableField<String> getDeviceName() {
        return deviceName;
    }

    public ObservableField<String> getDeviceIpAddress() {
        return deviceIpAddress;
    }
}
