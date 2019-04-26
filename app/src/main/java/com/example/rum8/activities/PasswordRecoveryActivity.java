package com.example.rum8.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rum8.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.support.v7.app.AppCompatActivity;


public class PasswordRecoveryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailField;
    private EditText passwordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        // views
        emailField = findViewById(R.id.input_email);
        passwordField = findViewById(R.id.input_password);

        //buttons
        //TODO: should be forget password bottom
        findViewById(R.id.button_register).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //TODO: update UI based on user status
    }
    // [END on_start_check_user]

    @Override
    public void onClick(View v) {
        //TODO: everthing: function call; implement functions etc.

    }

}
