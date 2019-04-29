package com.example.rum8.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rum8.R;
import com.example.rum8.controllers.PasswordRecoveryController;
import com.example.rum8.listeners.PasswordRecoveryControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;


public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryControllerListener {

  private EditText emailField;
  private EditText passwordField;
  private Button button_resetPassword;
  private PasswordRecoveryController controller;

  // [START declare_auth]
  private FirebaseAuth mAuth;
  // [END declare_auth]

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_password_recovery);
    initViews();
    initController();

    // views
    emailField = (EditText) findViewById(R.id.user_email);
    passwordField = (EditText) findViewById(R.id.user_password);
    button_resetPassword = (Button) findViewById(R.id.button_reset_password);

    // [START initialize_auth]
    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();
    // [END initialize_auth]

  }

  private void initViews() {
  }

  private void initController() {
    controller = new PasswordRecoveryController(this);
  }

  // [START on_start_check_user]
  @Override
  public void onStart() {
    super.onStart();

  }
  // [END on_start_check_user]

}
