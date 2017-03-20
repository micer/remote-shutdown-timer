package cz.micr.remoteshutdowntimer.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.MainApplication;
import cz.micr.remoteshutdowntimer.MainMVVM;
import cz.micr.remoteshutdowntimer.R;
import cz.micr.remoteshutdowntimer.databinding.ActivityMainBinding;
import cz.micr.remoteshutdowntimer.model.ConnectionInfo;
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
        viewModel.setInputsTextWatcher(new AfterChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.getSaveButtonEnabled().set(validateInputs());
            }
        });

        // FIXME remove dummy data
        ConnectionInfo connectionInfo = new ConnectionInfo(
                "192.168.1.38",
                22,
                "micer",
                "123");
        binding.layoutContentMain.inputHost.setText(connectionInfo.getHost());
        binding.layoutContentMain.inputPort.setText(String.valueOf(connectionInfo.getPort()));
        binding.layoutContentMain.inputUsername.setText(connectionInfo.getUsername());
        binding.layoutContentMain.inputPassword.setText(connectionInfo.getPassword());

        // setup click listeners
        binding.layoutContentMain.btnTestAndSave.setOnClickListener(view -> {
            showLoading();
            viewModel.testConnection(connectionInfo, this);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // setup save button
        viewModel.getSaveButtonEnabled().set(validateInputs());
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

    private boolean validateHost() {
        EditText inputHost = binding.layoutContentMain.inputHost;
        String host = inputHost.getText().toString();
        if (host.isEmpty()) {
            inputHost.setError(getString(R.string.error_required_value));
            return false;
        } else if (!host.matches(Constant.Regex.IP_ADDRESS)) { // TODO validate text format
            inputHost.setError(getString(R.string.error_invalid_format));
            return false;
        }
        return true;
    }

    private boolean validatePort() {
        EditText inputPort = binding.layoutContentMain.inputPort;
        if (inputPort.getText().toString().isEmpty()) {
            inputPort.setError(getString(R.string.error_required_value));
            return false;
        }
        return true;
    }

    private boolean validateUsername() {
        EditText inputUsername = binding.layoutContentMain.inputUsername;
        if (inputUsername.getText().toString().isEmpty()) {
            inputUsername.setError(getString(R.string.error_required_value));
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        EditText inputPassword = binding.layoutContentMain.inputPassword;
        if (inputPassword.getText().toString().isEmpty()) {
            inputPassword.setError(getString(R.string.error_required_value));
            return false;
        }
        return true;
    }

    private boolean validateInputs() {
        return validateHost()
                && validatePort()
                && validateUsername()
                && validatePassword();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        Timber.d("focus changed to %1$b on %2$s", hasFocus, view.getResources().getResourceName(view.getId()));
        if (!hasFocus) {
            validateInputs();
        }
    }

    @Override
    public void onConnectionSuccess() {
        runOnUiThread(() -> {
            hideLoading();
            Timber.d("Connection successful");
            // TODO save connection info and continue
        });
    }

    @Override
    public void onConnectionError(String error) {
        runOnUiThread(() -> {
            hideLoading();
            showError("Connection error");
        });
    }
}
