package com.example.rum8.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rum8.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseApp;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    //member variables for text field
    private EditText emailField;
    private EditText passwordField;
    private Button buttonLogin;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // views
        emailField = (EditText) findViewById(R.id.user_email);
        passwordField = (EditText) findViewById(R.id.user_password);
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

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            //TODO: update UI if the user is already log-in
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}