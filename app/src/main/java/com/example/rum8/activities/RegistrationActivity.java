package com.example.rum8.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rum8.R;
import com.example.rum8.controllers.RegistrationController;
import com.example.rum8.listeners.RegistrationControllerListener;

public class RegistrationActivity extends AppCompatActivity
        implements RegistrationControllerListener {

    private RegistrationController controller;
    private TextInputEditText editText_email, editText_password;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        initController();
    }

    @Override
    protected void onStop() {
        super.onStop();
        controller.destroy();
    }

    @Override
    public void onUserRegistered() {
        final Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(RegistrationActivity.this, message, toastLength).show();
    }

    @Override
    public void goBackToLogin() {
        final Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void initViews() {
        editText_email = (TextInputEditText) findViewById(R.id.input_email);
        editText_password = (TextInputEditText) findViewById(R.id.input_password);
        final Button button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = editText_email.getText().toString();
                final String password = editText_password.getText().toString();
                controller.onSubmit(email, password);
            }
        });

        final Button button_cancel = (Button) findViewById(R.id.button_registration_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onGoBackToLoginButtonClicked();
            }
        });
    }

    private void initController() {
        controller = new RegistrationController(this, this);
    }

}
