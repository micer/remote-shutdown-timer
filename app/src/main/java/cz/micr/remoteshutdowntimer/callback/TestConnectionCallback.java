package cz.micr.remoteshutdowntimer.callback;

public interface TestConnectionCallback {

    void onConnectionSuccess();

    void onConnectionError(String error);
}
