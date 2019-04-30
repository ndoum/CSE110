package com.example.rum8.controllers;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.rum8.listeners.RegistrationControllerListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationController {

    private RegistrationControllerListener controllerListener;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public RegistrationController(final RegistrationControllerListener controllerListener, final Context context) {
        this.controllerListener = controllerListener;
        this.context = context;

        // Listener to check the status of registration
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(final @NonNull FirebaseAuth firebaseAuth) {

                // Get the current user
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                /*// If the user exists
                if (user != null) {
                    controllerListener.onUserRegistered();

                    final String message = "You have registered";
                    controllerListener.showToast(message, Toast.LENGTH_SHORT);
                }*/
            }
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);
    }

    public void onSubmit(final String email, final String password) {
        if (!isValidEmail(email)) {
            final String message = "Please use your UCSD email (i.e. abc@ucsd.edu)";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else if (!isValidPassword(password)) {
            final String message = "Your password need to be more than 6 characters";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                    ((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(final @NonNull Task<AuthResult> task) {
                            final String message;
                            if (task.isSuccessful()) {
                                controllerListener.onUserRegistered();
                                Log.d("Success", "createUserWithEmail:success");
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    message = "An account with this email already exists";
                                } else {
                                    message = "Authentication failed";
                                }
                                controllerListener.showToast(message, Toast.LENGTH_SHORT);

                                Log.e("Error:", "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });
        }
    }

    private static boolean isValidEmail(final String email) {
        if (email == null) {
            return false;
        }

        final int minimumEmailLength = 10;
        return email.length() >= minimumEmailLength && email.endsWith("@ucsd.edu");
    }

    private static boolean isValidPassword(final String password) {
        final int minimumPasswordLength = 6;
        return password != null && password.length() >= minimumPasswordLength;
    }

    public void destroy() {
        auth.removeAuthStateListener(authStateListener);
    }


    public void onGoBackToLoginButtonClicked(){ this.controllerListener.goBackToLogin();}

}
