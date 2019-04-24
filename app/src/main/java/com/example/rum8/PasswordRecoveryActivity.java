package com.example.rum8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.quickstart.auth.R;

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
