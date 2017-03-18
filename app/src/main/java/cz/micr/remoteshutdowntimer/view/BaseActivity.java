package cz.micr.remoteshutdowntimer.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Common base activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    protected void hideSoftwareKeyboard() {
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
