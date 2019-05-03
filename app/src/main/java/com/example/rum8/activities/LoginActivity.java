package com.example.rum8.activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
		button_goToPasswordRecovery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				controller.onGoToPasswordRecoverClicked();
			}
		});

		// override onClick goToRegistration button
		final Button button_goToRegistration = findViewById(R.id.button_register);
		button_goToRegistration.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.onGoToRegistrationButtonClicked();
			}
		});

		// views
		emailField = (TextInputEditText) findViewById(R.id.user_email);
		passwordField = (TextInputEditText) findViewById(R.id.user_password);
		buttonLogin = (Button) findViewById(R.id.button_login);

		findViewById(R.id.button_login);

		// override onClick login button
		buttonLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String email = emailField.getText().toString();
				String pw = passwordField.getText().toString();
				controller.userLogin(email, pw);
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		controller.destroy();
	}

	private void initController() {
		controller = new LoginController(this, this);
	}

}