package com.example.rum8;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mEmail, mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    // users password and email
    private EditText uEmail, uPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // listener to check the status of registration
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                // get the current user (should be null)
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // check the user not null then they can't register again
                if (user != null) {

                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(RegistrationActivity.this, "You have registered",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        };


        // get the button, user email, password
        mRegister = (Button) findViewById(R.id.button_register);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);

        mRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                if (email == null) {
                    Toast.makeText(RegistrationActivity.this, "please use your " +
                                    "ucsd official email: example: abc@ucsd.edu",
                            Toast.LENGTH_SHORT).show();
                }


                // check for email length less than 9 chars
                if (email.length() < 10) {
                    Toast.makeText(RegistrationActivity.this, "please use your " +
                                    "ucsd official email: example: abc@ucsd.edu",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    // get the last 8 chars of the email address
                    String last9 = email.substring(email.length() - 9);

                    // check for not ucsd email
                    if (!last9.equals("@ucsd.edu")) {
                        Toast.makeText(RegistrationActivity.this, "please use your " +
                                        "ucsd official email: example: abc@ucsd.edu",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // check for password length less that 6 characters
                if (mPassword.length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Your password " +
                                    "need to be more than 6 characters .",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                        (RegistrationActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Success", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Error:", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }
}
