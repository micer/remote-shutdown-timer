package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextWatcher;

public interface MainMVVM {

    interface Model {
    }

    interface View extends android.view.View.OnFocusChangeListener {
        void showError(CharSequence error);
    }

    interface ViewModel {
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
    }

}
