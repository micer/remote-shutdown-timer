package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextWatcher;

import cz.micr.remoteshutdowntimer.callback.DeviceConnectionCallback;
import cz.micr.remoteshutdowntimer.model.HostInfo;

public interface MainMVVM {

    interface Model {
        void connectToDevice(HostInfo hostInfo, DeviceConnectionCallback callback);
    }

    interface View extends android.view.View.OnFocusChangeListener,
            DeviceConnectionCallback {
        void showError(CharSequence error);

        void showLoading();

        void hideLoading();
    }

    interface ViewModel {
        ObservableBoolean showLoading();

        ObservableField<String> getDeviceName();

        ObservableField<String> getDeviceIpAddress();

        ObservableBoolean getConnectButtonEnabled();

        /**
         * Fields that are set only once in onCreate() and are never changed don't need
         * to be observable fields.
         */
        TextWatcher getIpAddressTextWatcher();

        void setIpAddressTextWatcher(TextWatcher textWatcher);

        TextWatcher getPasswordTextWatcher();

        void setPasswordTextWatcher(TextWatcher textWatcher);


        /**
         * Actions.
         */
        void connectToDevice(HostInfo hostInfo, DeviceConnectionCallback callback);
    }

}
