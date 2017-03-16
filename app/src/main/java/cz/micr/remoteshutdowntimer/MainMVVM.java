package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextWatcher;

public interface MainMVVM {

    interface View {
        void showError(CharSequence error);
    }

    interface Model {

    }

    interface ViewModel {
        ObservableField<String> getDeviceName();

        ObservableField<String> getDeviceIpAddress();

        ObservableBoolean getConnectButtonEnabled();

        /**
         * Fields that are set only once in onCreate() and are never changed don't need
         * to be observable fields.
         */
        TextWatcher getDeviceIpTextWatcher();

        void setDeviceIpTextWatcher(TextWatcher textWatcher);

        TextWatcher getPasswordTextWatcher();

        void setPasswordTextWatcher(TextWatcher textWatcher);
    }

}
