package com.example.rum8.controllers;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rum8.listeners.LoginControllerListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LoginController {
    private LoginControllerListener controllerListener;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public LoginController(final LoginControllerListener controllerListener, final Context context) {
        this.controllerListener = controllerListener;
        this.context = context;

        // Listener to check the status of login
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(final @NonNull FirebaseAuth firebaseAuth) {

                // Get the current user
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);
    }

    public void userLogin(final String email, final String password) {
        if (!isValidEmail(email)) {
            final String message = "Please use your UCSD email (i.e. abc@ucsd.edu)";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else if (!isValidPassword(password)) {
            final String message = "Your password need to be more than 6 characters";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                    ((Activity) context, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            final String message;
                            if (task.isSuccessful()) {
                                if (!auth.getCurrentUser().isEmailVerified()) {
                                    message = "Please verify your email!";
                                    controllerListener.showToast(message, Toast.LENGTH_SHORT);
                                } else {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Success", "signInWithEmail:success");
                                    FirebaseUser user = auth.getCurrentUser();
                                    onLoginSuccessful();
                                }
                            } else {
                                checkIfRegistered(email);
                                Log.w("Error:", "signInWithEmail:failure", task.getException());
                            }
                        }
                    });
        }
    }

    /*
     * This function checks if the user email is already registered
     * */
    private void checkIfRegistered(String email) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(
                new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        final String message;

                        // check account exist
                        if (task.getResult().getSignInMethods().isEmpty()) {
                            message = "Account does not exist! Please register first!";
                            controllerListener.showToast(message, Toast.LENGTH_SHORT);
                        } else {
                            message = "Wrong password!";
                            controllerListener.showToast(message, Toast.LENGTH_SHORT);
                        }
                    }
                });
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

    public void onGoToRegistrationButtonClicked() {
        controllerListener.goToRegistration();
    }

    public void onGoToPasswordRecoverClicked() {
        controllerListener.goToPasswordRecover();
    }

    public void onLoginSuccessful() {
        controllerListener.goToMainPage();
    }

    public void destroy() {
        auth.removeAuthStateListener(authStateListener);
    }
}

