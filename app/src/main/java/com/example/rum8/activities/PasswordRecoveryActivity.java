package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.PasswordRecoveryController;
import com.example.rum8.listeners.PasswordRecoveryControllerListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryControllerListener {

    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private Button button_resetPassword;
    private Button button_goBackToLogin;
    private PasswordRecoveryController controller;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        // views
        emailField = (TextInputEditText) findViewById(R.id.user_email);
        passwordField = (TextInputEditText) findViewById(R.id.user_password);
        button_resetPassword = (Button) findViewById(R.id.button_reset_password);
        button_goBackToLogin = (Button) findViewById(R.id.button_go_back_to_login);

        initViews();
        initController();



        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

    }

    private void initViews() {
        button_goBackToLogin.setOnClickListener(v -> controller.onGoBackToLoginButtonClicked());
    }

    private void initController() {
        controller = new PasswordRecoveryController(this);
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void goBackToLogin() {
        //TODO go back to the login page
        final Intent intent = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    // [END on_start_check_user]

}
