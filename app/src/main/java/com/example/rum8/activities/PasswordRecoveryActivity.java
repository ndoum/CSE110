package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.PasswordRecoveryController;
import com.example.rum8.listeners.PasswordRecoveryControllerListener;
import com.google.android.material.textfield.TextInputEditText;


public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryControllerListener {

    private PasswordRecoveryController controller;
    private TextInputEditText emailField;
    private Button button_resetPassword;
    private Button button_goBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        initViews();
        initController();
    }

    private void initViews() {
        emailField = findViewById(R.id.user_email);
        button_resetPassword = findViewById(R.id.button_reset_password);

        button_resetPassword.setOnClickListener(v -> {
            final String email = emailField.getText().toString();
            controller.onSubmit(email);
        });
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

    @Override
    public void showToast(final String message) {
        Toast.makeText(PasswordRecoveryActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
