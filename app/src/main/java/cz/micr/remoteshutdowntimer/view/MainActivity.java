package cz.micr.remoteshutdowntimer.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainApplication;
import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.R;
import cz.micr.remoteshutdowntimer.databinding.ActivityMainBinding;
import cz.micr.remoteshutdowntimer.util.AfterChangedTextWatcher;
import cz.micr.remoteshutdowntimer.util.Constant;
import cz.micr.remoteshutdowntimer.viewmodel.MainViewModel;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements MainMVVM.View {

    @Inject
    MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.getInstance().getAppComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(viewModel);

        // setup action bar
        setSupportActionBar(binding.toolbar);

        // setup text watchers
        viewModel.setDeviceIpTextWatcher(new AfterChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Timber.d("DeviceIp text changed.");
                viewModel.getConnectButtonEnabled().set(areInputsValid());
            }
        });
        viewModel.setPasswordTextWatcher(new AfterChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Timber.d("Password text changed.");
                viewModel.getConnectButtonEnabled().set(areInputsValid());
            }
        });

        // setup floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(CharSequence error) {
        Timber.d(error.toString());
        // TODO show error dialog
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private boolean isDeviceIpInputValid() {
        String text = binding.layoutContentMain.deviceIpEditText.getText().toString();
        return text.matches(Constant.Regex.IP_ADDRESS);
    }

    private boolean areInputsValid() {
        return isDeviceIpInputValid() &&
                !binding.layoutContentMain.passwordEditText.getText().toString().isEmpty();
    }
}
