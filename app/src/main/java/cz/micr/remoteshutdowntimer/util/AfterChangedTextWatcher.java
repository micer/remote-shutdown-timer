package cz.micr.remoteshutdowntimer.util;

import android.text.TextWatcher;

public abstract class AfterChangedTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // do nothing
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // do nothing
    }
}
