package cz.micr.remoteshutdowntimer.model;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.callback.DeviceConnectionCallback;
import cz.micr.remoteshutdowntimer.util.SshConnectionManager;


public class MainModel implements MainMVVM.Model {


    @Inject
    public MainModel() {
    }

    @Override
    public void connectToDevice(HostInfo hostInfo, DeviceConnectionCallback callback) {

        SshConnectionManager.executeShutdownNow();
        SshConnectionManager.close();
        callback.onConnectionSuccess();

    }
}
