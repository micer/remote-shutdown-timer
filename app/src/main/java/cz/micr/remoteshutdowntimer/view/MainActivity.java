package cz.micr.remoteshutdowntimer.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
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
        binding.setActivity(this);

        // setup action bar
        setSupportActionBar(binding.toolbar);

        // setup floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        // setup text watchers
        TextWatcher inputTextWatcher = new AfterChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.getConnectButtonEnabled().set(areInputsValid());
            }
        };
        viewModel.setIpAddressTextWatcher(inputTextWatcher);
        viewModel.setPasswordTextWatcher(inputTextWatcher);

        // setup click listeners
        binding.layoutContentMain.btnConnect.setOnClickListener(view -> {
            showLoading();
            // TODO next action
            CountDownTimer timer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    hideLoading();
                }
            };
            timer.start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // setup connect button
        viewModel.getConnectButtonEnabled().set(areInputsValid());
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

    @Override
    public void showLoading() {
        viewModel.showLoading().set(true);
        binding.progressBarOverlay.requestFocus();
        hideSoftwareKeyboard();
    }

    @Override
    public void hideLoading() {
        viewModel.showLoading().set(false);
    }

    private boolean isIpAddressValid() {
        String text = binding.layoutContentMain.inputIpAddress.getText().toString();
        return text.matches(Constant.Regex.IP_ADDRESS);
    }

    private boolean isPasswordValid() {
        return !binding.layoutContentMain.inputPassword.getText().toString().isEmpty();
    }

    private boolean areInputsValid() {
        return isIpAddressValid() && isPasswordValid();
    }

    @Override
    public void onFocusChange(View view, boolean focused) {
        if (!focused && !isIpAddressValid()) {
            binding.layoutContentMain.inputIpAddress.setError(getString(R.string.error_invalid_format));
        }
    }
}
