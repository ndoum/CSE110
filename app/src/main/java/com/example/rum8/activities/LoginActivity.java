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

<<<<<<< HEAD
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
=======
    //member variables for text field
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private Button buttonLogin;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initController();

        // views
        emailField = (TextInputEditText) findViewById(R.id.user_email);
        passwordField = (TextInputEditText) findViewById(R.id.user_password);
        buttonLogin = (Button) findViewById(R.id.button_login);

        findViewById(R.id.button_login);

        FirebaseApp.initializeApp(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = emailField.getText().toString();
                String pw = passwordField.getText().toString();

                // check empty emails
                if (TextUtils.isEmpty(email)) {
                    emailField.setError("Required");
                    return;
                }
                //check empty pw
                if (TextUtils.isEmpty(pw)) {
                    passwordField.setError("Required");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener
                        (LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Success", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //TODO: updateUI
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Error:", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //TODO: updateUI
                                }
                            }
                        });

            }
        });

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

    private void initViews() {


        final Button button_goToPasswordRecovery = findViewById(R.id.button_password_recovery);
        button_goToPasswordRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                controller.onGoToPasswordRecoverClicked();
            }
        });
        final Button button_goToRegistration = findViewById(R.id.button_register);
        button_goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onGoToRegistrationButtonClicked();
            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //TODO: update UI if the user is already log-in
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initController() {
        controller = new LoginController(this);
    }
>>>>>>> ccee4b2efd9085183cd9135317f7c4043493b2dc

}