package com.example.rum8.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rum8.R;
import com.example.rum8.controllers.RegistrationController;
import com.example.rum8.listeners.RegistrationControllerListener;

public class RegistrationActivity extends AppCompatActivity
                                  implements RegistrationControllerListener {

    private RegistrationController controller;
    private EditText editText_email, editText_password;

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
        final Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(RegistrationActivity.this, message, toastLength).show();
    }

    private void initViews() {
        editText_email = (EditText) findViewById(R.id.input_email);
        editText_password = (EditText) findViewById(R.id.input_password);
        final Button button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = editText_email.getText().toString();
                final String password = editText_password.getText().toString();
                controller.onSubmit(email, password);
            }
        });
    }

    private void initController() {
        controller = new RegistrationController(this, this);
    }

}
