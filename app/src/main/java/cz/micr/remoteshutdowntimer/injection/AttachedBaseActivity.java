package cz.micr.remoteshutdowntimer.injection;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;
import java.net.URISyntaxException;

import cz.micr.remoteshutdowntimer.view.activity.BaseActivity;

public final class AttachedBaseActivity implements AttachedActivity {

    private final WeakReference<BaseActivity> weakActivity;

    public AttachedBaseActivity(BaseActivity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass) {
        BaseActivity activity = weakActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivity(new Intent(activity, activityClass));
        }
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> activityClass, int requestCode) {
        BaseActivity activity = weakActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivityForResult(new Intent(activity, activityClass), requestCode);
        }
    }

    @Override
    public void openUrl(String url) throws URISyntaxException {
        BaseActivity activity = weakActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivity(Intent.parseUri(url, 0));
        }
    }
}
