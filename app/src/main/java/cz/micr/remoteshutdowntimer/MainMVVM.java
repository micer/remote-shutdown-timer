package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableField;

public interface MainMVVM {

    interface View {
        void showError(CharSequence error);
    }

    interface Model {

    }

    interface ViewModel {
        ObservableField<String> getDeviceName();

        ObservableField<String> getDeviceIpAddress();
    }

}
