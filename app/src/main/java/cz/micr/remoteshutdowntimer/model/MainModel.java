package cz.micr.remoteshutdowntimer.model;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.callback.TestConnectionCallback;
import cz.micr.remoteshutdowntimer.util.SshConnectionManager;


public class MainModel implements MainMVVM.Model {


    @Inject
    public MainModel() {
    }

    @Override
    public void testConnection(ConnectionInfo connectionInfo, TestConnectionCallback callback) {

        // FIXME test connection status

        SshConnectionManager.executeShutdown(connectionInfo, 0);
        SshConnectionManager.close();
        callback.onConnectionSuccess();

    }
}
