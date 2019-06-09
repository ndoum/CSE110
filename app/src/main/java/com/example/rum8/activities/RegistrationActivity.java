package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RegistrationController;
import com.example.rum8.listeners.RegistrationControllerListener;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Class that handles registration of user by checking input
 * of user and authenticate user in the fire base.
 */
public class RegistrationActivity extends AppCompatActivity
        implements RegistrationControllerListener {

    //Initialize class variable
    private RegistrationController controller;
    private TextInputEditText editText_email;
    private TextInputEditText editText_password;
    private TextInputEditText editText_passwordConfirm;

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
    public void showToast(final String message) {
        Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        editText_email = findViewById(R.id.input_email);
        editText_password = findViewById(R.id.input_password);
        editText_passwordConfirm = findViewById(R.id.input_password_confirm);
        final Button button_register = findViewById(R.id.button_register);

        button_register.setOnClickListener(v -> {
            final String email = editText_email.getText().toString();
            final String password = editText_password.getText().toString();
            final String passwordConfirm = editText_passwordConfirm.getText().toString();
            controller.onSubmit(email, password, passwordConfirm);
        });
    }

    private void initController() {
        controller = new RegistrationController(this, this);
    }

}
