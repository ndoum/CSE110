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

public class LoginActivity extends AppCompatActivity implements LoginControllerListener {

    //member variables for text field
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private Button buttonLogin;

    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initController();
    }

    @Override
    public void goToPasswordRecover() {
        final Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToRegistration() {
        final Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMainPage() {
        final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(LoginActivity.this, message, toastLength).show();
    }

    private void initViews() {

        // override onclick goToPasswordRecovery button
        final Button button_goToPasswordRecovery = findViewById(R.id.button_password_recovery);
        button_goToPasswordRecovery.setOnClickListener(v -> controller.onGoToPasswordRecoverClicked());

        // override onClick goToRegistration button
        final Button button_goToRegistration = findViewById(R.id.button_register);
        button_goToRegistration.setOnClickListener(v -> controller.onGoToRegistrationButtonClicked());

        // views
        emailField = (TextInputEditText) findViewById(R.id.user_email);
        passwordField = (TextInputEditText) findViewById(R.id.user_password);
        buttonLogin = (Button) findViewById(R.id.button_login);


        // override onClick login button
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