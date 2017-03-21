package cz.micr.remoteshutdowntimer.injection;

import android.app.Activity;

import java.net.URISyntaxException;

public interface AttachedActivity {

    void startActivity(Class<? extends Activity> activityClass);

    void startActivityForResult(Class<? extends Activity> activityClass, int requestCode);

    void openUrl(String url) throws URISyntaxException;
}
