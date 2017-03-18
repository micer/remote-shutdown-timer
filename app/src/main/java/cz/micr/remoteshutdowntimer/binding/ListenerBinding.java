package cz.micr.remoteshutdowntimer.binding;

import android.databinding.BindingAdapter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class ListenerBinding {

    @BindingAdapter({"onTextChange"})
    public static void onTextChange(View view, TextWatcher textWatcher) {
        if (view instanceof EditText && textWatcher != null) {
            ((EditText) view).addTextChangedListener(textWatcher);
        }
    }

    @BindingAdapter({"onFocusChange"})
    public static void onFocusChange(View view, View.OnFocusChangeListener onFocusChangeListener) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnFocusChangeListener(onFocusChangeListener);
    }
}
