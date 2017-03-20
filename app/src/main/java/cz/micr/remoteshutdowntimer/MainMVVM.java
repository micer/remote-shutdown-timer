package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableBoolean;
import android.text.TextWatcher;

import cz.micr.remoteshutdowntimer.callback.TestConnectionCallback;
import cz.micr.remoteshutdowntimer.model.ConnectionInfo;

public interface MainMVVM {

    interface Model {
        void testConnection(ConnectionInfo connectionInfo, TestConnectionCallback callback);
    }

    interface View extends android.view.View.OnFocusChangeListener,
            TestConnectionCallback {
        void showError(CharSequence error);

        void showLoading();

        void hideLoading();
    }

    interface ViewModel {
        ObservableBoolean showLoading();

        ObservableBoolean getSaveButtonEnabled();

        /**
         * Fields that are set only once in onCreate() and are never changed don't need
         * to be observable fields.
         */
        TextWatcher getInputsTextWatcher();

        void setInputsTextWatcher(TextWatcher textWatcher);


        /**
         * Actions.
         */
        void testConnection(ConnectionInfo connectionInfo, TestConnectionCallback callback);
    }

}
