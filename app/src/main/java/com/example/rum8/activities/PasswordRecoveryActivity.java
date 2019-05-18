package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.PasswordRecoveryController;
import com.example.rum8.listeners.PasswordRecoveryControllerListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryControllerListener {

    private PasswordRecoveryController controller;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private Button button_resetPassword;
    private Button button_goBackToLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        initViews();
        initController();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        emailField = (TextInputEditText) findViewById(R.id.user_email);
        passwordField = (TextInputEditText) findViewById(R.id.user_password);
        button_resetPassword = (Button) findViewById(R.id.button_reset_password);
        button_goBackToLogin = (Button) findViewById(R.id.button_go_back_to_login);
        button_goBackToLogin.setOnClickListener(v -> controller.onGoBackToLoginButtonClicked());
    }

    private void initController() {
        controller = new PasswordRecoveryController(this);
    }

    /**
     * Finish this activity because {@link LoginActivity} is the parent activity of this activity.
     */
    @Override
    public void goBackToLogin() {
        finish();
    }

}
