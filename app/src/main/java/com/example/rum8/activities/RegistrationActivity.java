package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RegistrationController;
import com.example.rum8.listeners.RegistrationControllerListener;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity
        implements RegistrationControllerListener {

    private RegistrationController controller;
    private TextInputEditText editText_email, editText_password,editText_passwordConfirm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        initController();
    }

    /**
     * Finish this activity because {@link LoginActivity} is the parent activity of this activity.
     */
    @Override
    public void goToLogin() {
        finish();
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(RegistrationActivity.this, message, toastLength).show();
    }

    private void initViews() {
        editText_email = (TextInputEditText) findViewById(R.id.input_email);
        editText_password = (TextInputEditText) findViewById(R.id.input_password);
        editText_passwordConfirm = (TextInputEditText) findViewById(R.id.input_password_confirm);
        final Button button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(v -> {
            final String email = editText_email.getText().toString();
            final String password = editText_password.getText().toString();
            final String passwordConfirm = editText_passwordConfirm.getText().toString();
            controller.onSubmit(email, password, passwordConfirm);
        });

        final Button button_cancel = (Button) findViewById(R.id.button_registration_cancel);
        button_cancel.setOnClickListener(v -> controller.onGoBackToLoginButtonClicked());
    }

    private void initController() {
        controller = new RegistrationController(this, this);
    }

}
