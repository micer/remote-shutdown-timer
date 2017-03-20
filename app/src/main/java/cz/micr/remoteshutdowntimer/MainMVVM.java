package cz.micr.remoteshutdowntimer;

import android.databinding.ObservableBoolean;
import android.text.TextWatcher;

public interface MainMVVM {

    interface Model {
    }

    interface View extends android.view.View.OnFocusChangeListener {
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
    }

}
