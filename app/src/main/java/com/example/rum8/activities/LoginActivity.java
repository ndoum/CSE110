package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.LoginController;
import com.example.rum8.listeners.LoginControllerListener;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Class for logging in the user to the application. It contains checking user input and
 * authentication in the fire base.
 */
public class LoginActivity extends AppCompatActivity implements LoginControllerListener {

    // Initialize class variable
    private LoginController controller;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initController();
    }

    @Override
    public void goToPasswordRecovery() {
        final Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToRegistration() {
        final Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * Finish this activity because it should not be the parent activity of {@link MainActivity}.
     */
    @Override
    public void goToMainPage() {
        final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Finish this activity because it should not be the parent activity of {@link PreferencesActivity}.
     */
    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(LoginActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        final Button button_goToPasswordRecovery = findViewById(R.id.button_password_recovery);
        button_goToPasswordRecovery.setOnClickListener(v -> controller.onGoToPasswordRecoverClicked());

        final Button button_goToRegistration = findViewById(R.id.button_register);
        button_goToRegistration.setOnClickListener(v -> controller.onGoToRegistrationButtonClicked());

        emailField = findViewById(R.id.user_email);
        passwordField = findViewById(R.id.user_password);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(v -> {
            final String email = emailField.getText().toString();
            final String pw = passwordField.getText().toString();
            controller.onSubmit(email, pw);
        });
    }

    private void initController() {
        controller = new LoginController(this);
    }

}