package cz.micr.remoteshutdowntimer.callback;

public interface DeviceConnectionCallback {

    void onConnectionSuccess();

    void onConnectionError(String error);
}
